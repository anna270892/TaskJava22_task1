package org.example.delivery.dataGenerator;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class UserDataGenerator {

    private static final Faker faker = new Faker(new Locale("ru"));

    private UserDataGenerator() {

    }

    public static String generateCity() {
        var validCities = new String[]{"Санкт-Петербург", "Москва", "Новосибирск", "Воронеж", "Великий Новгород", "Йошкар-Ола",
                "Саратов", "Краснодар", "Калининград", "Тверь", "Чебоксары"};
        return validCities[new Random().nextInt(validCities.length)];
    }

    public static String generateDate(int daysToAdd) {
        LocalDate currentDate = LocalDate.now();
        LocalDate futureDate = currentDate.plusDays(daysToAdd);
        return futureDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String generateFullName() {
        return faker.name().fullName();
    }

    public static String generatePhoneNumber() {
        return faker.phoneNumber().subscriberNumber(10);
    }

}