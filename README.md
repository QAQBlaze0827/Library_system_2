# Library Management System

This is a simple Library Management System implemented in Java. It allows users to borrow, return, and manage books, and provides basic user and admin functionality. The application uses CSV files to persist data.

## Project Structure

```
Library_system_2
├── AddBook.java             # Handles adding books to the system
├── AddUser.java             # Handles adding users to the system
├── Admin.java               # Admin-related functionalities
├── allBook.csv              # Stores book data
├── Book.java                # Represents book-related data and actions
├── DatabaseConnector.java   # Placeholder for database connection
├── Library.java             # Main library logic
├── Loginui.java             # Login user interface logic
├── MainSystemuiAdmin.java   # Admin UI logic
├── MainSystemuiUser.java    # User UI logic
├── Mainui.java              # Main UI logic
├── oldaddUser.txt           # Legacy user data (optional)
├── oldUser.txt              # Legacy user data (optional)
├── README.md                # Project documentation (this file)
├── Registerui.java          # User registration UI logic
├── RemoveBook.java          # Handles removing books
├── RemoveUser.java          # Handles removing users
├── user.csv                 # Stores user data
└── User.java                # Represents user-related data and actions
```

## Requirements

- **Java Development Kit (JDK)**: Version 8 or above (tested with version `22.0.2`).
- A Java IDE or terminal to compile and run the program.

## How to Run the Program

### Step 1: Compile the Project

Navigate to the project folder where the `.java` files are located. Open a terminal or command prompt and run the following command:

```bash
javac *.java
```

This compiles all the `.java` files in the directory.

### Step 2: Run the Main Program

Run the program using the following command:

```bash
java Mainui.java
```

This will start the main user interface of the application.

## Features

### 1. Book Management
- **Add Book**: Admins can add books to the system.
- **Remove Book**: Admins can remove books.
- **Borrow Book**: Users can borrow books if available.
- **Return Book**: Users can return borrowed books.

### 2. User Management
- **Register User**: New users can be added to the system.
- **Remove User**: Admins can remove users from the system.
- **Login**: Users and admins can log in to access their respective interfaces.

### 3. Data Persistence
- **Books**: Stored in `allBook.csv`.
- **Users**: Stored in `user.csv`.

## CSV File Format

### Books (`allBook.csv`):
```
BookName,BookId,IsBorrowed,BorrowedByUid
```
- **BookName**: Name of the book.
- **BookId**: Unique identifier for the book.
- **IsBorrowed**: `true` if borrowed, `false` otherwise.
- **BorrowedByUid**: User ID of the borrower (if borrowed).

### Users (`user.csv`):
```
UserId,UserName,Password,Role
```
- **UserId**: Unique identifier for the user.
- **UserName**: Name of the user.
- **Password**: User password.
- **Role**: `admin` or `user`.

## Notes

- Ensure `allBook.csv` and `user.csv` are present in the root directory of the project before running.
- To reset the system, clear the contents of the CSV files or replace them with default data.

## Troubleshooting

### "File Not Found" Error
- Make sure the `allBook.csv` and `user.csv` files exist in the same directory as the `.java` files.

### "Access Denied" Error
- Run the terminal or command prompt with appropriate permissions.

## Future Improvements
- Replace CSV-based persistence with a database (e.g., MySQL, SQLite).
- Add unit tests for core functionality.
- Improve user interface with a graphical library such as JavaFX or Swing.

## License
This project is open-source and available under the MIT License.

