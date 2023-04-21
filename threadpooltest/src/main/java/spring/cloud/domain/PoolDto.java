package spring.cloud.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PoolDto {

    //ExecutorService || Schedulers
    String poolType;
    //ArraysQ || LinkedQ
    String queueType;
    //Bound || Parallel
    String sPoolType;
    //I/O Thread sleep time
    int blockTime;
    //Cpu Bound task Loop nums
    int N;

}
