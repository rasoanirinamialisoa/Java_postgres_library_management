package hei.example.repository;

import hei.example.model.Subscribers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscribersCrudOperations implements CrudOperations<Subscribers> {

    private Connection connection;

    public SubscribersCrudOperations(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Subscribers> findAll() {
        List<Subscribers> subscribersList = new ArrayList<>();
        try {
            String query = "SELECT * FROM subscribers";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                 ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Subscribers subscribers = mapResultSetToSubscribers(resultSet);
                    subscribersList.add(subscribers);
                }
            }
        } catch (SQLException e) {

            throw new RuntimeException("Erreur lors de la récupération des abonnés.", e);
        }
        return subscribersList;
    }

    @Override
    public List<Subscribers> saveAll(List<Subscribers> toSave) {
        String query = "INSERT INTO subscribers (subscriber_username, subscriber_password, subscription_start_date, subscription_end_date) VALUES (?, ?, ?, ?)";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (Subscribers subscribers : toSave) {
                    preparedStatement.setString(1, subscribers.getUsername());
                    preparedStatement.setString(2, subscribers.getPassword());
                    preparedStatement.setDate(3, subscribers.getSubscriptionStartDate());
                    preparedStatement.setDate(4, subscribers.getSubscriptionEndDate());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {

            throw new RuntimeException("Erreur lors de l'enregistrement des abonnés.", e);
        }
        return toSave;
    }

    @Override
    public Subscribers save(Subscribers toSave) {
        String query = "INSERT INTO subscribers (subscriber_username, subscriber_password, subscription_start_date, subscription_end_date) VALUES (?, ?, ?, ?) RETURNING *";
        try {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, toSave.getUsername());
                preparedStatement.setString(2, toSave.getPassword());
                preparedStatement.setDate(3, toSave.getSubscriptionStartDate());
                preparedStatement.setDate(4, toSave.getSubscriptionEndDate());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            return mapResultSetToSubscribers(resultSet);
                        }
                    }
                }
            }
        } catch (SQLException e) {

            throw new RuntimeException("Erreur lors de l'enregistrement d'un abonné.", e);
        }
        return null;
    }

    @Override
    public Subscribers delete(Subscribers toDelete) {
        String query = "DELETE FROM subscribers WHERE subscriber_id = ?";
        try {
            Long userId = toDelete.getId();
            if (userId != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setLong(1, userId);
                    preparedStatement.executeUpdate();
                }
            } else {

                throw new IllegalArgumentException("L'ID de l'abonné à supprimer est null.");
            }
        } catch (SQLException e) {

            throw new RuntimeException("Erreur lors de la suppression de l'abonné.", e);
        }
        return toDelete;
    }


    private Subscribers mapResultSetToSubscribers(ResultSet resultSet) throws SQLException {
        return new Subscribers(
                resultSet.getLong("subscriber_id"),
                resultSet.getString("subscriber_username"),
                resultSet.getString("subscriber_password"),
                resultSet.getDate("subscription_start_date"),
                resultSet.getDate("subscription_end_date")
        );
    }
}

