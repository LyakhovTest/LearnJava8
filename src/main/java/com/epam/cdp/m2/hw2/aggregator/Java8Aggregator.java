package com.epam.cdp.m2.hw2.aggregator;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import javafx.util.Pair;

public class Java8Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        return numbers.stream().reduce(0, Integer::sum);
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        return words.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream().map(e->new Pair<>(e.getKey(), e.getValue()))
                .sorted((o1, o2) -> {
                    long compare = o2.getValue()-o1.getValue();
                    if(compare==0){
                        return o1.getKey().compareTo(o2.getKey());
                    }
                    return (int) compare;
                }).limit(limit).collect(Collectors.toList());

    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> elements = new HashSet<>();
        return words.stream().filter(e -> !elements.add(e)).collect(Collectors.toList());
    }
}