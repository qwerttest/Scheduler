package com.wj.base.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import io.reactivex.disposables.Disposable;

/**
 * Created by zshh on 3/20/18.
 */
public class Scheduler implements IScheduler{

    private Map<ITask, Disposable> map = new HashMap<>();

    /**
     * @param task
     * @param threadType
     */
    @Override
    public void schedule(final ITask task, ThreadType threadType) {
        Disposable disposable = threadType.getScheduler().scheduleDirect(new Runnable() {
            @Override
            public void run() {
                task.run();
            }
        });
        map.put(task,disposable);
    }

    /**
     * @param task
     * @param initDelay
     * @param threadType
     */
    @Override
    public void schedule(final ITask task, long initDelay, ThreadType threadType) {
        Disposable disposable = threadType.getScheduler().scheduleDirect(new Runnable() {
            @Override
            public void run() {
                task.run();
            }
        }, initDelay, TimeUnit.MILLISECONDS);
        map.put(task,disposable);
    }

    /**
     * @param task
     * @param initDelay
     * @param period
     * @param threadType
     */
    @Override
    public void schedule(final ITask task, long initDelay, long period, ThreadType threadType) {
        Disposable disposable = threadType.getScheduler().schedulePeriodicallyDirect(new Runnable() {
            @Override
            public void run() {
                task.run();
            }
        }, initDelay,period,TimeUnit.MILLISECONDS);
        map.put(task,disposable);
    }

    /**
     * @param task 取消任务.
     */
    @Override
    public void cancel(ITask task) {
        Disposable disposable = map.remove(task);
        if(disposable!=null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }

    /**
     * 取消全部任务.
     */
    @Override
    public void cancel() {
        for(ITask task : map.keySet()){
            cancel(task);
        }
    }
}
