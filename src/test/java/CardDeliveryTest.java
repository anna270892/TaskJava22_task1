import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @BeforeEach
    void setUpp() {
        open("http://localhost:7777/");
    }


    //валидная отправка формы
    @Test
    void validSubmissionOne() {
        String city = UserDataGenerator.generateCity();
        String date = UserDataGenerator.generateDate(5);
        String fullName = UserDataGenerator.generateFullName();
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='success-notification'].notification .notification__title").shouldBe(visible, text("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldBe(visible, text("Встреча успешно запланирована на " + date));
    }

    //валидная отправка формы с повторными данными
    @Test
    void validSubmissionTwo() {
        String city = UserDataGenerator.generateCity();
        String date = UserDataGenerator.generateDate(5);
        String fullName = UserDataGenerator.generateFullName();
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='success-notification'].notification .notification__title").shouldBe(visible, text("Успешно!"));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldBe(visible, text("Встреча успешно запланирована на " + date));
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='replan-notification'] .notification__content")
                .shouldBe(visible, text("У вас уже запланирована встреча на другую дату. Перепланировать?"));
    }

    //Отправка формы с валидными данными без отмеченного чекбокса
    @Test
    void validSubmissionWithoutCheckbox() {
        String city = UserDataGenerator.generateCity();
        String date = UserDataGenerator.generateDate(5);
        String fullName = UserDataGenerator.generateFullName();
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='agreement'].input_invalid").shouldHave(text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }

    //Отправка пустой формы без отмеченного чекбокса
    @Test
    void emptyFormWithoutCheckbox() {
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("");
        $("[data-test-id='name'] .input__control").setValue("");
        $("[data-test-id='phone'] input").setValue("");
        $(".button.button_view_extra").click();
        $("[data-test-id='city'].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
    }

    //Отправка пустой формы с отмеченным чекбоксом
    @Test
    void emptyFormWithCheckbox() {
        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("");
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra").click();
        $("[data-test-id='city'].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
    }

    //Отправка формы с пустым полем "Город"
    @Test
    void validSubmissionWithoutCity() {
        String date = UserDataGenerator.generateDate(5);
        String fullName = UserDataGenerator.generateFullName();
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue("");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='city'].input_invalid").shouldHave(text("Поле обязательно для заполнения"));
    }

    //Отправка формы с не валидным значением поля "Город"
    @Test
    void submittingAFormWithAnInvalidCity() {
        String date = UserDataGenerator.generateDate(5);
        String fullName = UserDataGenerator.generateFullName();
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue("qwERTY");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    //Отправка формы с двумя городами в поле "Город"
    @Test
    void submittingAFormWithAnInvalidTwoCity() {
        String date = UserDataGenerator.generateDate(5);
        String fullName = UserDataGenerator.generateFullName();
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue("Москва Питер");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='city'].input_invalid .input__sub").shouldHave(text("Доставка в выбранный город недоступна"));
    }

    //Отправка формы с пустым полем "Дата"
    @Test
    void validSubmissionWithoutDate() {
        String city = UserDataGenerator.generateCity();
        String fullName = UserDataGenerator.generateFullName();
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("");
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(text("Неверно введена дата"));
    }

    //Отправка формы с несуществующей датой в поле "Дата"
    @Test
    void submittingAFormWithANonExistentDate() {
        String city = UserDataGenerator.generateCity();
        String fullName = UserDataGenerator.generateFullName();
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("35.15.2050");
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(text("Неверно введена дата"));
    }

    //Отправка формы с прошедшей датой в поле "Дата"
    @Test
    void submittingAFormWithAPastDate() {
        String city = UserDataGenerator.generateCity();
        String fullName = UserDataGenerator.generateFullName();
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue("27.11.2023");
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='date'] .input_invalid .input__sub").shouldHave(text("Заказ на выбранную дату невозможен"));
    }

    //Отправка формы с пустым полем "ФИО"
    @Test
    void submittingAFormWithAnEmptyNameField() {
        String city = UserDataGenerator.generateCity();
        String date = UserDataGenerator.generateDate(5);
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue("");
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }

    //Отправка формы с не разрешенными символами "ФИО"
    @Test
    void submittingAFormWithInvalidData() {
        String city = UserDataGenerator.generateCity();
        String date = UserDataGenerator.generateDate(5);
        String phoneNumber = UserDataGenerator.generatePhoneNumber();

        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue("QWERty");
        $("[data-test-id='phone'] input").setValue(phoneNumber);
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    //Отправка формы с пустым полем "Телефон"
    @Test
    void validSubmissionWithoutPhone() {
        String city = UserDataGenerator.generateCity();
        String date = UserDataGenerator.generateDate(5);
        String fullName = UserDataGenerator.generateFullName();

        $("[data-test-id='city'] input").setValue(city);
        $("[data-test-id='date'] .input__control").sendKeys(Keys.CONTROL + "a");
        $("[data-test-id='date'] .input__control").sendKeys(Keys.DELETE);
        $("[data-test-id='date'] .input__control").setValue(date);
        $("[data-test-id='name'] .input__control").setValue(fullName);
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='phone'] .input__sub").shouldBe(visible, text("На указанный номер моб. тел. будет " +
                "отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
        $("[data-test-id='agreement'] .checkbox__box").click();
        $(".button.button_view_extra .button__content").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(text("Поле обязательно для заполнения"));
    }
}



