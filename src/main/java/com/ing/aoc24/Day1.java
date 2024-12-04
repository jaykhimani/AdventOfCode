package com.ing.aoc24;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Day1 {
    public static void main(String[] args) {
        Stream<String> input = Util.getInputStream("1");
        List<Integer> col1 = new ArrayList<>();
        List<Integer> col2 = new ArrayList<>();

        input.forEach(line -> {
            String[] split = line.split("[ ]+");
            col1.add(Integer.parseInt(split[0]));
            col2.add(Integer.parseInt(split[1]));
        });
        col1.sort(Integer::compareTo);
        col2.sort(Integer::compareTo);

        Integer totalDistance = 0;
        for (int i = 0; i < col1.size(); i++) {
            totalDistance += Math.abs(col1.get(i) - col2.get(i));
        }

        System.out.println(totalDistance);


        Map<Integer, Integer> frequencyMap = new HashMap<>();
        col2.forEach(col -> frequencyMap.put(col, frequencyMap.getOrDefault(col, 0) + 1));

        Integer similarityScore = 0;
        for (Integer val : col1) {
            Integer i = frequencyMap.getOrDefault(val, 0) * val;
            similarityScore = similarityScore + i;
        }

        System.out.println(similarityScore);
    }

}
