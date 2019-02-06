package ua.lviv.iot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.DTO.ReaderDTO;
import ua.lviv.iot.domain.Reader;
import ua.lviv.iot.exceptions.ExistsBookForReader;
import ua.lviv.iot.exceptions.NoSuchBookException;
import ua.lviv.iot.exceptions.NoSuchGenreException;
import ua.lviv.iot.exceptions.NoSuchReaderException;
import ua.lviv.iot.service.ReaderService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class ReaderController {

    @Autowired
    ReaderService readerService;

    @GetMapping(value = "/api/reader")
    public ResponseEntity<List<ReaderDTO>> getAllReaders()
            throws NoSuchBookException, NoSuchGenreException, NoSuchReaderException {
        List<Reader> readerList = readerService.getAllReaders();
        Link link = linkTo(methodOn(ReaderController.class).getAllReaders()).withSelfRel();

        List<ReaderDTO> readersDTO = new ArrayList<>();
        for (Reader reader: readerList) {
            Link selfLink = new Link(link.getHref() + '/' + reader.getReaderId()).withSelfRel();
            ReaderDTO readerDTO = new ReaderDTO(reader, selfLink);
            readersDTO.add(readerDTO);
        }

        return new ResponseEntity<>(readersDTO, HttpStatus.OK);
    }

    @GetMapping(value = "api/reader/{readerId}")
    public ResponseEntity<ReaderDTO> getReader(@PathVariable Integer readerId)
            throws NoSuchReaderException, NoSuchBookException, NoSuchGenreException {
        Reader reader = readerService.getReader(readerId);

        Link link = linkTo(methodOn(ReaderController.class).getReader(readerId)).withSelfRel();

        ReaderDTO readerDTO = new ReaderDTO(reader, link);

        return new ResponseEntity<>(readerDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/reader/book/{bookId}")
    public ResponseEntity<List<ReaderDTO>> getReadersByBookId(@PathVariable Integer bookId)
            throws NoSuchBookException, NoSuchGenreException, NoSuchReaderException {
        List<Reader> readerList = readerService.getReadersByBookId(bookId);
        Link link = linkTo(methodOn(ReaderController.class).getAllReaders()).withSelfRel();

        List<ReaderDTO> readersDTO = new ArrayList<>();
        for (Reader entity : readerList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getReaderId()).withSelfRel();
            ReaderDTO dto = new ReaderDTO(entity, selfLink);
            readersDTO.add(dto);
        }

        return new ResponseEntity<>(readersDTO, HttpStatus.OK);
    }

    @PostMapping(value = "api/reader/book/{bookId}")
    public ResponseEntity<String> createReader(@RequestBody Reader newReader, @PathVariable Integer bookId)
            throws NoSuchBookException {
        readerService.createReader(newReader, bookId);

        return new ResponseEntity<>("Reader Created", HttpStatus.CREATED);
    }

    @PutMapping(value = "api/reader/{oldReaderId}/book/{bookId}")
    public ResponseEntity<String> updateReader(@RequestBody Reader newReader,
                                               @PathVariable Integer oldReaderId,
                                               @PathVariable Integer bookId)
            throws NoSuchReaderException, NoSuchBookException {

        readerService.updateReader(newReader, oldReaderId, bookId);

        return new ResponseEntity<>("Reader Updated", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "api/reader/{readerId}")
    public ResponseEntity<String> deleteReader(@PathVariable Integer readerId)
            throws NoSuchReaderException {
        readerService.deleteReader(readerId);

        return new ResponseEntity<>("Reader Deleted", HttpStatus.OK);
    }

    @PostMapping(value = "api/reader/{readerId}/book/{bookId}")
    public ResponseEntity<String> addBookForReader(@PathVariable Integer readerId,
                                                   @PathVariable Integer bookId)
            throws NoSuchReaderException, NoSuchBookException, ExistsBookForReader {

        readerService.addBookForReader(readerId, bookId);

        return new ResponseEntity<>("Book Added", HttpStatus.OK);
    }
}
