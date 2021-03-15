package requestEntities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RequestHandler {

    private Request request;
    private RequestSpecBuilder requestBuilder = new RequestSpecBuilder();

    /**
     * Processes the request by setting the headers, query params, body and HTTP method. Also sends the request to the
     * endpoint thereby logging both the request and response.
     *
     * @param request request object
     * @return a validatable response
     */
    public ValidatableResponse getResponse(Request request){
        RequestHandler handler = new RequestHandler();
        handler.request = request;
        setHeaders(handler.request, handler);
        setQueryParams(handler.request, handler);
        setBody(handler.request);
        HttpMethod method = request.getMethod();
        RequestSpecification rspec = handler.requestBuilder.build();

        switch (method){
            case GET:
                return get(rspec);
            case POST:
                return post(rspec);
            case PUT:
                return put(rspec);
            case DELETE:
                return delete(rspec);
        }

        return null;
    }

    private void setHeaders(Request request, RequestHandler handler){
        if(request.getHeaders()!=null){
            handler.requestBuilder.addHeaders(request.getHeaders());
        }
    }

    private void setQueryParams(Request request, RequestHandler handler){
        if(request.getQueryParams()!=null){
            handler.requestBuilder.addQueryParams(request.getQueryParams());
        }
    }

    private void setBody(Request request){

    }

    /**
     * Fires a GET request and also logs both the request and response
     *
     * @param rspec request specification object
     * @return a validatable response
     */
    private ValidatableResponse get(RequestSpecification rspec){
        return RestAssured.given(rspec).log().all().get().then().log().all();
    }

    /**
     * Fires a POST request and also logs both the request and response
     *
     * @param rspec request specification object
     * @return a validatable response
     */
    private ValidatableResponse post(RequestSpecification rspec){
        return RestAssured.given(rspec).log().all().post().then().log().all();
    }

    /**
     * Fires a PUT request and also logs both the request and response
     *
     * @param rspec request specification object
     * @return a validatable response
     */
    private ValidatableResponse put(RequestSpecification rspec){
        return RestAssured.given(rspec).log().all().put().then().log().all();
    }

    /**
     * Fires a DELETE request and also logs both the request and response
     *
     * @param rspec request specification object
     * @return a validatable response
     */
    private ValidatableResponse delete(RequestSpecification rspec){
        return RestAssured.given(rspec).log().all().delete().then().log().all();
    }

    /**
     * To change the response to a validatable json
     *
     * @param response validatable response
     * @return a validatable json object
     */
    public static JsonPath getResponseAsJsonPath(ValidatableResponse response){
        return response.extract().body().jsonPath();
    }

}
