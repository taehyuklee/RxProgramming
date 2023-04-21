package spring.cloud.config.threadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

@Component
@Slf4j
public class JavaThreadPoolFactory {

    public ThreadPoolExecutor linkedQPool;

    public ThreadPoolExecutor arraysQPool;

    public Scheduler linkedQpoolSchedule;

    public Scheduler arraysQpoolSchedule;

    @Value("${thread.poolParams.corePoolSize}")
    private int corePoolSize;

    @Value("${thread.poolParams.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${thread.poolParams.capacity}")
    private int capacity;

    @Value("${thread.poolParams.keepAliveTime}")
    private int keepAliveTime;


    @PostConstruct //postConstruct를 함으로써 container에 싱글톤 패턴으로 하나만 주입해놓는다.
    public void makeExecutorPool(){

        linkedQPool = makeBlockingQueuePool(corePoolSize, maximumPoolSize, keepAliveTime, capacity);
        arraysQPool = makeArrayQueuePool(corePoolSize, maximumPoolSize, keepAliveTime, capacity);

        linkedQpoolSchedule = Schedulers.fromExecutorService(linkedQPool);
        arraysQpoolSchedule = Schedulers.fromExecutorService(arraysQPool);

    }

    public ThreadPoolExecutor makeBlockingQueuePool(int corePoolSize, int maximumPoolSize, long keepAliveTime){

        //BlockingQueue로 구현 (Queue에 대기를 넣을수 있는게 무한이라 이건 쓰면 안됨)
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();

        ThreadPoolExecutor threadPoolExecutor = 
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

         return threadPoolExecutor;
    }

    public ThreadPoolExecutor makeBlockingQueuePool(int corePoolSize, int maximumPoolSize, long keepAliveTime, int capacity){

        //BlockingQueue로 구현 (Queue에 대기를 넣을수 있는게 무한이라 이건 쓰면 안됨)
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(capacity);

        ThreadPoolExecutor threadPoolExecutor = 
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

         return threadPoolExecutor;
    }



    public ThreadPoolExecutor makeArrayQueuePool(int corePoolSize, int maximumPoolSize, long keepAliveTime, int queueCapacity){

        //BlockingQueue로 ArrayBlockingQueue를 사용함.
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(queueCapacity);

        ThreadPoolExecutor threadPoolExecutor = 
              new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

       return threadPoolExecutor;
   }


  public ThreadPoolExecutor makeSynchQueuePool(int corePoolSize, int maximumPoolSize, long keepAliveTime){

        //SynchronousQueue로 구현
        BlockingQueue<Runnable> workQueue = new SynchronousQueue<>();

        ThreadPoolExecutor threadPoolExecutor = 
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);

        return threadPoolExecutor;

   }

   public ThreadPoolExecutor makePriorityQueuePool(int corePoolSize, int maximumPoolSize, long keepAliveTime){

        //SynchronousQueue로 구현
        BlockingQueue<Runnable> workQueue = new PriorityBlockingQueue<>();
        
        ThreadPoolExecutor threadPoolExecutor = 
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue);
        
       return threadPoolExecutor;
   
   }

   /**
    * RejectedExecutionHandler 인터페이스는 스레드풀에서 작업 처리를 거부할 때의 동작을 경정하는데 사용한다.
    * ThreadPoolExecutor 클래스는 기본적으로 4가지 정책을 제공하고 있다.
    * 1. AbortPolicy (default) : 스레드풀의 작업 대기 큐가 가득 차서 더 이상 작업을 처리할 수 없는 경우, RejectedExecutionException 예외를 발생시키고,
    *                            작업처리를 중단시킨다. 이 정책이 기본 정책
    * 2. CallerRunsPolicy : 작업 처리 거부 시, 작업을 제출한 스레드에서 작업을 처리한다.
    * 3. DiscardPolicy : 작업 처리 거부 시, 작업을 무시한다
    * 4. DiscardOldestPolicy : 작업 처리 거부 시, 작업 대기 큐에서 가장 오래된 작업을 삭제하고, 새로운 작업을 큐에 넣는다.
    */


   public ThreadPoolExecutor makeBlockPoolWithRejectHandler(int corePoolSize, int maximumPoolSize, long keepAliveTime){

        //RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.DiscardOldestPolicy();
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(20);
        
        ThreadPoolExecutor threadPoolExecutor = 
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workQueue, rejectedExecutionHandler);
        
       return threadPoolExecutor;
      }

    // @Scheduled(cron = "0/2 * * * * ?")
    // public void viewThreadPool(){
    //     //log.info("dd");
    //     System.out.println("queueSize: " + arraysQPool.getQueue().size());
    //     System.out.println(capacity);
    // }

}
