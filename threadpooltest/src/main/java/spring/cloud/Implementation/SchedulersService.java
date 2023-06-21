package spring.cloud.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.scheduler.Scheduler;
import spring.cloud.config.threadPool.SchedulersPoolFactory;

@Component
public class SchedulersService {

    @Autowired
    private SchedulersPoolFactory schedulersPoolFactory;


	public Scheduler getSchedulersPool(String sPoolType) throws Exception {
        /*
         * poolType : B: bound, P: parallel
         * taskType : CPU, IO
         * N : loop Num (if I/O bound N=0)
         */

        if(sPoolType.equals("bound")){
            // System.out.println("Bound 입니다");
            return schedulersPoolFactory.sharedBoundedScheduler();
            //return schedulersPoolFactory.customBoundedElastic;
        }else if(sPoolType.equals("paral")){
            // System.out.println("pararrel 입니다");
            return schedulersPoolFactory.sharedParallelScheduler();
        }else if(sPoolType.equals("newBo")){
            // System.out.println("pararrel 입니다");
            return schedulersPoolFactory.customBoundedElastic; 
        }

        return null;

    }



}
