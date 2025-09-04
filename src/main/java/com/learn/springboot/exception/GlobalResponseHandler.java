package com.learn.springboot.exception;

import com.learn.springboot.exception.model.Response;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.UUID;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    private final HttpServletRequest request;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // Apply to all controllers
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class selectedConverterType,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {

        // If already a Response object (like exceptions), just return it
        if (body instanceof Response) {
            return body;
        }

        // Wrap normal responses
        String traceId = UUID.randomUUID().toString();

        return new Response<>(
                0, // success code
                200, // HTTP status
                body, // actual response
                "Success",
                traceId,
                request.getRequestURI()
        );
    }
}
