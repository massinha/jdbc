package DAO;

import Database.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Model.Movie;

public class MovieDAO {

    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public MovieDAO() throws SQLException {
        this.connection = ConnectionFactory.getConnection();
    }

    public ArrayList<Movie> listMovies() throws SQLException {
        String query = "SELECT id, name FROM movie";
        ArrayList<Movie> movies = new ArrayList<Movie>();
        preparedStatement = connection.prepareStatement(query);
        resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            Movie movie = new Movie(resultSet.getInt("id"), resultSet.getString("name"));
            movies.add(movie);
        }
        return movies;
    }

    public boolean insert(Movie movie) throws SQLException {
        String query = "INSERT INTO movie VALUES (DEFAULT, ?)";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, movie.getName());
        return preparedStatement.execute();
    }

    public boolean update(Movie movie) throws SQLException{
        String query = "UPDATE movie SET name = ? WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, movie.getName());
        preparedStatement.setInt(2, movie.getId());
        return preparedStatement.execute();
    }

    public boolean remove(Movie movie) throws SQLException {
        String query = "DELETE FROM movie WHERE id = ?";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,movie.getId());
        return preparedStatement.execute();
    }



}
