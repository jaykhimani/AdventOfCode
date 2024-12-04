package com.ing.aoc24;

import java.math.BigInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        String input = Util.getInput("3");
        String mulRegexWithDo = "mul\\(\\d{0,3},\\d{0,3}\\)|do\\(\\)|don't\\(\\)";
        Pattern newPattern = Pattern.compile(mulRegexWithDo);
        Matcher newMatcher = newPattern.matcher(input);
        BigInteger newResult = BigInteger.ZERO;

        boolean stopProcessing = false;
        while (newMatcher.find()) {
            String foundGroup = newMatcher.group();
            if (foundGroup.startsWith("do(")) {
                stopProcessing = false;
                continue;
            }
            if (foundGroup.startsWith("don't(") || stopProcessing) {
                stopProcessing = true;
                continue;
            }
            String[] split = foundGroup
                    .replace("mul(", "")
                    .replace(")", "")
                    .split(",");
            BigInteger multiply = new BigInteger(split[0]).multiply(new BigInteger(split[1]));
            newResult = newResult.add(multiply);
        }

        // Print the cleaned output
        System.out.println(newResult);
    }
}