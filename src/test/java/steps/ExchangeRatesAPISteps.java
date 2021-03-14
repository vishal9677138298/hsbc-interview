package steps;

import clients.CurrentExRateClient;
import constants.Error;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import requestEntities.Endpoint;
import requestEntities.RequestHandler;
import utils.DateHandler;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

public class ExchangeRatesAPISteps {
    private StepsData data;

    public ExchangeRatesAPISteps(StepsData data){
        this.data = data;
    }

    @Given("^when (currentDate|\\d{4}-\\d{2}-\\d{2}) is set as the date$")
    public void setEndpoint(String date) {
        if (date.equals("currentDate")) {
            new Endpoint("/latest");
            data.setDateHandler(new DateHandler());
        } else {
            new Endpoint("/" + date);
            data.setDateHandler(new DateHandler().setDate(date));
        }
    }

    @When("making a call to the rates api")
    public void fireRequest(){
        data.response = CurrentExRateClient.getCurrentExchangeRates();
    }

    @Then("the response code must be 200")
    public void checkResponse(){
        data.response.assertThat().statusCode(HttpStatus.SC_OK);
    }


    @Then("^the exchange rates must be fetched for the requested date for various currencies based on (\\w+)$")
    public void theExchangeRatesMustBeFetchedForTheCurrentDate(String baseCurrency) {
        JsonPath jsonResponse = RequestHandler.getResponseAsJsonPath(data.response);
        assertThat(jsonResponse.getString("base"), is(equalTo(baseCurrency)));
        assertThat(jsonResponse.getString("date"), is(equalTo(data.getDateHandler().date)));
        Map<String, Float> rates = jsonResponse.getMap("rates");
        assertThat(rates.keySet().size(), is(greaterThan(0)));
        rates.forEach((currency, exRate) -> {
            assertThat(currency, is(instanceOf(String.class)));
            assertThat(exRate, is(instanceOf(Float.class)));
        });
    }


    @When("^making a call to the rates api with several currencies as query param$")
    public void makingACallToLatestRateApiWithSeveralCurrenciesAsQueryParam(List<String> currencies) {
        data.response = CurrentExRateClient.getRatesForCurrencies(currencies);
        data.setCurrencies(currencies);
    }

    @Then("the response must only have the exchange rates for the requested currencies")
    public void theResponseMustOnlyHaveTheExchangeRatesForTheRequestedCurrencies() {
        JsonPath response = RequestHandler.getResponseAsJsonPath(data.response);
        Map<String, Float> rates = response.getMap("rates");
        assertThat(rates.keySet()).hasSameElementsAs(data.currencies);
        rates.forEach((currency, exchangeRate) -> assertThat(exchangeRate, is(instanceOf(Float.class))));
        assertThat(response.getString("date"), is(equalTo(data.getDateHandler().date)));
    }


    @When("^making a call to rates api with (\\w+) as the base currency$")
    public void makingACallToLatestRateApiWithUSDAsTheBaseCurrency(String baseCurrency) {
        data.response = CurrentExRateClient.getExRatesBasedOnCurrency(baseCurrency);
        data.setBaseCurrency(baseCurrency);
    }

    @Then("the response should have rates based on the requested currency")
    public void theResponseShouldHaveRatesBasedOnTheRequestedCurrency() {
        data.response.assertThat().body("base", is(equalTo(data.baseCurrency)));
        data.response.assertThat().body("date", is(equalTo(data.getDateHandler().date)));
    }

    @Given("^(\\w+) is set as the base currency")
    public void inrIsSetAsTheBaseCurrency(String baseCurrency) {
        data.setBaseCurrency(baseCurrency);
    }

    @When("firing a request to latest rate api with the below currency list as query param")
    public void firingARequestToLatestRateApiWithACurrencyListAsQueryParam(List<String> currencyList) {
        data.response = CurrentExRateClient.getExRatesBasedOnCurrencyFor(data.baseCurrency, currencyList);
        data.setCurrencies(currencyList);
    }

    @Then("the results should have exchange rates for requested currencies based on the requested base currency")
    public void theResultsShouldHaveExchangeRatesForRequestedCurrenciesBasedOnTheRequestedBaseCurrency() {
        JsonPath response = RequestHandler.getResponseAsJsonPath(data.response);
        Map<String, Float> rates = response.getMap("rates");
        assertThat(rates.keySet()).hasSameElementsAs(data.currencies);
        rates.forEach((currency, exchangeRate) -> assertThat(exchangeRate, is(instanceOf(Float.class))));
        assertThat(response.getString("date"), is(equalTo(data.getDateHandler().date)));
        assertThat(response.getString("base"), is(equalTo(data.baseCurrency)));
    }

    @Then("^response should be 400 with an appropriate error message for (\\w+)$")
    public void responseShouldBeWithAnAppropriateErrorMessage(String error) {
        Error errorType = Error.valueOf(error);
        String errorMsg = null;

        switch (errorType){
            case INVALID_BASE:
                errorMsg = String.format(errorType.getErrorMessage(), data.baseCurrency);
                break;
            case INVALID_SYMBOL:
                StringJoiner currencyListString = new StringJoiner(",");
                data.currencies.forEach(currencyListString::add);
                errorMsg = String.format(errorType.getErrorMessage(), currencyListString);
                break;
        }

        data.response.assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .body("error", containsString(errorMsg));
    }

}
