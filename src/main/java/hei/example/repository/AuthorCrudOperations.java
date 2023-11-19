package hei.example.repository;

import hei.example.model.Author;
import hei.example.model.Sex;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorCrudOperations implements CrudOperations<Author> {

    private Connection connection;

    public AuthorCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();
        try {
            String query = "SELECT * FROM author";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Author author = mapResultSetToAuthor(resultSet);
                    authors.add(author);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave) {
        String query = "INSERT INTO author (author_name, sex) VALUES (?, ?)";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (Author author : toSave) {
                    preparedStatement.setString(1, author.getAuthorName());
                    preparedStatement.setString(2, author.getSex().name());
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
    public Author save(Author toSave) {
        String query = "INSERT INTO author (author_name, sex) VALUES (?, ?) RETURNING *";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, toSave.getAuthorName());
                preparedStatement.setString(2, toSave.getSex().name());

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return mapResultSetToAuthor(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Author delete(Author toDelete) {
        String query = "DELETE FROM author WHERE author_id = ?";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                Integer authorId = Math.toIntExact(toDelete.getAuthorId());
                if (authorId != null) {
                    preparedStatement.setInt(1, authorId);
                    preparedStatement.executeUpdate();
                } else {

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDelete;
    }


    private Author mapResultSetToAuthor(ResultSet resultSet) throws SQLException {
        Author author = new Author();
        author.setAuthorId(resultSet.getInt("author_id"));
        author.setAuthorName(resultSet.getString("author_name"));
        author.setSex(Sex.valueOf(resultSet.getString("sex")));
        return author;
    }
}
