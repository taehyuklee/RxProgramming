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
public class Filter8 implements GlobalFilter, Ordered {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        
        log.info("Filter8 위치입니다." + Thread.currentThread());

        Mono<Void> interrupt = Mono.defer(()->{
            try {
                log.info("thread 시작");
                Thread.sleep(10000000);
                log.info("thread 끝");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Mono.empty();
        }
        ).timeout(Duration.ofSeconds(5), Mono.error(new RuntimeException("Fallback RuntimeException")), Schedulers.boundedElastic())
                    .then();
        /*
        * .onErrorResume(e->{
                            log.info("error: {}" + e.getMessage());
                            return null;})
        */
                    
        Mono<Void> infinite = Mono.defer(()->{
 
                log.info("thread 무한루프 시작");
                boolean a = true;
                while(a){
                    try {
                        Thread.sleep(1000);
                        log.info("hi");
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    
                }
                log.info("thread 무한루프 끝");
               
            return Mono.empty();
        }
        ).subscribeOn(Schedulers.boundedElastic())
            .timeout(Duration.ofSeconds(5), Schedulers.boundedElastic()).onErrorResume(e->{
                    log.info("error: {}" + e.getMessage());
                    return null;}).then();


        Mono<Void> callable = Mono.fromCallable(()->{
 
                log.info("thread 무한루프 시작");
                boolean a = true;
                while(a){
                    try {
                        Thread.sleep(1000);
                        log.info("hi");
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    
                }
                log.info("thread 무한루프 끝");
               
            return Mono.empty();
        }
        ).subscribeOn(Schedulers.boundedElastic())
            .timeout(Duration.ofSeconds(5), Schedulers.boundedElastic()).onErrorResume(e->{
                    log.info("error: {}" + e.getMessage());
                    return null;}).then();


        Mono<Void> anotherThread = Mono.defer(()->{
 
            log.info("스레드 timeout?");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
               
            return Mono.empty();
        }
        ).subscribeOn(Schedulers.boundedElastic())
            .timeout(Duration.ofSeconds(5), Schedulers.boundedElastic()).onErrorResume(e->{
                    log.info("error: {}" + e.getMessage());
                    return null;}).then();




        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 11;
    }


}