package hei.example;

import hei.example.model.*;
import hei.example.model.BookStatus;
import hei.example.repository.AuthorCrudOperations;
import hei.example.repository.BookCrudOperations;
import hei.example.model.Book;
import hei.example.repository.SubscribersCrudOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        ConnectDatabase connectDatabase = new ConnectDatabase();
        Connection connection = null;

        try {
            connection = connectDatabase.openConnection();

            if (connection != null) {
                System.out.println("Connexion à la base de données réussie ok!");
            } else {
                System.out.println("La connexion à la base de données a échoué.");
            }


            // Utiliser la même instance de connexion créée au début
            BookCrudOperations bookCrudOperations = new BookCrudOperations(connection);

            // Tester la méthode findAll de BookCrudOperations
            logger.info("Test de la méthode findAll de BookCrudOperations");

            try {
                // ...
                List<Book> allBooks = bookCrudOperations.findAll();
                allBooks.forEach(book -> logger.info("Livre trouvé : {}", book));
                // ...
            } catch (Exception e) {
                logger.error("Une erreur s'est produite lors du test de la méthode findAll : {}", e.getMessage());
            }

            // Tester la méthode saveAll
            logger.info("Test de la méthode saveAll de BookCrudOperations");

            // Créer la liste de livres à sauvegarder
            List<Book> booksToSave = new ArrayList<>();

            Book book1 = new Book();
            book1.setBookName("Livre 1");
            book1.setPageNumbers(150);
            book1.setReleaseDate(java.sql.Date.valueOf("2023-02-01"));
            book1.setAuthorId(2); // Remplacez par l'ID réel de l'auteur
            book1.setTopicId(3); // Remplacez par l'ID réel du sujet
            book1.setBookStatus(BookStatus.available);
            booksToSave.add(book1);

            Book book2 = new Book();
            book2.setBookName("Livre 2");
            book2.setPageNumbers(200);
            book2.setReleaseDate(java.sql.Date.valueOf("2023-02-15"));
            book2.setAuthorId(1); // Remplacez par l'ID réel de l'auteur
            book2.setTopicId(2); // Remplacez par l'ID réel du sujet
            book2.setBookStatus(BookStatus.borrowed);
            booksToSave.add(book2);

            // Appeler la méthode saveAll
            List<Book> savedBooks = bookCrudOperations.saveAll(booksToSave);

            // Journaliser les livres sauvegardés
            savedBooks.forEach(saved -> logger.info("Livre sauvegardé : {}", saved));


            // Tester la méthode save
            logger.info("Test de la méthode save de BookCrudOperations");
            Book newBook = new Book();
            newBook.setBookName("Nouveau Livre");
            newBook.setPageNumbers(200);
            newBook.setReleaseDate(java.sql.Date.valueOf("2023-01-01"));
            newBook.setAuthorId(1);
            newBook.setTopicId(1);
            newBook.setBookStatus(BookStatus.available);

            Book savedBook = bookCrudOperations.save(newBook);
            logger.info("Livre sauvegardé : {}", savedBook);

            // Tester la méthode delete
            logger.info("Test de la méthode delete de BookCrudOperations");
            Book bookToDelete = savedBooks.get(1);
            Book deletedBook = bookCrudOperations.delete(bookToDelete);
            logger.info("Livre supprimé : {}", deletedBook);

            // Tester d'autres méthodes selon vos besoins...
            logger.info("Tests de BookCrudOperations terminés!");


            // Utilisez la même instance de connexion créée au début
            AuthorCrudOperations authorCrudOperations = new AuthorCrudOperations(connection);

            // Tester la méthode findAll de AuthorCrudOperations
            logger.info("Test de la méthode findAll de AuthorCrudOperations");

            try {
                List<Author> allAuthors = authorCrudOperations.findAll();
                allAuthors.forEach(author -> logger.info("Auteur trouvé : {}", author));
            } catch (Exception e) {
                logger.error("Une erreur s'est produite lors du test de la méthode findAll : {}", e.getMessage());
            }

            // Tester la méthode saveAll de AuthorCrudOperations
            logger.info("Test de la méthode saveAll de AuthorCrudOperations");

            // Créer la liste d'auteurs à sauvegarder
            List<Author> authorsToSave = new ArrayList<>();

            Author author1 = new Author();
            author1.setAuthorName("Auteur 1");
            author1.setSex(Sex.M);
            authorsToSave.add(author1);

            Author author2 = new Author();
            author2.setAuthorName("Auteur 2");
            author2.setSex(Sex.F);
            authorsToSave.add(author2);

            // Appeler la méthode saveAll
            List<Author> savedAuthors = authorCrudOperations.saveAll(authorsToSave);

            // Journaliser les auteurs sauvegardés
            savedAuthors.forEach(saved -> logger.info("Auteur sauvegardé : {}", saved));

            // Tester la méthode save de AuthorCrudOperations
            logger.info("Test de la méthode save de AuthorCrudOperations");
            Author newAuthor = new Author();
            newAuthor.setAuthorName("Nouvel Auteur");
            newAuthor.setSex(Sex.M);

            Author savedAuthor = authorCrudOperations.save(newAuthor);
            logger.info("Auteur sauvegardé : {}", savedAuthor);

            // Tester la méthode delete de AuthorCrudOperations
            logger.info("Test de la méthode delete de AuthorCrudOperations");
            Author authorToDelete = savedAuthors.get(1);
            Author deletedAuthor = authorCrudOperations.delete(authorToDelete);
            logger.info("Auteur supprimé : {}", deletedAuthor);

            // Tester d'autres méthodes selon vos besoins...
            logger.info("Tests de AuthorCrudOperations terminés!");

            // Utilisez la même instance de connexion créée au début
            SubscribersCrudOperations subscribersCrudOperations = new SubscribersCrudOperations(connection);

            // Tester la méthode findAll de SubscribersCrudOperations
            logger.info("Test de la méthode findAll de SubscribersCrudOperations");

            try {
                List<Subscribers> allSubscribers = subscribersCrudOperations.findAll();
                allSubscribers.forEach(subscribers -> logger.info("Abonné trouvé : {}", subscribers));
            } catch (Exception e) {
                logger.error("Une erreur s'est produite lors du test de la méthode findAll : {}", e.getMessage());
            }

            // Tester la méthode saveAll de SubscribersCrudOperations
            logger.info("Test de la méthode saveAll de SubscribersCrudOperations");

            // Créer la liste d'abonnés à sauvegarder
            List<Subscribers> subscribersToSave = new ArrayList<>();

            Subscribers subscriber1 = new Subscribers();
            subscriber1.setUsername("Abonne1");
            subscriber1.setPassword("MotDePasse1");
            subscriber1.setSubscriptionStartDate(Date.valueOf("2023-02-01"));
            subscriber1.setSubscriptionEndDate(Date.valueOf("2023-03-01"));
            subscribersToSave.add(subscriber1);

            Subscribers subscriber2 = new Subscribers();
            subscriber2.setUsername("Abonne2");
            subscriber2.setPassword("MotDePasse2");
            subscriber2.setSubscriptionStartDate(Date.valueOf("2023-02-15"));
            subscriber2.setSubscriptionEndDate(Date.valueOf("2023-03-15"));
            subscribersToSave.add(subscriber2);

            // Appeler la méthode saveAll
            List<Subscribers> savedSubscribers = subscribersCrudOperations.saveAll(subscribersToSave);

            // Journaliser les abonnés sauvegardés
            savedSubscribers.forEach(saved -> logger.info("Abonné sauvegardé : {}", saved));

            // Tester la méthode save de SubscribersCrudOperations
            logger.info("Test de la méthode save de SubscribersCrudOperations");
            Subscribers newSubscriber = new Subscribers();
            newSubscriber.setUsername("NouvelAbonne");
            newSubscriber.setPassword("NouveauMotDePasse");
            newSubscriber.setSubscriptionStartDate(Date.valueOf("2023-01-01"));
            newSubscriber.setSubscriptionEndDate(Date.valueOf("2023-02-01"));

            Subscribers savedSubscriber = subscribersCrudOperations.save(newSubscriber);
            logger.info("Abonné sauvegardé : {}", savedSubscriber);

            // Tester la méthode delete de SubscribersCrudOperations
            logger.info("Test de la méthode delete de SubscribersCrudOperations");
            Subscribers subscriberToDelete = savedSubscribers.get(1);
            Subscribers deletedSubscriber = subscribersCrudOperations.delete(subscriberToDelete);
            logger.info("Abonné supprimé : {}", deletedSubscriber);

            // Tester d'autres méthodes selon vos besoins...
            logger.info("Tests de SubscribersCrudOperations terminés!");


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
