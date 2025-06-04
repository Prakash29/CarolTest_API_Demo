package StepDefinitions.api;

import io.restassured.response.Response;
import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import api.RequestHandler;
public class GetRequestSteps {
	
	Response response;
    RequestHandler requestHandler = new RequestHandler();

    @Given("I send a GET request to the echo API")
    public void i_send_a_get_request_to_the_echo_api() {
        response = requestHandler.sendGetRequest();
    }

    
    
    @Then("I validate response contains path, ip and headers")
    public void i_validate_response() {
        response.then().statusCode(200);
        response.then().body("$", hasKey("path"));
        response.then().body("$", hasKey("ip"));
        response.then().body("headers", notNullValue());    }
}
