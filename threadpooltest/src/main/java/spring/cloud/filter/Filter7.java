package spring.cloud.filter;

import java.time.Duration;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Component
@Slf4j
public class Filter7 implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        
        log.info("Filter7 위치입니다." + Thread.currentThread());


        Mono<Void> anotherThread = Mono.fromCallable(()->{

            log.info("다른 스레드를 구독했습니다.");
 
            log.info("스레드 timeout?");
            Thread.sleep(10000);
               
            log.info("끝");
            return Mono.empty();
        }
        ).subscribeOn(Schedulers.boundedElastic()).then();

        //        anotherThread.subscribe();

        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 10;
    }


}