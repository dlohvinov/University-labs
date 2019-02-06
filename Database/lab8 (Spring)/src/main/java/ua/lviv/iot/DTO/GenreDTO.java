package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.controller.BookController;
import ua.lviv.iot.domain.Book;
import ua.lviv.iot.domain.Genre;
import ua.lviv.iot.exceptions.NoSuchBookException;
import ua.lviv.iot.exceptions.NoSuchGenreException;
import ua.lviv.iot.exceptions.NoSuchReaderException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class GenreDTO extends ResourceSupport {

    Genre genre;

    public GenreDTO(Genre genre, Link selfLink)
            throws NoSuchReaderException, NoSuchBookException, NoSuchGenreException {
        this.genre = genre;
        add(selfLink);
        add(linkTo(methodOn(BookController.class).getBooksByGenre(genre.getGenre())).withRel("books"));
    }

    public String getGenreName() {
        return genre.getGenre();
    }

    public List<Integer> getBooks() {
        List<Integer> bookList = new ArrayList<>();
        for (Book book: genre.getBookByGenre()) {
            bookList.add(book.getBookId());
        }
        return bookList;
    }
}
