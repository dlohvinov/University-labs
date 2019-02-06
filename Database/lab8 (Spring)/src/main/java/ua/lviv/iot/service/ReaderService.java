package ua.lviv.iot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.domain.Book;
import ua.lviv.iot.domain.Logger;
import ua.lviv.iot.domain.Reader;
import ua.lviv.iot.exceptions.ExistsBookForReader;
import ua.lviv.iot.exceptions.NoSuchBookException;
import ua.lviv.iot.exceptions.NoSuchReaderException;
import ua.lviv.iot.repository.BookRepository;
import ua.lviv.iot.repository.LoggerRepository;
import ua.lviv.iot.repository.ReaderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    @Autowired
    ReaderRepository readerRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LoggerRepository loggerRepository;

    public List<Reader> getReadersByBookId(Integer bookId) throws NoSuchBookException{
        Book book = bookRepository.findById(bookId).get();
        if (book == null) throw new NoSuchBookException();
        return book.getReaders();
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Reader getReader(Integer readerId) throws NoSuchReaderException {
        Reader reader;
        if (readerRepository.findById(readerId).isPresent()) {
            reader = readerRepository.findById(readerId).get();
        } else throw new NoSuchReaderException();

        return reader;
    }

    @Transactional
    public void createReader(Reader newReader, Integer bookId)
            throws NoSuchBookException {
        Book book;
        if (bookRepository.findById(bookId).isPresent()) {
            book = bookRepository.findById(bookId).get();
            newReader.getBooks().add(book);
        } else throw new NoSuchBookException();
        readerRepository.save(newReader);
    }

    @Transactional
    public void updateReader(Reader newReader, Integer oldReaderId, Integer bookId)
            throws NoSuchReaderException, NoSuchBookException {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        Book book;
        if (bookOptional.isPresent()) {
            book = bookOptional.get();
        } else throw new NoSuchBookException();

        Optional<Reader> readerOptional = readerRepository.findById(oldReaderId);
        Reader reader;
        if (readerOptional.isPresent()) {
            reader = readerOptional.get();
            reader.setReaderAddress(newReader.getReaderAddress());
            reader.setReaderName(newReader.getReaderName());
            reader.setReaderName(newReader.getReaderSurname());
            reader.getBooks().add(book);
        } else throw new NoSuchReaderException();

        readerRepository.save(reader);
    }

    @Transactional
    public void deleteReader(Integer readerId) throws NoSuchReaderException {
        Optional<Reader> readerOptional = readerRepository.findById(readerId);
        Reader reader;
        if (readerOptional.isPresent()) {
            reader = readerOptional.get();
        } else throw new NoSuchReaderException();

        readerRepository.delete(reader);
    }

    @Transactional
    public void addBookForReader(Integer readerId, Integer bookId)
            throws NoSuchBookException, NoSuchReaderException, ExistsBookForReader {
        Optional<Reader> readerOptional = readerRepository.findById(readerId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        Reader reader;
        Book book;

        if (readerOptional.isPresent()) {
            reader = readerOptional.get();
        } else throw new NoSuchReaderException();

        if (bookOptional.isPresent()) {
            book = bookOptional.get();
        } else throw new NoSuchBookException();

        if (reader.getBooks().contains(book)) throw new ExistsBookForReader();

        Logger logger = new Logger();
        logger.setBook(bookId);
        logger.setReader(readerId);

        loggerRepository.save(logger);

    }
}
