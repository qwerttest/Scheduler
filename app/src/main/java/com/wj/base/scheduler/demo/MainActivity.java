package com.wj.base.scheduler.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.wj.base.scheduler.ITask;
import com.wj.base.scheduler.Scheduler;

public class MainActivity extends AppCompatActivity {
    Scheduler scheduler;
    String result = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scheduler = new Scheduler();
    }
    int num = 0;
    public void test(final View view) {
        scheduler.schedule(new ITask() {
            @Override
            protected void run() {
                System.out.println(num += 1);
            }
        }, 2000, 1000, Scheduler.ThreadType.UI);
    }

    @Override
    public void finish() {
        super.finish();
        scheduler.cancel();
    }
}
