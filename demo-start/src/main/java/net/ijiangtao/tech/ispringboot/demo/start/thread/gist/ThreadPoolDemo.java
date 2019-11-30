package net.ijiangtao.tech.ispringboot.demo.start.thread.gist;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {

        //Thread.State
        MyThread thread=new MyThread();
        thread.start();
        thread.suspend();
        for (int j=0;j<1000;j++){
            try{
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
            Thread.interrupted();
            if (!thread.isInterrupted()){
                thread.interrupt();
            }

        }



        for (int i = 0; i < 0; i++) {

            executor.execute(new MyTask(i, 10000));

            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount() + "，ActiveCount=" + executor.getActiveCount());

            executor.execute(new MyTask(i + 1, 1));


            System.out.println("线程池中线程数目：" + executor.getPoolSize() + "，队列中等待执行的任务数目：" +
                    executor.getQueue().size() + "，已执行完的任务数目：" + executor.getCompletedTaskCount() + "，ActiveCount=" + executor.getActiveCount());
        }

    }


    static class MyThread extends Thread{


        int count=0;

        @Override
        public void run(){

            System.out.println("isInterrupted="+this.isInterrupted()+",count="+count);

            while (true){
                if (!this.isInterrupted()){
                    System.out.println("========isInterrupted="+this.isInterrupted()+",count="+count);
                    count++;
                }else {
                    System.out.println("========isInterrupted="+this.isInterrupted()+",count="+count);
                }

            }

        }

    }

    static class MyTask implements Runnable {

        private int taskNum;

        private long sleepTime;

        public MyTask(int num, long sleepTime) {
            this.taskNum = num;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            System.out.println("线程名称：" + Thread.currentThread().getName() + "，正在执行task " + taskNum);
            try {
                Thread.currentThread().sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task " + taskNum + "执行完毕");
        }
    }
}
