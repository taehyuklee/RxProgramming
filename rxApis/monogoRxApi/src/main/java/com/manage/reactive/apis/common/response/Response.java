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
        return Mono.just(new Response<V>().setCode(StatusEnums.SUCCESS.getCode())
                    .setReturnMessage(StatusEnums.SUCCESS.getMessage()));
    }

    //ResponseOk for Read
    public Mono<Response<V>> responseOk(V value){
        return Mono.just(new Response<V>().setCode(StatusEnums.SUCCESS.getCode())
                    .setReturnMessage(StatusEnums.SUCCESS.getMessage())
                    .setData(value));
    }

    //ResponseOk for Read
    public Mono<Response<V>> conflictError(){
        return Mono.just(new Response<V>().setCode(StatusEnums.CONFLICT.getCode())
                    .setReturnMessage(StatusEnums.CONFLICT.getMessage()));
    }

}
