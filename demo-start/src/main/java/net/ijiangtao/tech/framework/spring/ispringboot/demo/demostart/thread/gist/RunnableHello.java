package net.ijiangtao.tech.framework.spring.ispringboot.demo.demostart.thread.gist;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class RunnableHello {

    private static boolean said = false;

    public void say() {

        Runnable hi = () -> {

            for (int i = 0; i < 999; i++) {
                System.out.println("say-" + i);
            }
            said = true;
        };

        Runnable bye = () -> {

            int i = 0;
            while (!said) {
                i++;
                System.out.println("bye i=" + i + " said=" + said);
            }
            System.out.println("*********bye-" + i);
        };

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(5);
        scheduledThreadPoolExecutor.execute(hi);
        scheduledThreadPoolExecutor.execute(bye);
        scheduledThreadPoolExecutor.shutdown();
    }

    private static volatile int count = 0;

    /**
     * 模拟死锁。
     * <p>
     * 100个线程，每个线程将count累加1000次。
     */
    public void countTask() {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50);
        for (int i = 1; i <= 100; i++) {

            int taskId = i;
            Runnable task = () -> {
                for (int k = 1; k <= 1000; k++) {
                    count++;
                }
                System.out.println(taskId + " : " + count);
            };

            executor.execute(task);

        }

        executor.shutdown();

    }

    public static void main(String[] args) {
        new RunnableHello().countTask();
        //new RunnableHello().say();
    }
}
