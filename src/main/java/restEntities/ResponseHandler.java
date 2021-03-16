package restEntities;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

public class ResponseHandler {

    /**
     * To change the response to a json
     *
     * @param response validatable response
     * @return a validatable json object
     */
    public static JsonPath getResponseAsJsonPath(ValidatableResponse response){
        return response.extract().body().jsonPath();
    }

    /**
     * To change the response to a string
     *
     * @param response validatable response
     * @return a validatable json object
     */
    public static String getResponeAsString(ValidatableResponse response){
        return response.extract().body().asString();
    }

}
