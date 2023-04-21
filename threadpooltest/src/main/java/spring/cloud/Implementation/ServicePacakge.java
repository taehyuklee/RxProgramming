package spring.cloud.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.scheduler.Scheduler;
import spring.cloud.domain.PoolDto;

@Component
@Slf4j
public class ServicePacakge {

    @Autowired
    private ExecutorPoolService executorPoolService;
    
    @Autowired
    private SchedulersService schedulersService;

    /*  
     *  N == 0일때 I/O-bound tasks, N>0일때 CPU-bound tasks
     *  blockTime - blocking time (N>0일때는 무시됨)
     * 
     */

    public Scheduler getThreadPoolPacakgeTest(PoolDto poolDto) throws Exception{

        if(poolDto.getPoolType().equals("Executors")){
            //일반 자바 스레드 풀.
            return executorPoolService.getExecutor(poolDto.getQueueType());
            

        }else if(poolDto.getPoolType().equals("Scheduler")){
            //Schedulers 스레드 풀.
            return schedulersService.getSchedulersPool(poolDto.getSPoolType());

        }else{
            log.info("자바스레드풀인지, 스케줄러 스레드풀인지 구분할수 없습니다. 헤더에 스레드풀 종류를 정확히 입력해주세요.");
            return null;
        }

    }
    
}
