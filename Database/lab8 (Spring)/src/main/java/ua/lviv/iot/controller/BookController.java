package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.DTO.BookDTO;
import ua.lviv.iot.domain.Book;
import ua.lviv.iot.exceptions.NoSuchBookException;
import ua.lviv.iot.exceptions.NoSuchGenreException;
import ua.lviv.iot.exceptions.NoSuchReaderException;
import ua.lviv.iot.service.BookService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping(value = "/api/book")
    public ResponseEntity<List<BookDTO>> getAllBooks()
            throws NoSuchBookException, NoSuchReaderException, NoSuchGenreException {
        List<Book> clubList = bookService.getAllBooks();
        Link link = linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel();

        List<BookDTO> clubsDTO = new ArrayList<>();
        for (Book entity : clubList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getBookId()).withSelfRel();
            BookDTO dto = new BookDTO(entity, selfLink);
            clubsDTO.add(dto);
        }

        return new ResponseEntity<>(clubsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "api/book/reader/{readerId}")
    public ResponseEntity<List<BookDTO>> getBooksByReaderId(@PathVariable Integer readerId)
            throws NoSuchBookException, NoSuchReaderException, NoSuchGenreException {
        List<Book> bookList = bookService.getBooksByReaderId(readerId);
        Link link = linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel();

        List<BookDTO> booksDTO = new ArrayList<>();
        for (Book book: bookList) {
            Link selfLink = new Link(link.getHref() + "/" + book.getBookId()).withSelfRel();
            BookDTO bookDTO = new BookDTO(book, selfLink);
            booksDTO.add(bookDTO);
        }

        return new ResponseEntity<>(booksDTO, HttpStatus.OK);
    }

    @GetMapping(value = "api/book/genre/{genre}")
    public ResponseEntity<List<BookDTO>> getBooksByGenre(@PathVariable String genre)
            throws NoSuchBookException, NoSuchReaderException, NoSuchGenreException {
        Collection<Book> bookList = bookService.getBooksByGenre(genre);
        Link link = linkTo(methodOn(BookController.class).getAllBooks()).withSelfRel();

        List<BookDTO> booksDTO = new ArrayList<>();
        for (Book book: bookList) {
            Link selfLink = new Link(link.getHref() + "/" + book.getBookId()).withSelfRel();
            BookDTO bookDTO = new BookDTO(book, selfLink);
            booksDTO.add(bookDTO);
        }

        return new ResponseEntity<>(booksDTO, HttpStatus.OK);
    }

}
