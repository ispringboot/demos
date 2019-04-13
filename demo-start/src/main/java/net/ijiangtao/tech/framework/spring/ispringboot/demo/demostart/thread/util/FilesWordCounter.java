package net.ijiangtao.tech.framework.spring.ispringboot.demo.demostart.thread.util;

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

/**
 * 统计某个单词(word)在指定路径(path)，包括所有子目录，下的文本文件中出现的次数。
 * <p>
 * 使用UTF-8编码。
 * <p>
 * 使用多线程读取文件内容并统计多个线程的执行结果之和。
 *
 * @author ijiangtao.net
 */
public class FilesWordCounter {

    /**
     * 以非字母分隔符进行分隔。
     * <p>
     * 例如统计『hello』出现的次数，那么『hello-hello』就是出现了两次，而『hellohello』 不在统计范畴之内。
     * <p>
     * 预编译正则表达式，以提升执行速度。
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
            Set<Path> paths = getAllFilePaths(path, null);
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

    /**
     * 递归获取指定目录下所有文件的Path集合。
     *
     * @param fileDir
     * @param paths
     * @return
     */
    private static Set<Path> getAllFilePaths(String fileDir, Set<Path> paths) {
        if (null == paths) {
            paths = new HashSet<>();
        }

        // 获取目录下的所有文件或文件夹
        File file = new File(fileDir);
        File[] files = file.listFiles();

        // 如果目录为空，直接退出
        if (files == null) {
            return paths;
        }

        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.isFile()) {
                Path path = Paths.get(f.getAbsolutePath());
                paths.add(path);
            } else if (f.isDirectory()) {
                getAllFilePaths(f.getAbsolutePath(), paths);
            }
        }
        return paths;
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {

        //指定你需要统计的单词和文件所在目录进行测试
        System.out.println("Occurrences of String: " + countWordInAllFiles("hello", "C:\\1files\\test\\"));


    }

}
