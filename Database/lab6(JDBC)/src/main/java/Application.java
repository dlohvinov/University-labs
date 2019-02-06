import java.sql.*;
import java.util.Scanner;

public class Application {
    private static final String url =
            "jdbc:mysql://localhost:3306/library?serverTimezone=UTC&useSSL=false";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static void main(String args[]) {
        try {
            //load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Get a connection to database
            connection = DriverManager.getConnection(url, user, password);

            // Create a statement
            // Statements allow to issue SQL queries to the database
            statement = connection.createStatement();

//            readData();
//            updateData();
//            deleteData();
//            addData();

            startApp();


        } catch (ClassNotFoundException e) {
            System.out.println("Driver not loaded");
        } catch (SQLException ex) {
            System.out.println("SQL Ex: " + ex.getMessage());
            System.out.println("SQL State: " + ex.getSQLState());
            System.out.println("Vendor error: " + ex.getErrorCode());
        }finally {
            if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { } // ignore
            if (statement != null) try { statement.close(); } catch (SQLException e) { }
            if (connection != null) try { connection.close(); } catch (SQLException e) { }
        }
    }

    private static void readData() throws SQLException {

        resultSet = statement.executeQuery("SELECT * FROM book");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String genre = resultSet.getString("genre");
            String publisher = resultSet.getString("publisher");
            Boolean availability = resultSet.getBoolean("availability");
            System.out.println("id: " + id + " ---Title: " + title + " ---Author: " + author + " ---Genre: " + genre + " ---Publisher: " + publisher + " Availability: " + availability);
        }

        resultSet = statement.executeQuery("SELECT * FROM genre");
        while (resultSet.next()) {
            String genre = resultSet.getString("genre");
            System.out.println("Genre: " + genre);
        }

        resultSet = statement.executeQuery("SELECT * FROM reader");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String address = resultSet.getString("address");
            System.out.println("id: " + id + " ---name: " + name + " ---surname: " + surname + " ---address: " + address);
        }

        resultSet = statement.executeQuery("SELECT title, name, surname FROM book, reader, history WHERE book.id = history.book AND reader.id = history.reader");
        while (resultSet.next()) {
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String title = resultSet.getString("title");
            System.out.println("Title: " + title + " ---name: " + name + " ---surname: " + surname);
        }

        startApp();
    }
    private static void updateData() throws SQLException {
        //update availability
        Scanner input = new Scanner(System.in);
        System.out.println("Input genre, which you wand to update: ");
        String oldGenre = input.next();
        System.out.println("Input new genre: ");
        String newGenre = input.next();


        // PreparedStatements can use variables and are more efficient
//        PreparedStatement ps = connection.prepareStatement("SELECT availability FROM book WHERE id =?");
//        ps.setInt(1, id);
//        resultSet = ps.executeQuery();
//        resultSet.next();
//        Boolean availability = resultSet.getBoolean("availability");

        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE genre SET genre=? WHERE genre=?");
        preparedStatement.setString(1, newGenre);
        preparedStatement.setString(2, oldGenre);


//        System.out.println("availability: " + availability);

//        if (availability) {
//            preparedStatement.setBoolean(1, false);
//        } else {
//            preparedStatement.setBoolean(1, true);
//        }
        preparedStatement.executeUpdate();

//        resultSet = statement.executeQuery("SELECT availability FROM book WHERE id =" + id);
//        resultSet.next();
//        availability = resultSet.getBoolean("availability");
//        System.out.println("availability: " + availability);



        startApp();
    }

    private static void deleteData() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input genre, which you wand to delete: ");
        String genre = input.next();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM genre WHERE genre=?");
        preparedStatement.setString(1, genre);
        preparedStatement.executeUpdate();


        startApp();
    }

    private static void addData() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\nInput new genre: ");
        String newGenre = input.next();
//        System.out.println("Input book id: ");
//        int book_id = Integer.parseInt(input.next());

        CallableStatement callableStatement;
        callableStatement = connection.prepareCall("{CALL insert_genre(?)}");
        callableStatement.setString("new_genre", newGenre);
        ResultSet rs = callableStatement.executeQuery();
        while (rs.next())
        {
            String msg = rs.getString(1);
            // Simply Print the results
            System.out.format("\nResult: "+msg);
        }


        startApp();
    }

    private static void startApp() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("\nHello! \n type: 1, if you want get all data \n 2, if you want to update book availability \n" +
                "3, if you want to delete user \n 4, if you want to insert history \n 0, if you want to quit: ");
        int choose = Integer.parseInt(input.next());

        switch (choose) {
            case 1: readData(); break;
            case 2: updateData(); break;
            case 3: deleteData(); break;
            case 4: addData(); break;
            case 0: break;
            default: startApp();

        }

    }
}
