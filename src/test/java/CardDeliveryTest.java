import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    @Test
    void shouldWithTrueData() {
        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[name='phone']").setValue("+71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $(byText("Встреча успешно забронирована на"));
        $(byText(date));
    }

    @Test
    void shouldUnfilledForm() {
        open("http://localhost:9999");
        $(withText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения")).shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void shouldWithoutCity() {
        open("http://localhost:9999");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[name='phone']").setValue("+71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения")).shouldHave(exactText("Поле обязательно для заполнения"));

    }
    @Test
    void shouldWithoutName() {
        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[name='phone']").setValue("+71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения")).shouldHave(exactText("Поле обязательно для заполнения"));

    }
    @Test
    void shouldWithoutPhone() {
        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Поле обязательно для заполнения")).shouldHave(exactText("Поле обязательно для заполнения"));

    }
    @Test
    void shouldWithoutDate() {
        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[name='phone']").setValue("+71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Неверно введена дата")).shouldHave(exactText("Неверно введена дата"));

    }

    @Test
    void shouldWithNonExistentCity() {
        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Мухосранск");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(10);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[name='phone']").setValue("+71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Доставка в выбранный город недоступна")).shouldHave(exactText("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldInvalidDate() {
        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[name='phone']").setValue("+71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Заказ на выбранную дату невозможен")).shouldHave(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldNameInLatin() {

        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Putin Vova");
        $("[name='phone']").setValue("+71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.")).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void shouldTelWithoutPlus() {

        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[name='phone']").setValue("71234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldTel12Symbols() {

        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[name='phone']").setValue("+712345678901");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));;
    }
    @Test
    void shouldTel10Symbols() {

        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[name='phone']").setValue("1234567890");
        $("[data-test-id=\"agreement\"]").click();
        $(withText("Забронировать")).click();
        $(withText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.")).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));;
    }
    @Test
    void shouldWithoutAgreement() {

        open("http://localhost:9999");
        $(".input__control[placeholder='Город']").setValue("Воронеж");
        $(".input__control[placeholder='Дата встречи']").doubleClick().sendKeys(Keys.DELETE);
        LocalDate dateOfDelivery = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(dateOfDelivery);
        $(".input__control[placeholder='Дата встречи']").setValue(date);
        $("[data-test-id=\"name\"] [type='text']").setValue("Путин Вова");
        $("[name='phone']").setValue("+71234567890");
        $(withText("Забронировать")).click();
        $(withText("Я соглашаюсь с условиями обработки и использования моих персональных данных")).shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных"));
    }
}