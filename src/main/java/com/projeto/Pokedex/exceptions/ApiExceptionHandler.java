package com.projeto.Pokedex.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        List<ResponseMessage.Campo> campos = new ArrayList<>();

        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            String nome = ((FieldError) error).getField();
            String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            campos.add(new ResponseMessage.Campo(nome, mensagem));
        }

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(status.value());
        responseMessage.setDatahora(OffsetDateTime.now());
        responseMessage.setTitulo("Um ou mais campos preenchidos incorretamente");
        responseMessage.setCampos(campos);

        return handleExceptionInternal(ex, responseMessage, headers, status, request);
    }

    @ExceptionHandler(PokedexException.class)
    public ResponseEntity<Object> handlePokedex(PokedexException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(status.value());
        responseMessage.setDatahora(OffsetDateTime.now());
        responseMessage.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, responseMessage, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handlePokemon(NotFoundException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setStatus(status.value());
        responseMessage.setDatahora(OffsetDateTime.now());
        responseMessage.setTitulo(ex.getMessage());

        return handleExceptionInternal(ex, responseMessage, new HttpHeaders(), status, request);
    }
}
