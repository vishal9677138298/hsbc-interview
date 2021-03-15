package requestEntities;

import io.restassured.RestAssured;
import utils.PropertyLoader;

public class Endpoint {
    private final String fileName="config.properties";

    /**
     * Sets the BaseURI and Base Path
     *
     * @param path basePath for the api endpoint
     */
    public Endpoint(String path){
        RestAssured.baseURI = new PropertyLoader("ExchangeRate", fileName).getProperty("host");
        RestAssured.basePath=path;
    }

}
