package com.github.pyrinoff.utils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public interface CollectionUtil {

    static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // Если значение не найдено
    }

    /**
     * Преобразовать POJO в Map. Можно использовать различными путями.
     */
    public static Map<String, Object> convertToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    /**
     * Проверка, что в полях объекта есть указанная текстовая строка.
     */
    public static boolean isObjectContainsStringValue(String searchData, Object someObject) {
        return convertToMap(someObject).values().stream().anyMatch(oneField -> oneField != null && oneField.toString().contains(searchData));
    }

    static <K, V extends Comparable<? super V>> Map<K, V> sortByValues(Map<K, V> map, boolean ascending) {
        List<Map.Entry<K, V>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
                int res = e1.getValue().compareTo(e2.getValue());
                return ascending ? res : -res; // Handle equal values by considering them not equal
            }
        });

        Map<K, V> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    static <K, V> V getFirstValue(Map<K, V> map) {
        if (map == null || map.isEmpty()) return null;
        Map.Entry<K, V> firstEntry = map.entrySet().iterator().next();
        return firstEntry.getValue();
    }

    static <E> void addToValue(Map<E, Integer> map, E key, int numberToAddToValue) {
        map.merge(key, numberToAddToValue, Integer::sum);
        //Integer currentValue = importanceMap.get(enumValue);
        //if (currentValue == null) importanceMap.put(enumValue, val);
        //else importanceMap.put(enumValue, currentValue + val);
    }

    static <E> void increaseValue(Map<E, Integer> map, E key, int numberToAddToValue) {
        addToValue(map, key, numberToAddToValue);
    }

    static <E> int getRandomIndex(Collection<E> collection) {
        return RandomUtil.randomInt(0, collection.size()-1);
    }

    static <E> E getRandomListElement(List<E> list) throws IndexOutOfBoundsException {
        if(list.isEmpty()) throw new IndexOutOfBoundsException();
        return list.get(getRandomIndex(list));
    }


    // Метод для получения строк с максимальным количеством совпадений
    public static <E> List<E> getKeysWithMaxValueOfValue(Map<E, Integer> mapWithIntegerValue) {
        // Находим максимальное значение Integer
        int maxCount = Collections.max(mapWithIntegerValue.values());

        // Собираем все пути, у которых количество совпадений равно максимальному
        return mapWithIntegerValue.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey) // Извлекаем ключи (пути)
                .collect(Collectors.toList());
    }

    static <E>  int getMaxValueOfIntegerValueMap(Map<E, Integer> mapWithIntegerValue) {
        // Находим максимальное значение Integer
        return Collections.max(mapWithIntegerValue.values());
    }


}
