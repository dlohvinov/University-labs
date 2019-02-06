package ua.lviv.iot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.DTO.GenreDTO;
import ua.lviv.iot.DTO.LoggerDTO;
import ua.lviv.iot.domain.Genre;
import ua.lviv.iot.domain.Logger;
import ua.lviv.iot.exceptions.NoSuchBookException;
import ua.lviv.iot.exceptions.NoSuchGenreException;
import ua.lviv.iot.exceptions.NoSuchReaderException;
import ua.lviv.iot.service.GenreService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping(value = "api/genre")
    public ResponseEntity<List<GenreDTO>> getAllGenres() throws NoSuchGenreException, NoSuchReaderException, NoSuchBookException {
        List<Genre> genres = genreService.getAllGenres();
        Link link = linkTo(methodOn(GenreController.class).getAllGenres()).withSelfRel();

        List<GenreDTO> genresDTO = new ArrayList<>();
        for (Genre genre: genres) {
            Link selfLink = new Link(link.getHref() + "/" + genre.getGenre()).withSelfRel();
            GenreDTO dto = new GenreDTO(genre, selfLink);
            genresDTO.add(dto);
        }

        return new ResponseEntity<>(genresDTO, HttpStatus.OK);
    }
}
