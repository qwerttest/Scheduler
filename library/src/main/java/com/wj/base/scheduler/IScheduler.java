package com.wj.base.scheduler;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zshh on 3/21/18.
 */

public interface IScheduler {

    enum ThreadType {
        UI(AndroidSchedulers.mainThread()),
        IO(Schedulers.io()),
        CPU(Schedulers.computation()),
        NEW(Schedulers.newThread());
        private Scheduler scheduler;
        ThreadType(Scheduler scheduler){
            this.scheduler = scheduler;
        }
        public Scheduler getScheduler(){
            return scheduler;
        }
    }

    void schedule(ITask task, ThreadType threadType);

    void schedule(ITask task, long initDelay, ThreadType threadType);

    void schedule(ITask task, long initDelay, long period, ThreadType threadType);

    void cancel(ITask task);

    void cancel();
}
