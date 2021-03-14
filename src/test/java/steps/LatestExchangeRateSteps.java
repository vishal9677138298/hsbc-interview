package steps;

import clients.CurrentExRateClient;
import constants.Error;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import org.apache.http.HttpStatus;
import requestEntities.Endpoint;
import requestEntities.RequestHandler;
import utils.DateUtil;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.Assertions.*;

public class LatestExchangeRateSteps {
    private StepsData data;

    public LatestExchangeRateSteps(StepsData data){
        this.data = data;
    }

    @Before("@latestExchangeRates")
    public void setup(){
        Endpoint endpoint = new Endpoint("/latest");
    }

    @Given("making a call to latest rate endpoint")
    public void fireRequest(){
        data.response = CurrentExRateClient.getCurrentExchangeRates();
    }

    @Then("the response code must be 200")
    public void checkResponse(){
        data.response.assertThat().statusCode(HttpStatus.SC_OK);
    }

}
