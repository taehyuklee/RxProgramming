package spring.cloud.filter;

import java.time.Duration;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Component
@Slf4j
public class Filter1 implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("Filter1 위치입니다." + Thread.currentThread());
//.subscribeOn(Schedulers.boundedElastic())
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 4;
    }


}