package ru.pyrinoff.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Random;

interface RandomUtils {

    /**
     * Получить рандомное число с нужным количеством цифр.
     */
    static int randomInt(int digits) {
        return new Random().nextInt(((int) Math.pow(10, digits) - 1) + 1);
    }

    /**
     * Получить рандомное число в заданном диапазоне.
     */
    static int randomInt(int from, int to) {
        if (to < from) throw new IllegalArgumentException("'To' value must be greater than or equal to 'From' value.");
        if (to == from) return to;
        return new Random().nextInt(to - from + 1) + from;
    }

    static String generateIpStartsWith() {
        return LocalDateTime.now(ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("MMddHHmm"))
                .replaceFirst("(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1.$2.$3.$4");
    }

    /**
     * Генерация IP-адреса.
     *
     * @param prefix - часть IP-адреса, с которого должен начинаться IP. Например, "255", "255.255", "255.255.255" или даже null / пустая строка.
     */
    public static String generateIpStartsWith(String prefix) {
        boolean prefixExists = prefix != null && !prefix.isEmpty();
        int alreadyExistsOctets = prefixExists ? prefix.split("\\.").length : 0;
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = alreadyExistsOctets; i < 4; i++) {
            sb.append(random.nextInt(256));
            if (i < 3) {
                sb.append(".");
            }
        }
        return (prefixExists ? prefix + "." : "") + sb;
    }

    /**
     * @param firstPart - первая часть IP-адреса.
     */
    static String generateIpByTime(Integer firstPart) {
        return firstPart
                + "."
                + LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("mm"))
                + "."
                + LocalDateTime.now(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("ss"))
                + "."
                + randomInt(0, 255)
                ;
    }

}
