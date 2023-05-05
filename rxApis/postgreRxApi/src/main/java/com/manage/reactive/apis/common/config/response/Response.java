package com.manage.reactive.apis.common.config.response;

import reactor.core.publisher.Mono;

public class Response {

    public static Mono<String> responseOk = Mono.just("Successs");

}
