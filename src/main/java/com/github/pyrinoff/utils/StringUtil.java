package com.github.pyrinoff.utils;

import java.util.*;
import java.util.stream.Collectors;

public interface StringUtil {

    int maxIntLength = String.valueOf(Integer.MAX_VALUE).length();

    static String getArgString(
            final String str,
            final int index,
            String delimiter,
            final int maxLen,
            final boolean cutIfLonger
    ) {
        if (index < 0) return null;
        if (delimiter == null) delimiter = " ";

        String[] exploded = str.split(delimiter);
        if ((index + 1) > exploded.length) return null;
        if (maxLen >= exploded[index].length()) return exploded[index];
        else {
            if (cutIfLonger) return exploded[index].substring(0, maxLen);
            else return null;
        }
    }

    static Integer getArgInt(String str, int index, String delimiter) {
        String s = getArgString(str, index, delimiter, maxIntLength, false);
        if (s == null) return null;
        return Integer.parseInt(s);
    }

    static Long getArgLong(String str, int index, String delimiter) {
        String s = getArgString(str, index, delimiter, maxIntLength, false);
        if (s == null) return null;
        return Long.parseLong(s);
    }

    static int getArgStringCount(
            final String str,
            String delimiter
    ) {
        String[] exploded = str.split(delimiter);
        return exploded.length;
    }

    static String getArgStringStartWithArg(
            final String str,
            int index,
            String delimiter
    ) {
        String[] exploded = str.split(delimiter);
        if ((index + 1) > exploded.length) return null;
        StringBuilder result = new StringBuilder();
        for (int i = index; i < exploded.length; i++) {
            result.append(exploded[i]).append(" ");
        }
        return result.toString().trim();
    }

    static boolean isNotEmpty(String str) {
        return !(str == null || str.isEmpty());
    }

    static boolean startsWithAnyOf(String searchIn, List<String> searchFor) {
        if (searchIn == null || searchFor == null || searchFor.isEmpty()) return false;
        return searchFor.stream().anyMatch(searchIn::startsWith);
    }

    static boolean startsWithAnyOf(String searchIn, String... searchFor) {
        return startsWithAnyOf(searchIn, List.of(searchFor));
    }

    static boolean containsAnyOfPhrases(String searchIn, List<String> searchFor) {
        if (searchIn == null || searchFor == null || searchFor.isEmpty()) return false;
        return searchFor.stream().anyMatch(searchIn::contains);
    }

    static boolean containsAnyOfPhrases(String searchIn, String... searchFor) {
        return containsAnyOfPhrases(searchIn, List.of(searchFor));
    }

    static boolean containsAllOfPhrases(String searchIn, List<String> searchFor) {
        if (searchIn == null || searchFor == null || searchFor.isEmpty()) return false;
        return searchFor.stream().allMatch(searchIn::contains);
    }

    static boolean containsAllOfPhrases(String searchIn, String... searchFor) {
        return containsAllOfPhrases(searchIn, List.of(searchFor));
    }

    static boolean startsWithAnyOfInsensitive(String searchIn, List<String> searchFor) {
        return startsWithAnyOf(toLowerCase(searchIn), toLowerCase(searchFor));
    }

    static boolean startsWithAnyOfInsensitive(String searchIn, String... searchFor) {
        return startsWithAnyOf(searchIn, List.of(searchFor));
    }

    static boolean startsWithAnyOfWords(String searchIn, List<String> searchFor) {
        if (searchIn == null || searchFor == null || searchFor.isEmpty()) return false;
        for (String oneSearchFor : searchFor) {
            if (searchIn.startsWith(oneSearchFor) //если строка начинается с нашей
                    //и следующий после нее символ либо отсутствует, либо не буквенный
                    && (oneSearchFor.length() == searchIn.length()  //отсутствует, т.к. строки равны
                    || RegexUtil.hasAtLeastOneMatch( //символ = буквенный
                    searchIn.charAt(oneSearchFor.length()),
                    RegexUtil.ANY_NON_CYR_OR_LAT_CHARS)
            )) return true;
        }
        return false;
    }

    public static Map<String, Integer> countWordsContainsInsideString(List<String> stringToSearchIn,
                                                                      List<String> words) {
        // Создаем Map для хранения результатов
        Map<String, Integer> resultMap = new HashMap<>();

        // Проходим по каждому пути
        for (String oneStringToSearchIn : stringToSearchIn) {
            int count = 0;

            // Считаем, сколько слов из списка words присутствуют в строке пути
            for (String word : words) {
                if (oneStringToSearchIn.contains(word)) {
                    count++;
                }
            }

            // Сохраняем абсолютный путь и количество найденных слов в Map
            resultMap.put(oneStringToSearchIn, count);
        }

        // Возвращаем несортированную карту
        return resultMap;
    }

    static boolean startsWithAnyOfWords(String searchIn, String... searchFor) {
        return startsWithAnyOfWords(searchIn, List.of(searchFor));
    }

    static boolean startsWithAnyOfWordsInsensitive(String searchIn, List<String> searchFor) {
        return startsWithAnyOfWords(toLowerCase(searchIn), toLowerCase(searchFor));
    }

    static boolean startsWithAnyOfWordsInsensitive(String searchIn, String... searchFor) {
        return startsWithAnyOfWordsInsensitive(searchIn, List.of(searchFor));
    }

    static boolean containsAnyOfWords(String searchIn, List<String> searchForWords) {
        if (searchIn == null || searchForWords == null || searchForWords.isEmpty()) return false;
        //List<String> listOfWordsInText = List.of(TextUtil.getCleanupText(searchIn).split(" "));
        //return searchForWords.stream().anyMatch(listOfWordsInText::contains);
        return searchForWords.stream().anyMatch(word ->
                RegexUtil.hasAtLeastOneMatch(searchIn, "\\b"+word+"\\b")
        );
    }

    static boolean containsAnyOfWords(String searchIn, String... searchForWords) {
        return containsAnyOfWords(searchIn, List.of(searchForWords));
    }

    static boolean containsAllOfWords(String searchIn, List<String> searchForWords) {
        if (searchIn == null || searchForWords == null || searchForWords.isEmpty()) return false;
        List<String> listOfWordsInText = List.of(TextUtil.getCleanupText(searchIn).split(" "));
        return searchForWords.stream().allMatch(listOfWordsInText::contains);
    }

    static boolean containsAllOfWords(String searchIn, String... searchForWords) {
        return containsAllOfWords(searchIn, List.of(searchForWords));
    }

    static String toLowerCase(String str) {
        if (str == null) return null;
        return str.toLowerCase();
    }

    static String[] toLowerCase(String... str) {
        if (str == null || str.length == 0) return str;
        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].toLowerCase();
        }
        return str;
    }

    static List<String> toLowerCase(List<String> list) {
        if (list == null || list.isEmpty()) return list;
        return list.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

}
