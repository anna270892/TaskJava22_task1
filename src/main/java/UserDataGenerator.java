import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class UserDataGenerator {

    private static final Faker faker = new Faker(new Locale("ru"));

    private UserDataGenerator() {

    }

    public static String generateCity() {
        return faker.address().city();
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

    public static String generateRandomText(int length) {
        return faker.lorem().characters(length);
    }
}