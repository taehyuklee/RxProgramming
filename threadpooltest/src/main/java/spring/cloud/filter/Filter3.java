package spring.cloud.filter;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import spring.cloud.Implementation.SchedulersService;

@Component
@Slf4j
public class Filter3 implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        log.info("Filter3 위치입니다." + Thread.currentThread());

        Scheduler schedulers = Schedulers.newBoundedElastic(100, 100, "timer-Thread", 60, false);


        return chain.filter(exchange).onErrorResume(e->{
            log.info("exception:{}", e);
            return null;
        });


    }

    @Override
    public int getOrder() {
        return 6;
    }


}