package spring.cloud.config.threadPool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class SpringThreadPool {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); //코어 스레드 개수
        executor.setMaxPoolSize(5); //최대 스레드 개수
        executor.setQueueCapacity(100); //큐 용량
        executor.setThreadNamePrefix("my-executor-"); // 
        executor.setRejectedExecutionHandler(null);
        executor.setKeepAliveSeconds(3000);
        executor.initialize();
        return executor;
    }
    
}
