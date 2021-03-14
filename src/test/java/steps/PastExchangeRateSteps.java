package steps;

import io.cucumber.java.en.Given;
import requestEntities.Endpoint;
import utils.DateUtil;

public class PastExchangeRateSteps {

    @Given("^when (\\d{4}-\\d{2}-\\d{2}) is set as the past date$")
    public void setPastDate(String date){
        Endpoint endpoint = new Endpoint("/"+date);
        new DateUtil().setDate(date);
    }
}
