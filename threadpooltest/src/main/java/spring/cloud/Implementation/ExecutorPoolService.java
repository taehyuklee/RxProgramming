package spring.cloud.Implementation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.scheduler.Scheduler;
import spring.cloud.config.threadPool.JavaThreadPoolFactory;

@Component
@Slf4j
public class ExecutorPoolService {

    @Autowired
    private JavaThreadPoolFactory javaThreadPoolFactory;


    public Scheduler getExecutor(String queueType) throws Exception {

        if(queueType.equals("LinkedQ")){
            //Blocking Queue
            log.info("LinkedQ 입니다.");
            //return Schedulers.fromExecutorService(javaThreadPoolFactory.linkedQPool);

            return javaThreadPoolFactory.linkedQpoolSchedule;

            

        }else if(queueType.equals("ArraysQ")){
            // Array Queue
            log.info("ArraysQ 입니다.");
            //return Schedulers.fromExecutorService(javaThreadPoolFactory.arraysQPool);

            return javaThreadPoolFactory.arraysQpoolSchedule;


        }else{
            log.info("Executor 스레드풀에서 Queue 이름이 잘못되었습니다");
            return null;
        }

    }


    // threadPool를 종료하고 모든 작업이 끝날 때까지 기다린다.
    private void shutDownAndWaitUntilTerminated(ExecutorService executorService) {
        try {
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
