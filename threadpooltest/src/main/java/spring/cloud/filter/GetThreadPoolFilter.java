package spring.cloud.filter;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import spring.cloud.Implementation.ServicePacakge;
import spring.cloud.domain.PoolDto;

@Component
@Slf4j
public class GetThreadPoolFilter implements GlobalFilter, Ordered {

    @Autowired
    private ServicePacakge servicePacakge;

    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        /* GlobalFilter1 : 미리 만들어져 있는 스레드풀을 선택해서 Operation filter로 보낸다.
         * poolType: Executor Java threadPool인지, Schedulers threadPool인지 ("Executors", "Scheduler")
         * sPoolType : Schedulers에서 bound인지 parallel인지 알려준다 ("bound", "paral")
         * queueType : Executor threadPool일 경우 queue 선정해줘야 한다 ("LinkedQ", "ArraysQ")
         */

        //System.out.println("GlobalFilter1 - Thread   " + Thread.currentThread());

        //System.out.println("범인은 : " + (exchange.getRequest().getRemoteAddress()));

        Scheduler threadPool = null;

        PoolDto poolDto = setPoolDto(exchange);

        //poolDto를 다음 필터에 넣어준다.
        exchange.getAttributes().put("poolDto",poolDto);

        try {
            //header에 따라서 설정이 달라진다.
            threadPool = servicePacakge.getThreadPoolPacakgeTest(poolDto);

        } catch (Exception e) {

            e.printStackTrace();
            log.info("시스템에 문제가 발생했습니다. (최외각 Exception에 걸렸습니다)");
        }
        
        //예외처리
        if(threadPool == null){
            log.info("스레드풀을 제대로 선택하지 않았습니다.");
            return Mono.empty();
        }
        // System.out.println("GlobalFilter1 - Thread   " + Thread.currentThread());

        return chain.filter(exchange).subscribeOn(threadPool);
    }



    private PoolDto setPoolDto(ServerWebExchange exchange){

        HttpHeaders headers = exchange.getRequest().getHeaders();

        PoolDto poolDto = new PoolDto();

        //예외 처리.
        if(headers.getFirst("blockTime") == null){
            poolDto.setBlockTime(0);
        }else{
            poolDto.setBlockTime( Integer.parseInt(headers.getFirst("blockTime")));
        }

        if(headers.getFirst("N") == null){
            poolDto.setN(0);
        }else{
            poolDto.setN( Integer.parseInt(headers.getFirst("N")));
        }

        if(headers.getFirst("poolType") == null){
            poolDto.setPoolType("");
        }else{
            poolDto.setPoolType(String.valueOf(headers.getFirst("poolType")));
        }

        if(headers.getFirst("queueType") == null){
            poolDto.setQueueType("");
        }else{
            poolDto.setQueueType(String.valueOf(headers.getFirst("queueType")));
        }   

        if(headers.getFirst("sPoolType") == null){
            poolDto.setSPoolType("");
        }else{
            poolDto.setSPoolType(String.valueOf(headers.getFirst("sPoolType")));
        }
             

        //null로 들어올때는 int=0으로 , String은 ""으로 처리. (Exception처리)
        //exceptionTrt(headers);
        return poolDto;
    }

    @Override
    public int getOrder() {
        return 2;
    }



}