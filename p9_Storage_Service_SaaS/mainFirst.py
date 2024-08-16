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
    requests = [
        {
            'insertText': {
                'location': {'index': 1},
                'text': new_content
            }
        }
    ]

    docs_service.documents().batchUpdate(
        documentId=doc_id, body={'requests': requests}).execute()

def delete_document(drive_service, doc_id):
    drive_service.files().delete(fileId=doc_id).execute()

def main():
    creds = authenticate()
    drive_service = build('drive', 'v3', credentials=creds)
    docs_service = build('docs', 'v1', credentials=creds)

    # Example usage
    doc_id = create_document(drive_service, docs_service, "Test Document One", "Hello, World! Deepak Durgam here")
    print(f"Created document with ID: {doc_id}")

    content = read_document(docs_service, doc_id)
    print(f"Document content: {content}")

    update_document(docs_service, doc_id, "new content ")
    content = read_document(docs_service, doc_id)
    print(f"Updated document content: {content}")

    delete_document(drive_service, doc_id)
    print("Document deleted")

if __name__ == '__main__':
    main()