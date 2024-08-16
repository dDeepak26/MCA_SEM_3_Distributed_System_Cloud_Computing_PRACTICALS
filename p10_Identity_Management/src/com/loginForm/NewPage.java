package com.loginForm;

import javax.swing.*;
import java.awt.*;

class NewPage extends JFrame {
	private static final long serialVersionUID = 1L;
	
    NewPage() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Welcome");
        setSize(400, 200);
    }

    public static void main(String[] args) {
        NewPage page = new NewPage();
        page.setVisible(true);
    }
}
