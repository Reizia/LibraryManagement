
package librarymanagementsystem;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

class Book {
    String title;
    String author;
    String isbn;
    String borrowerName;
    String borrowDate;

    Book(String title, String author, String isbn, String borrowerName, String borrowDate) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
    }
}

public class LibraryManagementSystem extends Frame implements ActionListener {
    ArrayList<Book> books = new ArrayList<>();

    Label titleLabel, authorLabel, isbnLabel, borrowerLabel;
    TextField titleField, authorField, isbnField, borrowerField;
    Button addButton, removeButton, displayButton;
    TextArea displayArea;
    Panel titlePanel, inputPanel, buttonPanel, displayPanel;

    LibraryManagementSystem() {
        setLayout(new BorderLayout());
        setBackground(new Color(0 ,255, 51));

        // Library title
        Label libraryTitle = new Label("Cavite State University-NAIC Library", Label.CENTER);
        libraryTitle.setFont(new Font("Times New Roman", Font.BOLD, 24)); 
        libraryTitle.setForeground(Color.BLACK);

        titleLabel = new Label("Title:");
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14)); 
        titleLabel.setForeground(Color.BLACK); 
        titleField = new TextField(20);
        authorLabel = new Label("Author:");
        authorLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14)); 
        authorLabel.setForeground(Color.BLACK); 
        authorField = new TextField(20);
        isbnLabel = new Label("ISBN:");
        isbnLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        isbnLabel.setForeground(Color.BLACK); 
        isbnField = new TextField(20);
        borrowerLabel = new Label("Borrower:");
        borrowerLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14)); 
        borrowerLabel.setForeground(Color.BLACK);
        borrowerField = new TextField(20);

        addButton = new Button("Add Book");
        addButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        removeButton = new Button("Remove Book");
        removeButton.setFont(new Font("Times New Roman", Font.PLAIN, 14)); 
        displayButton = new Button("Display Books");
        displayButton.setFont(new Font("Times New Roman", Font.PLAIN, 14)); 
        displayArea = new TextArea(10, 50);
        displayArea.setFont(new Font("Times New Roman", Font.PLAIN, 14)); 
        displayArea.setEditable(false);

      
        setBackground(new Color(0 ,255, 51)); 
        addButton.setBackground(new Color(255, 215, 0)); 
        removeButton.setBackground(new Color(255, 69, 0)); 
        displayButton.setBackground(new Color(124, 252, 0)); 
        displayArea.setBackground(new Color(240, 248, 255)); 

        titlePanel = new Panel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(libraryTitle, BorderLayout.CENTER);
        titlePanel.setBackground(new Color(0 ,255, 51)); 

        inputPanel = new Panel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        inputPanel.add(titleLabel);
        inputPanel.add(titleField);
        inputPanel.add(authorLabel);
        inputPanel.add(authorField);
        inputPanel.add(isbnLabel);
        inputPanel.add(isbnField);
        inputPanel.add(borrowerLabel);
        inputPanel.add(borrowerField);
        inputPanel.setBackground(new Color(0 ,255, 51));

        buttonPanel = new Panel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(displayButton);
        buttonPanel.setBackground(new Color(0 ,255, 51)); 

        displayPanel = new Panel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayArea, BorderLayout.CENTER);
        displayPanel.setBackground(new Color(0 ,255, 51)); 

        add(titlePanel, BorderLayout.NORTH);
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(displayPanel, BorderLayout.EAST);

        addButton.addActionListener(this);
        removeButton.addActionListener(this);
        displayButton.addActionListener(this);

        setTitle("Cavite State University Naic Library");
        setSize(800, 400); 
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            String title = titleField.getText();
            String author = authorField.getText();
            String isbn = isbnField.getText();
            String borrower = borrowerField.getText();
            String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            if (!title.isEmpty() && !author.isEmpty() && !isbn.isEmpty() && !borrower.isEmpty()) {
                books.add(new Book(title, author, isbn, borrower, date));
                titleField.setText("");
                authorField.setText("");
                isbnField.setText("");
                borrowerField.setText("");
                displayArea.setText("Book added successfully!\n");
            } else {
                displayArea.setText("All fields must be filled out.\n");
            }
        } else if (ae.getSource() == removeButton) {
            String isbn = isbnField.getText();
            if (!isbn.isEmpty()) {
                boolean removed = books.removeIf(book -> book.isbn.equals(isbn));
                if (removed) {
                    displayArea.setText("Book removed successfully!\n");
                } else {
                    displayArea.setText("Book not found.\n");
                }
                isbnField.setText("");
            } else {
                displayArea.setText("ISBN field must be filled out.\n");
            }
        } else if (ae.getSource() == displayButton) {
            if (books.isEmpty()) {
                displayArea.setText("No books available.\n");
            } else {
                StringBuilder displayText = new StringBuilder();
                displayText.append("Books in the library:\n");
                for (int i = 0; i < books.size(); i++) {
                    Book book = books.get(i);
                    displayText.append(String.format("%d. Title: %s, Author: %s, ISBN: %s, Borrower: %s, Date: %s\n",
                            i + 1, book.title, book.author, book.isbn, book.borrowerName, book.borrowDate));
                }
                displayArea.setText(displayText.toString());
            }
        }
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}

