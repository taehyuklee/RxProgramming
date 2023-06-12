package com.manage.reactive.apis.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import reactor.core.publisher.Mono;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class Response<V> {

    public static Mono<String> responseOk = Mono.just("Successs");

    private int code;

    private String returnMessage;

    private String errorMessage;

    private V data;


    //ResponseOk for Insert
    public Mono<Response<V>> responseOk(){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.SUCCESS.getCode())
                    .setReturnMessage(StatusEnums.SUCCESS.getMessage()));
    }

    //ResponseOk for Read
    public Mono<Response<V>> responseOk(V value){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.SUCCESS.getCode())
                    .setReturnMessage(StatusEnums.SUCCESS.getMessage())
                    .setData(value));
    }

    //ResponseOk for Read
    public Mono<Response<V>> conflictError(){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.CONFLICT.getCode())
                    .setReturnMessage(StatusEnums.CONFLICT.getMessage()));
    }

    //ResponseError - internal Server error (500)
    public Mono<Response<V>> internalServerError(){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.INTERNAL_SERVER_ERROR.getCode())
                    .setErrorMessage(StatusEnums.INTERNAL_SERVER_ERROR.getMessage()));
    }


    //ResponseError - missing(Required Value) (0000)
    public Mono<Response<V>> wrontType(String resource){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.WRONG_TYPE.getCode())
                    .setErrorMessage(insertParsing(StatusEnums.WRONG_TYPE.getMessage(), resource)));
    }
    

    //ResponseError - missing(Required Value) (0001)
    public Mono<Response<V>> missingRequiredError(String resource){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.MISSING_REQUIRED.getCode())
                    .setErrorMessage(insertParsing(StatusEnums.MISSING_REQUIRED.getMessage(), resource)));
    }

    //ResponseError - missing(Required Value) (0002)
    public Mono<Response<V>> outOfRangeError(String resource){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.OUT_OF_RANGE_SIZE.getCode())
                    .setErrorMessage(insertParsing(StatusEnums.OUT_OF_RANGE_SIZE.getMessage(), resource)));
    }

    //ResponseError - (duplicated resource error) (0003)
    public Mono<Response<V>> duplicatedError(String resource){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.ALREADY_EXIST.getCode())
                    .setErrorMessage(insertParsing(StatusEnums.ALREADY_EXIST.getMessage(), resource)));
    }

    //ResponseError - (violate pattern resource error) (0004)
    public Mono<Response<V>> violatePatternError(String resource){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.VIOLATE_PATTERN.getCode())
                    .setErrorMessage(insertParsing(StatusEnums.VIOLATE_PATTERN.getMessage(), resource)));
    }

    //ResponseError - (using resource error) (0005)
    public Mono<Response<V>> usingError(String resource){
        return Mono.just(new Response<V>()
                    .setCode(StatusEnums.IN_USE_ERROR.getCode())
                    .setErrorMessage(insertParsing(StatusEnums.IN_USE_ERROR.getMessage(), resource)));
    }



    public String insertParsing(String returnMessage, String resource){
        return returnMessage.replace("{}", resource);
    }

}
