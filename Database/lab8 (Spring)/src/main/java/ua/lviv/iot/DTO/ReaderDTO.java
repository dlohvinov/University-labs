package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.controller.BookController;
import ua.lviv.iot.domain.Book;
import ua.lviv.iot.domain.Reader;
import ua.lviv.iot.exceptions.NoSuchBookException;
import ua.lviv.iot.exceptions.NoSuchGenreException;
import ua.lviv.iot.exceptions.NoSuchReaderException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ReaderDTO extends ResourceSupport {

    Reader reader;

    public ReaderDTO(Reader reader, Link selfLink)
            throws NoSuchReaderException, NoSuchBookException, NoSuchGenreException {

        this.reader = reader;
        add(selfLink);
        add(linkTo(methodOn(BookController.class).getBooksByReaderId(reader.getReaderId())).withRel("books"));
    }

    public String getReaderName() {
        return reader.getReaderName();
    }

    public String getReaderSurname() {
        return reader.getReaderSurname();
    }

    public String getReaderAddress() {
        return reader.getReaderAddress();
    }

    public Integer getReaderId() {
        return reader.getReaderId();
    }

    public List<Integer> getReaderBooks() {
        List<Integer> bookList = new ArrayList<>();
        for (Book book: reader.getBooks()) {
            bookList.add(book.getBookId());
        }

        return bookList;
    }
}
