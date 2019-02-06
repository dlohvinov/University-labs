package ua.lviv.iot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.Book;
import ua.lviv.iot.domain.Genre;
import ua.lviv.iot.domain.Reader;
import ua.lviv.iot.exceptions.NoSuchGenreException;
import ua.lviv.iot.exceptions.NoSuchReaderException;
import ua.lviv.iot.repository.BookRepository;
import ua.lviv.iot.repository.GenreRepository;
import ua.lviv.iot.repository.ReaderRepository;

import java.util.Collection;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    GenreRepository genreRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByReaderId(Integer readerId) throws NoSuchReaderException {
        Reader reader = readerRepository.findById(readerId).get();
        if (reader == null) throw new NoSuchReaderException();
        return reader.getBooks();
    }

    public Collection<Book> getBooksByGenre(String genreName) throws NoSuchGenreException {
        Genre genre = genreRepository.findById(genreName).get();
        if (genre == null) throw new NoSuchGenreException();
        return genre.getBookByGenre();
    }
}
