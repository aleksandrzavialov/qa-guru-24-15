package pages.components;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    @Step("Set date in calendar")
    public void setCalendarDate(int year, String month, String date) {
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(byText(Integer.toString(year))).click();
        $(".react-datepicker__month-select").click();
        $(byText(month)).click();
        String dateUpd =  Integer.parseInt(date) > 9 ? date : "0" + date;
        String dateSelector = ".react-datepicker__day--0" + dateUpd;
        $(dateSelector + "[aria-label *= " + month + "]").click();
    }
}
