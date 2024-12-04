package com.ing.aoc24;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Day4 {
    private static final String WORD = "XMAS";

    private static final Pattern XMAS = Pattern.compile("(?=XMAS)|(?=SAMX)");
    private static final Pattern MAS = Pattern.compile("(?=MAS)|(?=SAM)");

    public static void main(String[] args) {

        List<String> lines = Util.getLines("4");

        long count = puzzlePart1(lines);
        System.out.println("XMAS appears: " + count);

        count = puzzlePart2(lines);
        System.out.println("X-MAS appears: " + count);
    }

    private static long puzzlePart2(List<String> lines) {
        long count = 0;
        for (int row = 0; row < lines.size() - 2; row++) {
            String firstRow = lines.get(row);
            String secondRow = lines.get(row + 1);
            String thirdRow = lines.get(row + 2);

            for (int col = 0; col <= firstRow.length() - 3; col++) {
                String leftMas = "" + firstRow.charAt(col) + secondRow.charAt(col + 1) + thirdRow.charAt(col + 2);
                String rightMas = "" + firstRow.charAt(col + 2) + secondRow.charAt(col + 1) + thirdRow.charAt(col);

                if (MAS.matcher(leftMas).results().count() + MAS.matcher(rightMas).results().count() == 2) {
                    count++;
                }
            }
        }
        return count;
    }

    // Find XMAS - word, reverse word, diagonally or reverse diagonally
    private static long puzzlePart1(List<String> lines) {
        Map<Integer, List<Character>> processedLines = new HashMap<>();
        long count = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            processedLines.put(i, line.chars().mapToObj(c -> (char) c).toList());

            // Look in the same line if we have word or reversed word
            count += XMAS.matcher(line).results().count();
            if (i > 2) {
                StringBuilder verticalCache = new StringBuilder();
                StringBuilder diagonalCache = new StringBuilder();
                StringBuilder reverseDiagonalCache = new StringBuilder();

                int lineLen = line.length();
                for (int col = 0; col < lineLen; col++) {
                    int diagonalColIdx = col;
                    int reverseDiagonalColIdx = lineLen - col - 1;
                    for (int row = i; row >= i - 3; row--) {
                        // Lookup vertically
                        Character c = processedLines.get(row).get(col);
                        verticalCache.append(c);

                        // Look up diagonally
                        if (diagonalColIdx < lineLen) {
                            diagonalCache.append(processedLines.get(row).get(diagonalColIdx++));
                        }

                        // Look up reverse diagonally
                        if (reverseDiagonalColIdx >= 0) {
                            reverseDiagonalCache.append(processedLines.get(row).get(reverseDiagonalColIdx--));
                        }
                    }
                    count += XMAS.matcher(verticalCache.toString()).results().count();
                    count += XMAS.matcher(diagonalCache.toString()).results().count();
                    count += XMAS.matcher(reverseDiagonalCache.toString()).results().count();

                    verticalCache.delete(0, verticalCache.length());
                    diagonalCache.delete(0, diagonalCache.length());
                    reverseDiagonalCache.delete(0, reverseDiagonalCache.length());
                }
            }
        }
        return count;
    }
}
