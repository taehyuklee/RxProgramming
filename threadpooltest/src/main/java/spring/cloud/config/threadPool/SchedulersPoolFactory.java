package spring.cloud.config.threadPool;

import java.util.concurrent.ExecutorService;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Component
public class SchedulersPoolFactory {

    public Scheduler customBoundedElastic;

    @Value("${thread.poolParams.corePoolSize}")
    private int corePoolSize;

    @Value("${thread.poolParams.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${thread.poolParams.capacity}")
    private int capacity;

    @Value("${thread.poolParams.keepAliveTime}")
    private int keepAliveTime;

    @PostConstruct //postConstruct를 함으로써 container에 싱글톤 패턴으로 하나만 주입해놓는다.
    public void makeSchedulerPool(){
        customBoundedElastic = SchedulersBoundThreadPool();
    }

    //SchedulersBoundedThreadPool 인스턴스를 새로 생성
    public Scheduler SchedulersBoundThreadPool(){

        Scheduler schedulers = Schedulers.newBoundedElastic(corePoolSize, capacity, "my-thread", keepAliveTime, false);

        return schedulers;
    }

    //새로운 Parallel 스레드풀을 사용. CPU-bound작업 처리
    public Scheduler SchedulersParallelThreadPool() throws InterruptedException{

        Scheduler schedulers = Schedulers.newParallel("par",3);

        return schedulers;
    }

    //우리가 만든 ExecuteService의 스레드 풀을 Schedulers로 래핑해서 제공
    public Scheduler SchedulersWithExecute(){

        JavaThreadPoolFactory javaThreadPool = new JavaThreadPoolFactory();
        ExecutorService exectJavaPool = javaThreadPool.makeBlockingQueuePool(5, 10, 60);

        Scheduler schedulers = Schedulers.fromExecutorService(exectJavaPool);

        return schedulers;
    }

    //공용 스레드 풀에서 가져와서 사용
    public Scheduler sharedBoundedScheduler() throws InterruptedException{

        Scheduler schedulers = Schedulers.boundedElastic();

        return schedulers;
    }

    //공용 스레드 풀에서 가져와서 사용
    public Scheduler sharedParallelScheduler() throws InterruptedException{

        Scheduler schedulers = Schedulers.parallel();

        return schedulers;
    }

    //공용 스레드 풀에서 가져와서 사용
    public Scheduler sharedSingleScheduler() throws InterruptedException{

        Scheduler schedulers = Schedulers.single();

        return schedulers;
    }
    
}
