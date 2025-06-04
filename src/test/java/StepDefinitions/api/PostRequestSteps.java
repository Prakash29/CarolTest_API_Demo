package StepDefinitions.api;
import api.RequestHandler;
import io.cucumber.java.en.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
public class PostRequestSteps {
	Response response;
    RequestHandler requestHandler = new RequestHandler();

    @Given("I send a POST request with order details")
    public void i_send_a_post_request_with_order_details() {
        response = requestHandler.sendPostRequest();
    }

    @Then("I validate response includes customer and payment info")
    public void i_validate_response_fields() {
        response.then().statusCode(anyOf(equalTo(200), equalTo(201)));
        response.then().body("headers.Host", notNullValue());
        response.then().body("body", containsString("Jane Smith"));
        response.then().body("customer.name", equalTo("Jane Smith"));
        response.then().body("payment.method", equalTo("credit_card"));
        response.then().body("items.size()", greaterThan(0));
    }

}
