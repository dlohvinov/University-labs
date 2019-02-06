package ua.lviv.iot.DTO;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import ua.lviv.iot.controller.ReaderController;
import ua.lviv.iot.domain.Book;
import ua.lviv.iot.domain.Reader;
import ua.lviv.iot.exceptions.NoSuchBookException;
import ua.lviv.iot.exceptions.NoSuchGenreException;
import ua.lviv.iot.exceptions.NoSuchReaderException;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class BookDTO extends ResourceSupport {

    Book book;

    public BookDTO(Book book, Link selflink)
            throws NoSuchReaderException, NoSuchBookException, NoSuchGenreException {
        this.book = book;
        add(selflink);
        add(linkTo(methodOn(ReaderController.class).getReadersByBookId(book.getBookId())).withRel("readers"));
    }

    public Integer getBookId() {
        return book.getBookId();
    }

    public String getBookAuthor() {
        return book.getBookAuthor();
    }

    public String getBookGenre() {
        return book.getGenre().getGenre();
    }

    public Boolean getBookAvailability() {
        return book.getBookAvailability();
    }

    public String getBookPublisher() {
        return book.getBookPublisher();
    }

    public String getBookTitle() {
        return book.getBookTitle();
    }

    public List<Integer> getBookReaders() {
        List<Integer> readerList = new ArrayList<>();
        for (Reader reader: book.getReaders()) {
            readerList.add(reader.getReaderId());
        }
        return  readerList;
    }
}
