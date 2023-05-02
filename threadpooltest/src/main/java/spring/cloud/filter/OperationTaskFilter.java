package spring.cloud.filter;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import spring.cloud.Implementation.ServicePacakge;
import spring.cloud.domain.PoolDto;
import java.math.*;

@Component
@Slf4j
public class OperationTaskFilter implements GlobalFilter, Ordered {

     
    //I/O-bound 작업.
    private void IOTask(int time) {
        try {
            Thread.sleep(time);
            // System.out.println("time-sleep");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //CPU-bound 작업. (3중 forLoop O(n^3))
    private double cpuTask(int n){

        //System.out.println("지금의 스레드" + Thread.currentThread());
        double sum = 0;
        
        for(int i=0; i<n; i++){
            Double random = Math.random(); 
            sum += random;
        }
        // System.out.println(sum);
        return sum;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //System.out.println("GlobalFilter2 - Thread   " + Thread.currentThread());
        
        //이전에 작업해놨던 poolDto를 가져온다.
        PoolDto poolDto = (PoolDto) exchange.getAttribute("poolDto");
        
        //I/O 작업
            if(poolDto.getN()==0){ 
            //System.out.println("I/O Task입니다");
            IOTask(poolDto.getBlockTime());
        }
            else if(poolDto.getN()>0){ 
            //System.out.println("cpu Task입니다");
            cpuTask(poolDto.getN());
            }else{
            //아무것도 없을때
            //System.out.println("아무것도 안해요");

            }

            System.out.println("밖에 나왔을때의 위치 스레드 1" + Thread.currentThread());
            //return Mono.empty();

       return chain.filter(exchange);
        

    }

    @Override
    public int getOrder() {
        return 3;
    }


}