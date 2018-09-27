package cn.joes.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Created by myijoes on 2018/9/27.
 *
 * @author wanqiao
 */
public class ForkAndJoinTest {

    public static void main(String[] args) {
        ForkJoinPool forkjoinPool = new ForkJoinPool();
        ForkJoinTask forkJoinTask = new ForkJoinTask(0L, 10000000L);
        Future<Long> result = forkjoinPool.submit(forkJoinTask);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
