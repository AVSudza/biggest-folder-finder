package org.example;

import java.util.HashMap;

public class SizeCalculator {
    private static char[] sizeMultipliers = {'B', 'K', 'M', 'G', 'T'};
    private static HashMap<Character, Integer> char2multiplier = getMultipliers();

    //TODO: 24B, 234kB, 36Mb, 34Gb, 42TB
//    public String getHumanReadableSize(long size) {
//        String[] sizeLetter = {"B", "kB", "MB", "GB", "TB"};
//        int index = 0;
//        while (size > 1024) {
//            size /= 1024;
//            index += 1;
//        }
//        return size + sizeLetter[index];
//    }
    public static String getHumanReadableSize(long size) {
        for(int i = 0; i < sizeMultipliers.length; i++){
            double value = ((double) size) / Math.pow(1024, i);
            if (value < 1024) {
                return Math.round(value * 100) / 100. + "" +
                        sizeMultipliers[i] +
                        (i > 0 ? "b" : "");
            }
        }
        return "Very big!";
    }

    //TODO: 24B, 234kB, 36Mb, 34Gb, 42TB
    // 24B, 234K, 36M, 34G, 42T
    // 235K => 240640
    public static long getSizeFromHumanReadable(String size) {
//        System.out.println(size);
//        System.exit(0);

        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multiplier.get(sizeFactor);
        long length = multiplier * Long.valueOf(size.replaceAll("[^0-9]", ""));

        return length;
    }

    private static HashMap<Character,Integer> getMultipliers(){
        char[] multipliers = {'B', 'K', 'M', 'G', 'T'};
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for(int i = 0; i < multipliers.length; i++) {
            char2multiplier.put(
                    multipliers[i],
                    (int)Math.pow(1024, i)
            );
        }
        return char2multiplier;
    }
}
