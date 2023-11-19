package hei.example.repository;

import hei.example.model.Book;
import hei.example.model.BookStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCrudOperations implements CrudOperations<Book> {

    private Connection connection;
    public BookCrudOperations(Connection connection) {
        this.connection = connection;
    }



    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try {
            String query = "SELECT * FROM book";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = mapResultSetToBook(resultSet);
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        String query = "INSERT INTO book (book_name, page_numbers, release_date, author_id, topic_id, book_status) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (Book book : toSave) {
                    preparedStatement.setString(1, book.getBookName());
                    preparedStatement.setInt(2, book.getPageNumbers());
                    preparedStatement.setDate(3, book.getReleaseDate());
                    preparedStatement.setInt(4, book.getAuthorId());
                    preparedStatement.setInt(5, book.getTopicId());
                    preparedStatement.setString(6, String.valueOf(book.getBookStatus()));
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toSave;
    }

    @Override
    public Book save(Book toSave) {
        String query = "INSERT INTO book (book_name, page_numbers, release_date, author_id, topic_id, book_status) VALUES (?, ?, ?, ?, ?, ?) RETURNING *";

        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, toSave.getBookName());
                preparedStatement.setInt(2, toSave.getPageNumbers());
                preparedStatement.setDate(3, toSave.getReleaseDate());
                preparedStatement.setInt(4, toSave.getAuthorId());
                preparedStatement.setInt(5, toSave.getTopicId());
                preparedStatement.setString(6, toSave.getBookStatus().getLabel());


                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapResultSetToBook(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Book delete(Book toDelete) {
        String query = "DELETE FROM book WHERE book_id = ?";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                Integer bookId = toDelete.getBookId();
                if (bookId != null) {
                    preparedStatement.setInt(1, bookId);
                    preparedStatement.executeUpdate();
                } else {

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDelete;
    }

    private Book mapResultSetToBook(ResultSet resultSet) throws SQLException {
        Book book = new Book();
        book.setBookId(resultSet.getInt("book_id"));
        book.setBookName(resultSet.getString("book_name"));
        book.setPageNumbers(resultSet.getInt("page_numbers"));
        book.setReleaseDate(resultSet.getDate("release_date"));
        book.setAuthorId(resultSet.getInt("author_id"));
        book.setTopicId(resultSet.getInt("topic_id"));
        book.setBookStatus(BookStatus.valueOf(resultSet.getString("book_status")));
        return book;
    }
}
