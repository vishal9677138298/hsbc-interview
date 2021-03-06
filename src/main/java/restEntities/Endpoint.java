package restEntities;

import io.restassured.RestAssured;
import utils.PropertyLoader;

public class Endpoint {
    private final String fileName="config.properties";

    /**
     * Sets the BaseURI and Base Path
     *
     * @param api name of the api which is present as key in config.properties files
     * @param path basePath for the api endpoint
     */
    public Endpoint(String api, String path){
        RestAssured.baseURI = new PropertyLoader(api, fileName).getProperty("host");
        RestAssured.basePath=path;
    }

}
