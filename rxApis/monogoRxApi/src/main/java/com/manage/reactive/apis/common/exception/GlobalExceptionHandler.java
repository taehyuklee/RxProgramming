package com.manage.reactive.apis.common.exception;

import reactor.core.publisher.Mono;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.manage.reactive.apis.common.response.Response;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFormatException.class)
    public Mono<Response<Void>> invalidException(InvalidFormatException exception, ServerHttpRequest serverHttpRequest){
        return new Response<Void>().wrontType(exception.getPath().get(0).getFieldName());
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<Response<Void>> methodValidException(WebExchangeBindException exception, ServerHttpRequest serverHttpRequest){
        return makeErrorResponse(exception.getBindingResult());

    }

    private Mono<Response<Void>> makeErrorResponse(BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            String bindResultCode = bindingResult.getFieldError().getCode();
            String targetField = bindingResult.getFieldError().getField();

            switch (bindResultCode){
                case "NotNull":
                    return new Response<Void>().missingRequiredError(targetField);
                case "Size":
                    return new Response<Void>().outOfRangeError(targetField);
                case "NotEmpty":
                    return new Response<Void>().missingRequiredError(targetField);
                case "Pattern":
                    return new Response<Void>().violatePatternError(targetField);
            }
        }
        return new Response<Void>().internalServerError();
    }

}
