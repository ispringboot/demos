package net.ijiangtao.tech.framework.spring.ispringboot.demo.demostart.thread.gist;

public class SubThread extends Thread {

    public SubThread(String name){
        super.setName(name);
    }

    @Override
    public void run(){
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName()+"-"+i);
        }
    }

    public static void main(String[] args) {
        new SubThread("m1").start();
        new SubThread("m2").start();
        new SubThread("m3").start();
        new SubThread("m4").start();
    }

}
