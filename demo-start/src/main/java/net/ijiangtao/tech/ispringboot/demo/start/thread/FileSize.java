package net.ijiangtao.tech.ispringboot.demo.start.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicLong;

/**
 * get files' size
 *
 * @author ijiangtao
 * @create 2019-09-21 18:07
 **/
public class FileSize {

    private static ThreadLocal<ForkJoinPool> forkJoinPoolThreadLocal = new ThreadLocal<ForkJoinPool>() {
        @Override
        protected ForkJoinPool initialValue() {
            return new ForkJoinPool();
        }
    };

    public static long getDirAllFileSize(String dir) {
        File folder = new File(dir);
        if (!folder.isDirectory()) {
            return folder.length();
        }
        return forkJoinPoolThreadLocal.get().invoke(new FileSizeTask(folder));
    }

    private static class FileSizeTask extends RecursiveTask<Long> {

        private File folder;

        public FileSizeTask(File folder) {
            this.folder = folder;
        }


        @Override
        protected Long compute() {

            AtomicLong size = new AtomicLong(0);

            File[] fileArr = folder.listFiles();
            if (fileArr == null || fileArr.length == 0) {
                return 0L;
            }

            List<ForkJoinTask<Long>> taskList = new ArrayList<>();
            for (File f : fileArr) {
                if (!f.isDirectory()) {
                    size.addAndGet(f.length());
                } else {
                    //递归计算子文件夹
                    taskList.add(new FileSizeTask(f));
                }
            }
            for (ForkJoinTask<Long> t : invokeAll(taskList)) {
                size.addAndGet(t.join());
            }
            return size.get();
        }
    }


    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long result = getDirAllFileSize("/data/applogs");
        System.out.println("大小：" + result + "字节， 用时：" + (System.currentTimeMillis() - start) + "ms");
        //单线程 大小：263251930518
    }

}
