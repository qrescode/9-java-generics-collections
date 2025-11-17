package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {

        Set<String> words = new BufferedReader(new InputStreamReader(inputStream, charset))
                .lines()
                .map(String::trim)
                .map(String::toLowerCase)
                .filter(s -> s.length() >= 3)
                .filter(s -> s.matches("[а-яё]+"))
                .collect(Collectors.toSet());

        Map<String, List<String>> map = new HashMap<>();

        for (String word : words) {
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);

            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        return map.values().stream()
                .filter(list -> list.size() >= 2)
                .peek(Collections::sort)
                .sorted(Comparator.comparing(list -> list.get(0)))
                .map(list -> new LinkedHashSet<>(list))
                .collect(Collectors.toList());
    }
}
