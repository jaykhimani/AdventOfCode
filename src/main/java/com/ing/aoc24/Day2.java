package com.ing.aoc24;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {
    public static void main(String[] args) {
        AtomicInteger numOfSafeReports = new AtomicInteger();

        List<String> reports = Util.getLines("2");
        reports.forEach(report -> {
            String[] split = report.split("[ ]+");
            boolean safeReport = isSafeReport(split);
            if (safeReport) {
                numOfSafeReports.getAndIncrement();
            }
        });
        System.out.println("Num of safe report: " + numOfSafeReports.get());

        numOfSafeReports.set(0);
        for (String report : reports) {
            String[] split = report.split("[ ]+");
            if (isSafeReport(split)) {
                numOfSafeReports.getAndIncrement();
            } else {
                for (int i = 1; i < split.length; i++) {
                    String[] newSplit = removeElementAtPosition(i, split);
                    if (isSafeReport(newSplit)) {
                        numOfSafeReports.getAndIncrement();
                        break;
                    }
                }
            }
        }
        System.out.println("Num of safe report: " + numOfSafeReports.get());
    }

    private static String[] removeElementAtPosition(int position, String[] split) {
        String[] newArray = new String[split.length - 1];

        for (int i = 0, j = 0; i < split.length; i++) {
            if (i != position) {
                newArray[j++] = split[i];
            }
        }

        return newArray;
    }

    private static boolean isSafeReport(String[] split) {
        boolean safeReport = true;
        Boolean increasing = null;
        Integer prev = null;
        for (String s : split) {
            Integer parseInt = Integer.parseInt(s);
            if (prev != null) {
                if (prev > parseInt) {
                    if (increasing != null && increasing) {
                        safeReport = false;
                        break;
                    }
                    increasing = false;
                } else {
                    if (increasing != null && !increasing) {
                        safeReport = false;
                        break;
                    }
                    increasing = true;
                }

                int absDiff = Math.abs(prev - parseInt);
                if (absDiff < 1 || absDiff > 3) {
                    safeReport = false;
                    break;
                }
            }
            prev = parseInt;
        }
        return safeReport;
    }
}
