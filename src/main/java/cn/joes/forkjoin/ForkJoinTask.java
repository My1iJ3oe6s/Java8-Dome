package cn.joes.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * Created by myijoes on 2018/9/27.
 *
 * @author wanqiao
 */
public class ForkJoinTask extends RecursiveTask<Long> {

    private int taskSum = 5000;

    Long start;

    Long end;

    public ForkJoinTask(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= taskSum) {
            long total = 0;
            for (Long i = start; i < end; i++) {
                total += i;
            }
            return total;
        } else {
            long avg = (end - start) / 2;
            ForkJoinTask forkJoinTask = new ForkJoinTask(start, avg);
            ForkJoinTask forkJoinTask1 = new ForkJoinTask(avg, end);
            forkJoinTask.fork();
            forkJoinTask1.fork();
            return forkJoinTask.join() + forkJoinTask.join();
        }
    }
}
