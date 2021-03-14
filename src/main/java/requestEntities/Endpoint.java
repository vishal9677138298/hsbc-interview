package requestEntities;

import io.restassured.RestAssured;
import utils.PropertyLoader;

public class Endpoint {
    private final String fileName="config.properties";

    public Endpoint(String path){
        RestAssured.baseURI = new PropertyLoader("ExchangeRate", fileName).getProperty("host");
        RestAssured.basePath=path;
    }

}
