package steps;

import io.restassured.response.ValidatableResponse;
import utils.DateUtil;

import java.util.List;

public class StepsData {
    protected ValidatableResponse response;
    protected List<String> currencies;
    protected String baseCurrency;
    protected DateUtil dateHandler;

    public DateUtil getDateHandler() {
        return dateHandler;
    }

    public void setDateHandler(DateUtil dateHandler) {
        this.dateHandler = dateHandler;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }
}
