package steps;

import io.restassured.response.ValidatableResponse;

import java.util.List;

public class StepsData {
    protected ValidatableResponse response;
    protected List<String> currencies;
    protected String baseCurrency;

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }
}
