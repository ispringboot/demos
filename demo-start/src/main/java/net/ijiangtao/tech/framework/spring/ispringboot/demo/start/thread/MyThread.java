package net.ijiangtao.tech.framework.spring.ispringboot.demo.demostart.thread;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MyThread {

    /**
     * 预编译正则表达式，以提升执行速度
     */
    private static Pattern P = Pattern.compile("\\PL+");

    /**
     * 使用正则表达式，统计文本中word出现的次数
     *
     * @param word
     * @param path
     * @return
     */
    public static long occurrences(String word, Path path) {
        try {
            String contents = new String(Files.readAllBytes(path),
                    StandardCharsets.UTF_8);
            return P.splitAsStream(contents)
                    .filter(Predicate.isEqual(word))
                    .count();
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取指定路径下所有的文件
     *
     * @param p
     * @return
     * @throws IOException
     */
    public static Set<Path> descendants(Path p) throws IOException {
        try (Stream<Path> entries = Files.walk(p)) {
            return entries.collect(Collectors.toSet());
        }
    }

    /**
     * 多线程统计指定路径下所有文件中word出现的次数
     *
     * @param word
     * @param path
     * @return
     */
    public static long countWordInAllFiles(String word, String path) {

        ExecutorService executor = null;
        try {

            //读取指定目录下所有的文件
            Set<Path> paths = descendants( path);
            //descendants(Paths.get(path)); 会有 java.nio.file.AccessDeniedException ？？？

            //将读取文件内容的任务加入List
            List<Callable<Long>> tasks = new ArrayList<>();
            for (Path p : paths) {
                tasks.add(() -> {
                    return occurrences(word, p);
                });
            }

            //根据JVM核心数构造线程池
            int processors = Runtime.getRuntime().availableProcessors();
            executor = Executors.newFixedThreadPool(processors);

            //执行所有Callable的call方法统计每个文件中word出现的次数，并将执行结果放入Future
            List<Future<Long>> results = executor.invokeAll(tasks);

            //统计word出现的次数
            long total = 0;
            for (Future<Long> result : results) {
                total += result.get();
            }
            System.out.println("Occurrences of String: " + total);

            return total;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池
            if (null != executor) {
                executor.shutdown();
            }
        }

        return 0;
    }

    private static Set<Path> descendants(String fileDir) {
        Set<Path> paths=new HashSet<>();
        List<File> fileList = new ArrayList<File>();
        File file = new File(fileDir);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if (files == null) {// 如果目录为空，直接退出
            return paths;
        }
        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            } else if (f.isDirectory()) {
                System.out.println(f.getAbsolutePath());
                descendants(f.getAbsolutePath());
            }
        }
        for (File f1 : fileList) {
            Path path=Paths.get(f1.getAbsolutePath());
            paths.add(path);
        }

        return paths;
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        //test("C:\\1files\\test\\");
        countWordInAllFiles("hello", "C:\\1files\\test\\");

//        String searchWord = "occurrences";
//        List<Callable<Path>> searchTasks = new ArrayList<>();
//        for (Path p : paths) {
//            searchTasks.add(() -> {
//                if (occurrences(searchWord, p) > 0) {
//                    return p;
//                } else {
//                    throw new RuntimeException();
//                }
//            });
//        }
//
//        Path found = executor.invokeAny(searchTasks);
//        System.out.println(found);

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
