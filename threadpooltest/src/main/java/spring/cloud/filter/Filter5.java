// package spring.cloud.filter;

// import java.util.Map;
// import java.util.concurrent.TimeUnit;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.cloud.gateway.filter.GatewayFilterChain;
// import org.springframework.cloud.gateway.filter.GlobalFilter;
// import org.springframework.core.Ordered;
// import org.springframework.http.HttpHeaders;
// import org.springframework.stereotype.Component;
// import org.springframework.web.server.ServerWebExchange;


// import lombok.extern.slf4j.Slf4j;
// import reactor.core.publisher.Mono;
// import spring.cloud.Implementation.ServicePacakge;
// import spring.cloud.domain.PoolDto;
// import java.math.*;

// @Component
// @Slf4j
// public class Filter5 implements GlobalFilter, Ordered {


//     @Override
//     public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        
//          //System.out.println("밖에 나왔을때의 위치 스레드" + Thread.currentThread());


//         return chain.filter(exchange);

//     }

//     @Override
//     public int getOrder() {
//         return 6;
//     }


// }