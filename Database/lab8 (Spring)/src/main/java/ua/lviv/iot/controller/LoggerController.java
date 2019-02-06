package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.DTO.LoggerDTO;
import ua.lviv.iot.domain.Logger;
import ua.lviv.iot.service.LoggerService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class LoggerController {

    @Autowired
    LoggerService loggerService;

    @GetMapping(value = "api/logger")
    public ResponseEntity<List<LoggerDTO>> getAllLogs() {
        List<Logger> loggers = loggerService.getAllLogs();
        Link link = linkTo(methodOn(LoggerController.class).getAllLogs()).withSelfRel();

        List<LoggerDTO> loggersDTO = new ArrayList<>();
        for (Logger logger: loggers) {
            Link selfLink = new Link(link.getHref() + "/" + logger.getId()).withSelfRel();
            LoggerDTO dto = new LoggerDTO(logger, selfLink);
            loggersDTO.add(dto);
        }

        return new ResponseEntity<>(loggersDTO, HttpStatus.OK);
    }

}
