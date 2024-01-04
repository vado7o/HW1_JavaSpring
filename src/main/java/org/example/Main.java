package org.example;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        if (getVariant() == 1) {
            System.out.println("До следующего Нового года осталось " + getDaysForNY() + " дней !!!");
        } else System.out.println("По сегодняшний день Вы прожили " + getDaysFromBirth() + " дня !!!");
    }

    public static int getVariant() {
        int variant;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Что бы Вы хотели посчитать: 1 - Количество дней до Нового года; 2 - Количетсво прожитых Вами дней ???");
        while (true) {
            try {
                System.out.println("Введите 1 или 2:\n");
                variant = Integer.parseInt(br.readLine());
                if (variant == 1 || variant == 2) break;
            } catch (NumberFormatException | IOException e) {
                System.out.println("Ошибка ввода числа!!!");
            }
        }
        return variant;
    }

    public static int getDaysForNY() {
        DateTime newYear = DateTime.now().plusYears(1).withDayOfYear(1);
        /** далее я использую библиотеку JODA-TIME (прописана мною в build.gradle) */
        return org.joda.time.Days.daysBetween(DateTime.now(), newYear).getDays();
    }

    public static int getDaysFromBirth() {
        final DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Введите дату Вашего рождения в формате \"дд/мм/гггг\":\n");
            try {
                String input = br.readLine();
                DateTime date = formatter.parseDateTime(input);
                /** далее я использую библиотеку JODA-TIME (прописана мною в build.gradle) */
                return org.joda.time.Days.daysBetween(date, DateTime.now()).getDays();
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("Неправильно введена дата рождения !!!");
            }
        }
    }
}