import os
from google.oauth2.credentials import Credentials
from googleapiclient.discovery import build
from google.auth.transport.requests import Request
from google_auth_oauthlib.flow import InstalledAppFlow

# If modifying these scopes, delete the file token.json.
SCOPES = ['https://www.googleapis.com/auth/drive.file', 'https://www.googleapis.com/auth/documents']

def authenticate():
    creds = None
    if os.path.exists('token.json'):
        creds = Credentials.from_authorized_user_file('token.json', SCOPES)
    if not creds or not creds.valid:
        if creds and creds.expired and creds.refresh_token:
            creds.refresh(Request())
        else:
            flow = InstalledAppFlow.from_client_secrets_file(
                'credentials.json', SCOPES)
            creds = flow.run_local_server(port=0)
        with open('token.json', 'w') as token:
            token.write(creds.to_json())
    return creds

def create_document(drive_service, docs_service, title, content):
    document = drive_service.files().create(
        body={'name': title, 'mimeType': 'application/vnd.google-apps.document'},
        fields='id'
    ).execute()

    doc_id = document.get('id')

    requests = [
        {
            'insertText': {
                'location': {'index': 1},
                'text': content
            }
        }
    ]

    docs_service.documents().batchUpdate(
        documentId=doc_id, body={'requests': requests}).execute()

    return doc_id

def read_document(docs_service, doc_id):
    document = docs_service.documents().get(documentId=doc_id).execute()
    content = document.get('body').get('content')
    full_text = ""
    for section in content:
        if 'paragraph' in section:
            elements = section.get('paragraph').get('elements')
            for elem in elements:
                full_text += elem.get('textRun').get('content')
    return full_text

def update_document(docs_service, doc_id, new_content):
    try:
        # First, get the current document content
        document = docs_service.documents().get(documentId=doc_id).execute()
        
        # Prepare the requests
        requests = []
        
        # Check if the document has any content
        if 'body' in document and 'content' in document['body']:
            content = document['body']['content']
            if len(content) > 1:
                # If there's content, delete it all except the first element
                requests.append({
                    'deleteContentRange': {
                        'range': {
                            'startIndex': 1,
                            'endIndex': document['body']['content'][-1]['endIndex'] - 1
                        }
                    }
                })
        
        # Insert the new content
        requests.append({
            'insertText': {
                'location': {'index': 1},
                'text': new_content
            }
        })
        
        # Execute the update
        result = docs_service.documents().batchUpdate(
            documentId=doc_id, body={'requests': requests}).execute()
        
        print("Document updated successfully")
        return True
    except Exception as e:
        print(f"An error occurred: {str(e)}")
        return False

# The rest of your code remains the same

def list_documents(drive_service):
    results = drive_service.files().list(
        pageSize=10, fields="nextPageToken, files(id, name)",
        q="mimeType='application/vnd.google-apps.document'").execute()
    return results.get('files', [])

def main():
    creds = authenticate()
    drive_service = build('drive', 'v3', credentials=creds)
    docs_service = build('docs', 'v1', credentials=creds)

    while True:
        print("\nStorage as a Service Menu:")
        print("1. Create a new document")
        print("2. Read a document")
        print("3. Update a document")
        print("4. List all documents")
        print("5. Exit")
        
        choice = input("Enter your choice (1-5): ")

        if choice == '1':
            title = input("Enter document title: ")
            content = input("Enter document content: ")
            doc_id = create_document(drive_service, docs_service, title, content)
            print(f"Created document with ID: {doc_id}")

        elif choice == '2':
            doc_id = input("Enter document ID: ")
            content = read_document(docs_service, doc_id)
            print(f"Document content: {content}")

        elif choice == '3':
            doc_id = input("Enter document ID: ")
            new_content = input("Enter new content: ")
            if update_document(docs_service, doc_id, new_content):
                    print("Document updated successfully")
            else:
                print("Failed to update document")

        elif choice == '4':
            files = list_documents(drive_service)
            if not files:
                print("No documents found.")
            else:
                print("Documents:")
                for file in files:
                    print(f"ID: {file['id']}, Name: {file['name']}")

        elif choice == '5':
            print("Exiting the program. Goodbye!")
            break

        else:
            print("Invalid choice. Please try again.")

if __name__ == '__main__':
    main()