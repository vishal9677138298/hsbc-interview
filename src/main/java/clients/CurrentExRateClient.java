package clients;

import io.restassured.response.ValidatableResponse;
import requestEntities.HttpMethod;
import requestEntities.Request;
import requestEntities.RequestHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class CurrentExRateClient {
    private static RequestHandler reqHandler = new RequestHandler();

    public static ValidatableResponse getCurrentExchangeRates(){
        Request request = new Request();
        request.setMethod(HttpMethod.GET);
        return reqHandler.processRequest(request);
    }

    public static ValidatableResponse getRatesForCurrencies(List<String> currencies){
        Request request = new Request();
        request.setMethod(HttpMethod.GET);
        Map<String, String> queryParams = new HashMap<String, String>();
        StringJoiner currencyList = new StringJoiner(",");
        currencies.forEach(currencyList::add);
        queryParams.put("symbols", currencyList.toString());
        request.setQueryParams(queryParams);
        return reqHandler.processRequest(request);
    }

    public static ValidatableResponse getExRatesBasedOnCurrency(String currency){
        Request request = new Request();
        request.setMethod(HttpMethod.GET);
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("base", currency);
        request.setQueryParams(queryParams);
        return reqHandler.processRequest(request);
    }

    public static ValidatableResponse getExRatesBasedOnCurrencyFor(String baseCurrency, List<String> currencies){
        Request request = new Request();
        request.setMethod(HttpMethod.GET);
        Map<String, String> queryParams = new HashMap<String, String>();
        queryParams.put("base", baseCurrency);
        StringJoiner currencyList = new StringJoiner(",");
        currencies.forEach(currencyList::add);
        queryParams.put("symbols", currencyList.toString());
        request.setQueryParams(queryParams);
        return reqHandler.processRequest(request);
    }
}
