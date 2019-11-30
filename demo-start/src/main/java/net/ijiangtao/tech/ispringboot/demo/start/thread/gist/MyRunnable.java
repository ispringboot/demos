package net.ijiangtao.tech.ispringboot.demo.start.thread.gist;


public class MyRunnable implements Runnable {

    @Override
    public void run(){
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName()+"-"+i);
        }
    }

    public static void main(String[] args) {

        new Thread(new MyRunnable()).start();
        new Thread(new MyRunnable()).start();
        new Thread(new MyRunnable()).start();

        Thread myRunnable4=new Thread(new MyRunnable(),"1234");
        myRunnable4.setName("MyRunnable-4");
        myRunnable4.start();

    }
}
