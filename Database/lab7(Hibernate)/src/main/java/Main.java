import model.BookEntity;
import model.GenreEntity;
import model.ReaderEntity;


import org.hibernate.HibernateException;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.awt.print.Book;
import java.util.Scanner;


public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {// Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession(); //return opened session
    }

    public static void main(final String[] args) throws Exception {
        // get opened session
        Session session = getSession();

        try {
//            readData(session);
//            updateData(session);
//            deleteData(session);
//            addData(session);
            startApp(session);

            System.out.println("Finish work!");
        } finally {
            session.close();
            System.exit(0);
        }
    }

    private static void readData(Session session) {

        Query query = session.createQuery("FROM model.BookEntity");
        System.out.println("Books: ");
        for(Object obj: query.list()) {
            BookEntity book = (BookEntity) obj;
            int id = book.getBookId();
            String title = book.getBookTitle();
            String author = book.getBookAuthor();
            GenreEntity genre = book.getGenre();
            String publisher = book.getBookPublisher();
            Boolean availability = book.getBookAvailability();
            System.out.println("id: " + id + " ---Title: " + title + " ---Author: " + author + " ---Genre: " + genre + " ---Publisher: " + publisher + " Availability: " + availability);

        }

        query = session.createQuery("FROM model.GenreEntity");
        System.out.println("Genres: ");
        for (Object obj: query.list()) {
            GenreEntity genre = (GenreEntity) obj;
            System.out.println(genre.getGenre());
        }

        query = session.createQuery("FROM model.ReaderEntity");
        System.out.println("Readers: ");
        for (Object obj: query.list()) {
            ReaderEntity reader = (ReaderEntity) obj;
            int id = reader.getReaderId();
            String name = reader.getReaderName();
            String surname = reader.getReaderSurname();
            String address = reader.getReaderAddress();
            System.out.println("id: " + id + " ---name: " + name + " ---surname: " + surname + " ---address: " + address);
        }

        query = session.createQuery("FROM model.ReaderEntity");
        System.out.println("Reader's books: ");
        for (Object obj : query.list()) {
            ReaderEntity reader = (ReaderEntity) obj;
            for (BookEntity book : reader.getBooks()) {
                System.out.println(reader.getReaderSurname() + " :  " + book.getBookTitle()+ book.getBookAuthor());
            }
        }

        startApp(session);
    }

    private static void updateData(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input book id, which availability you wand to update: ");
        int id = Integer.parseInt(input.next());
//        int id = 10;
        Query query1 = session.createQuery("FROM model.BookEntity  WHERE id = :code1");
        query1.setParameter("code1", id);
        Object obj = query1.list().get(0);
        BookEntity book = (BookEntity) obj;
        Boolean curAvail = book.getBookAvailability();
        System.out.println("curAvail:  " + curAvail);

        BookEntity bookEntity = session.load(BookEntity.class, id);
        if (bookEntity != null) {
            session.beginTransaction();
            Query query = session.createQuery("UPDATE BookEntity SET bookAvailability = :code1 WHERE id = :code2");
            query.setParameter("code1", !curAvail);
            query.setParameter("code2", id);
            query.executeUpdate();
            session.getTransaction().commit();

            System.out.println("curAvail:  " + !curAvail);
        } else {System.out.println("This book isnt exists, heh");}

        startApp(session);
    }

    private static void deleteData(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input reader id, who you wand to delete: ");
        int id = Integer.parseInt(input.next());

        ReaderEntity readerEntity = session.load(ReaderEntity.class, id);
        if (readerEntity != null) {
            session.beginTransaction();
            Query query = session.createQuery("DELETE ReaderEntity WHERE readerId = :code");
            query.setParameter("code", id);
            query.executeUpdate();
            session.getTransaction().commit();
        }

        startApp(session);
    }

    private static void deleteConnData(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("\n(delete) Input reader id: ");
        int reader_id = Integer.parseInt(input.next());
        System.out.println("(delete) Input book id: ");
        int book_id = Integer.parseInt(input.next());

        session.beginTransaction();
        Query query1 = session.createQuery("from BookEntity where id= :codeBook");
        query1.setParameter("codeBook", book_id);
        BookEntity bookEntity = (BookEntity) query1.list().get(0);
        Query query2 = session.createQuery("from ReaderEntity  where id= :codeReader");
        query2.setParameter("codeReader", reader_id);
        ReaderEntity readerEntity = (ReaderEntity) query2.list().get(0);
        bookEntity.deleteReader(readerEntity);
        session.getTransaction().commit();
        System.out.println("Deleted from join table.");
//artistsEntity.setProjects();
        startApp(session);
    }

    private static void addData(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput reader id: ");
        int reader_id = Integer.parseInt(input.next());
        System.out.println("Input book id: ");
        int book_id = Integer.parseInt(input.next());

        StoredProcedureQuery query = session.createStoredProcedureQuery("insert_history")
        .registerStoredProcedureParameter("book_id", Integer.class, ParameterMode.IN)
        .registerStoredProcedureParameter("reader_id", Integer.class, ParameterMode.IN)
        .setParameter("book_id", book_id).setParameter("reader_id", reader_id);

        query.execute();
        String str = (String) query.getResultList().get(0);
        System.out.println(str);

        startApp(session);
    }


    private static void startApp(Session session) {
        Scanner input = new Scanner(System.in);
        System.out.println("\nHello! \n type: 1, if you want get all data \n 2, if you want to update book availability \n" +
                "3, if you want to delete user \n 4, if you want to insert history \n 5, if you want to delete connection\n 0, if you want to quit: ");
        int choose = Integer.parseInt(input.next());

        switch (choose) {
            case 1: readData(session); break;
            case 2: updateData(session); break;
            case 3: deleteData(session); break;
            case 4: addData(session); break;
            case 5: deleteConnData(session); break;
            case 0: break;
            default: startApp(session);

        }

    }
}