package com.epam.cdp.m2.hw2.aggregator;

import javafx.util.Pair;

import java.util.*;

public class Java7Aggregator implements Aggregator {

    @Override
    public int sum(List<Integer> numbers) {
        int result = 0;
        for(Integer i : numbers) result+= i;
        return result;
        //throw new UnsupportedOperationException();
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        List<Pair<String, Long>> pairs = new ArrayList<>();
        Set<String> uniquStr = new HashSet<>();

        for(String string:words){
            if(!uniquStr.contains(string)){
                uniquStr.add(string);
                Long count=0L;
                for(String str:words){
                    if(str.equals(string)) ++count;
                }
                pairs.add(new Pair<>(string, count));
            }
        }

        pairs.sort(new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> o1, Pair<String, Long> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                } else if (o1.getValue().equals(o2.getValue())) {
                    return 0; // You can change this to make it then look at the
                    //words alphabetical order
                } else {
                    return 1;
                }
            }
        });
        for (int i = pairs.size() - 1; i >= limit; --i)
            pairs.remove(i);
        return pairs;
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        Set<String> uniquStr = new HashSet<>();
        Set<String> dublicates = new HashSet<>();
        for(String string:words){
            if(!uniquStr.contains(string.toUpperCase(Locale.ROOT))){
                uniquStr.add(string.toUpperCase(Locale.ROOT));
                 }
            else dublicates.add(string.toUpperCase(Locale.ROOT));
        }
        List<String> mainList = new ArrayList<String>();
        mainList.addAll(dublicates);

        for (int i = mainList.size() - 1; i >= limit; --i)
            mainList.remove(i);
        //Collections.sort(mainList);
        return mainList;
    }
}
