package net.ijiangtao.tech.ispringboot.demo.start.thread;

import java.io.IOException;
import java.util.concurrent.*;

public class MyThread {



    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {


    }


    public static void test() {

        Executor executor = Executors.newSingleThreadExecutor();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ExecutorService executorServiceF = Executors.newFixedThreadPool(5);

        Future<String> future = executorServiceF.submit(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "call 55555555");
                        Thread.sleep(2 * 1000);
                        return "123123";
                    }
                }
        );


        try {
            System.out.println("===============" + future.isDone());

            System.out.println("===============" + future.cancel(true));

            //System.out.println("===============" + future.get());

            System.out.println("===============" + future.isCancelled());
            System.out.println("===============" + future.isDone());

        } catch (Exception e) {
            e.printStackTrace();
        }


        executorServiceF.submit(
                new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "call 55555555");
                        return null;
                    }
                }
        );

        executorServiceF.submit(
                new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "call 55555555");
                        return null;
                    }
                }
        );

        executorServiceF.submit(
                new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "call 55555555");
                        return null;
                    }
                }
        );

        executorServiceF.submit(
                new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "call 55555555");
                        return null;
                    }
                }
        );

        executorServiceF.submit(
                new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "call 55555555");
                        return null;
                    }
                }
        );

        executorServiceF.submit(
                new Callable<Object>() {
                    @Override
                    public Object call() throws Exception {
                        System.out.println(Thread.currentThread().getName() + "call 55555555");
                        return null;
                    }
                }
        );

        executorService.submit(new Callable<Object>() {

            public Object call() throws Exception {
                System.out.println("call");
                return null;
            }

        });

        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.printf("run");
            }
        });
    }
}
