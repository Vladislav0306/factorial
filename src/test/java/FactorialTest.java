import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FactorialTest {
    @BeforeEach
    void setUp() {
        open("https://qainterview.pythonanywhere.com/");
    }

    @Test
    @DisplayName("1. Вычисление факториала 1")
    public void factorialCalculation1() {
        $x("//*[@id=\"number\"]").setValue("1");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("The factorial of 1 is: 1"));
    }

    @Test
    @DisplayName("2. Вычисление факториала 5")
    public void factorialCalculation5() {
        $x("//*[@id=\"number\"]").setValue("5");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("The factorial of 5 is: 120"));
    }

    @Test
    @DisplayName("3. Вычисление факториала 170")
    public void factorialCalculation170() {
        $x("//*[@id=\"number\"]").setValue("170");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("The factorial of 170 is: 7.257415615307999e+306"));
    }

    @Test
    @DisplayName("4. Вычисление факториала 171")
    public void factorialCalculation171() {
        $x("//*[@id=\"number\"]").setValue("171");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("The factorial of 171 is: Infinity"));
    }

    @Test
    @DisplayName("5. Вычисление факториала 0")
    public void factorialCalculation0() {
        $x("//*[@id=\"number\"]").setValue("0");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("The factorial of 0 is: 1"));
    }

    @Test
    @DisplayName("6. Вычисление факториала отрицательного числа")
    public void calculatingTheFactorialOfANegativeNumber() {
        $x("//*[@id=\"number\"]").setValue("-1");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText(""));
        // Обнаружен баг: https://github.com/Vladislav0306/factorial/issues/1
    }

    @Test
    @DisplayName("7. Вычисление факториала вещественного числа")
    public void calculatingTheFactorialOfARealNumber() {
        $x("//*[@id=\"number\"]").setValue("1.1");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("Please enter an integer"));
    }

    @Test
    @DisplayName("8. Пустая строка при вычислении факториала")
    public void emptyStringWhenCalculatingFactorial() {
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("Please enter an integer"));
    }

    @Test
    @DisplayName("9. Ввод букв при вычислении факториала")
    public void enteringLettersWhenCalculatingFactorial() {
        $x("//*[@id=\"number\"]").setValue("абвгд");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("Please enter an integer"));
    }

    @Test
    @DisplayName("10. Ввод спецсимволов при вычислении факториала")
    public void enteringSpecialCharactersWhenCalculatingFactorial() {
        $x("//*[@id=\"number\"]").setValue("~!@#$");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("Please enter an integer"));
    }

    @Test
    @DisplayName("11. Ввод XSS-инъекции при вычислении факториала")
    public void enteringXSSInjectionWhenCalculatingFactorial() {
        $x("//*[@id=\"number\"]").setValue("<script>alert(123)</script>");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("Please enter an integer"));
    }

    @Test
    @DisplayName("12. Ввод SQL-инъекции при вычислении факториала")
    public void enteringSQLInjectionWhenCalculatingFactorial() {
        $x("//*[@id=\"number\"]").setValue("1 or sleep(5)#");
        $x("//*[@id=\"getFactorial\"]").click();
        $x("//*[@id=\"resultDiv\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("Please enter an integer"));
    }

    @Test
    @DisplayName("13. Переход на страницу \"Terms and Conditions\"")
    public void goToTheTermsAndConditionsPage() {
        $x("//*[@href=\"/privacy\"]").click();
        $x("//*[body=\"This is the privacy document. We are not yet ready with it. Stay tuned!\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("This is the privacy document. We are not yet ready with it. Stay tuned!"));
        // Обнаружен баг: https://github.com/Vladislav0306/factorial/issues/2
    }

    @Test
    @DisplayName("14. Переход на страницу \"Privacy\"")
    public void goToThePrivacyPage() {
        $x("//*[@href=\"/terms\"]").click();
        $x("//*[body=\"This is the terms and conditions document. We are not yet ready with it. Stay tuned!\"]").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("This is the terms and conditions document. We are not yet ready with it. Stay tuned!"));
        // Обнаружен баг: https://github.com/Vladislav0306/factorial/issues/3
    }

    @Test
    @DisplayName("15. Переход на страницу \"Qxf2 Services\"")
    public void goToTheQxf2ServicesPage() {
        $x("//*[@href=\"https://www.qxf2.com/?utm_source=qa-interview&utm_medium=click&utm_campaign=From%20QA%20Interview\"]").click();
        $x("/html/body/div[2]/div[6]/h1").shouldBe(visible, Duration.ofSeconds(11)).shouldHave(exactText("We'll lay the foundation for great testing at your startup"));
    }
}
