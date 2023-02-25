package org.example;

import java.io.File;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {

//        MyThread thread = new MyThread(1);
//        MyThread thread2 = new MyThread(2);
//        thread.start();
//        thread2.start();

//        for(int i = 0; i < args.length; i++){
//            System.out.println(i + " => " + args[i]);
//        }
//        String[] argums = {"-d", "E:\\IdeaProj", "-l", "7K"};

        ParametersBag bag = new ParametersBag(args);
        String folderPath = bag.getPath();
        long sizeLimit = bag.getLimit();


//        String folderPath = "E:/Батя/Скиллбокс";
//        long sizeLimit = 50 * 1024 * 1024;

        File file = new File(folderPath);
        Node root = new Node(file, sizeLimit);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator = new FolderSizeCalculator(root);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);
        System.out.println(size);
//        System.out.println("root.getSize(): " + root.getSize());
        System.out.println("root: " + root);

//        System.out.println(getFolderSize(file));

//        System.out.println(calculator.getSizeFromHumanReadable("235K"));
//        System.out.println(calculator.getHumanReadableSize(240640));
//        System.exit(0);

        long duration = (System.currentTimeMillis() - start);
        System.out.println(duration + " ms");

//        Set keys = System.getProperties().keySet();
//        for(Object key : keys){
//            System.out.println(key);
//        }
//        System.out.println(System.getProperties().get("user.dir"));
//        System.out.println("getHumanReadableSize: " + calculator.getHumanReadableSize(24234));

    }

    //TODO после введения нод метод не нужен
    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }
}