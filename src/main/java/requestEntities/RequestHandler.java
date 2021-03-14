package requestEntities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RequestHandler {

    private Request request;
    private RequestSpecBuilder requestBuilder = new RequestSpecBuilder();

    public ValidatableResponse processRequest(Request request){
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

    private ValidatableResponse get(RequestSpecification rspec){
        return RestAssured.given(rspec).log().all().get().then().log().all();
    }

    private ValidatableResponse post(RequestSpecification rspec){
        return RestAssured.given(rspec).log().all().post().then().log().all();
    }

    private ValidatableResponse put(RequestSpecification rspec){
        return RestAssured.given(rspec).log().all().put().then().log().all();
    }

    private ValidatableResponse delete(RequestSpecification rspec){
        return RestAssured.given(rspec).log().all().delete().then().log().all();
    }

    public static JsonPath getResponseAsJsonPath(ValidatableResponse response){
        return response.extract().body().jsonPath();
    }

}
