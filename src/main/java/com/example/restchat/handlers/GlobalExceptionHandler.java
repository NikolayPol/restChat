package com.example.restchat.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * Класс GlobalExceptionHandler - во всех контроллерах добавлена
 * валидация(проверки на null) входных данных.
 * Исключения, выбрасываемые в ходе валидации, обрабатываются в классе,
 * помеченным как @ControllerAdvise.
 *
 * Здесь аннотация @ControllerAdvise
 * используется совместно с @ExceptionHandler.
 * Код ниже обрабатывает все исключения NullPointerException,
 * которые возникают во всех контроллерах.
 *
 * @author Nikolay Polegaev
 * @version 1.0 06.01.2022
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(GlobalExceptionHandler.class.getSimpleName());

    private final ObjectMapper objectMapper;

    public GlobalExceptionHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(value = {NullPointerException.class})
    public void handleException(Exception e, HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(new HashMap<>() { {
            put("message", "Some of fields empty");
            put("details", e.getMessage());
        }}));
        LOGGER.error(e.getMessage());
    }
}