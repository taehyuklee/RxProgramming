package spring.cloud.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@Component
@Slf4j
public class Filter4 implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("Filter4 위치입니다" + Thread.currentThread());
        //.timeout(Duration.ofSeconds(5), Mono.error(new RuntimeException("걸림")))
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 7;
    }


}