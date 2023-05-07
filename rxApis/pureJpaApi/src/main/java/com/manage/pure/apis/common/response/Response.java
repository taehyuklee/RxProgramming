package com.manage.pure.apis.common.response;

import reactor.core.publisher.Mono;

public class Response {

    public static Mono<String> responseOk = Mono.just("Successs");

}
