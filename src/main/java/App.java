
import DAO.MovieDAO;
import Model.Movie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws SQLException{

        System.out.println("Escolha uma opção: ");
        System.out.println("1 - Listar os filmes");
        System.out.println("2 - Inserir novo filme");
        System.out.println("3 - Editar filme");
        System.out.println("4 - Remover filme");

        Scanner input = new Scanner(System.in);
        MovieDAO movieDAO = new MovieDAO();
        Movie movie = null;
        Integer movieId;
        ArrayList<Movie> movies;

        try{
            Integer option = input.nextInt();
            switch (option){
                case 1:
                    listMovies();
                    break;

                case 2:
                    System.out.println("Qual o nome do filme?");
                    Scanner scanner = new Scanner(System.in);
                    String movieName = scanner.nextLine();
                    scanner.close();
                    movie = new Movie(movieName);
                    movieDAO.insert(movie);
                    break;

                case 3:
                    movies = listMovies();
                    System.out.println("Digite o código do filme que quer editar");
                    scanner = new Scanner(System.in);
                    movieId = scanner.nextInt();
                    movie = findMovieById(movieId, movies);
                    if (movie != null) {
                        System.out.println("Digite o novo nome do filme: ");
                        Scanner newMovie = new Scanner(System.in);
                        String newMovieName = null;
                        if(newMovie.hasNextLine())
                            newMovieName = newMovie.nextLine();
                        movie.setName(newMovieName);
                        movieDAO.update(movie);
                    } else {
                        System.out.println("Filme não encontrado");
                    }
                    break;

                case 4:
                    movies = listMovies();
                    System.out.println("Insira o código do filme que deseja excluir");
                    scanner = new Scanner(System.in);
                    movieId = scanner.nextInt();
                    for (Movie m : movies) {
                        if (m.getId() == movieId){
                            movieDAO = new MovieDAO();
                            movieDAO.remove(m);
                            break;
                        }
                    }
                    break;
            }
        }catch(InputMismatchException exception){
            System.err.println("Parâmetro inválido");
        }
    }

    public static ArrayList<Movie> listMovies() throws SQLException {
        MovieDAO movieDAO = new MovieDAO();
        ArrayList<Movie> movies = movieDAO.listMovies();
        for (Movie movie: movies) {
            System.out.println("Id: " + movie.getId());
            System.out.println("Nome: " + movie.getName());
            System.out.println("**************");
        }
        return movies;
    }

    public static Movie findMovieById(Integer id, List<Movie> movie){
        for (Movie m : movie) {
            if (m.getId() == id){
                return m;
            }
        }
        return null;
    }

}
