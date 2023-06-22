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
public class Filter5 implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("Filter5 위치입니다." + Thread.currentThread());

//        try {
//            log.info("Filter5에서 Thread Sleep중입니다");
//            Thread.sleep(10000);
//            log.info("timeout 이전에 Pass했습니다");
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        Mono<Void> interrupt = Mono.defer(()->{
                            try {
                                log.info("Filter5에서 Thread Sleep중입니다");
                                Thread.sleep(100000);
                                log.info("thread 끝");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return Mono.empty();
                        }
                ).then();

        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 8;
    }


}