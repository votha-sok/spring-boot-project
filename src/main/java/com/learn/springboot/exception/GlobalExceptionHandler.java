package com.learn.springboot.exception;

import com.learn.springboot.exception.model.Response;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Object>> handleAllExceptions(Exception ex, HttpServletRequest request) {
        String traceId = UUID.randomUUID().toString(); // generate trace id
        Response<Object> response = new Response<>(
                1,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                null,
                ex.getMessage(),
                traceId,
                request.getRequestURI()
        );
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response<Object>> handleNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        String traceId = UUID.randomUUID().toString();
        Response<Object> response = new Response<>(
                1,
                HttpStatus.NOT_FOUND.value(),
                null,
                ex.getMessage(),
                traceId,
                request.getRequestURI()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Response<Object>> handleUnauthorized(
            UnauthorizedException ex, HttpServletRequest request) {

        String traceId = UUID.randomUUID().toString();

        Response<Object> response = new Response<>(
                1,  // custom code for error
                HttpStatus.UNAUTHORIZED.value(),
                null,
                ex.getMessage(),
                traceId,
                request.getRequestURI()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Response<Object>> handleAccessDenied(AccessDeniedException ex, HttpServletRequest request) {
        String traceId = UUID.randomUUID().toString();
        Response<Object> resp = new Response<>(1, 403, null, "Access Denied", traceId, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(resp);
    }
}
