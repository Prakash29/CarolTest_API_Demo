package api;

import io.restassured.response.Response;


import static io.restassured.RestAssured.*;
public class RequestHandler {

	private static final String BASE_GET_ENDPOINT = "https://echo.free.beeceptor.com/sample-request?author=beeceptor";
    private static final String BASE_POST_ENDPOINT = "http://echo.free.beeceptor.com/sample-request?author=beeceptor";

    // GET Request 
    public Response sendGetRequest() {
        return given()
                .when()
                .get(BASE_GET_ENDPOINT);
    }

    // POST Request 
    public Response sendPostRequest() {
        return given()
                .header("Content-Type", "application/json")
                .body(getOrderPayload())
                .when()
                .post(BASE_POST_ENDPOINT);
    }

  
    public String getOrderPayload() {
        return "{"
            + "\"order_id\": \"12345\","
            + "\"customer\": {"
            + "\"name\": \"Jane Smith\","
            + "\"email\": \"janesmith@example.com\","
            + "\"phone\": \"1-987-654-3210\","
            + "\"address\": {"
            + "\"street\": \"456 Oak Street\","
            + "\"city\": \"Metropolis\","
            + "\"state\": \"NY\","
            + "\"zipcode\": \"10001\","
            + "\"country\": \"USA\""
            + "}"
            + "},"
            + "\"items\": ["
            + "{"
            + "\"product_id\": \"A101\","
            + "\"name\": \"Wireless Headphones\","
            + "\"quantity\": 1,"
            + "\"price\": 79.99"
            + "},"
            + "{"
            + "\"product_id\": \"B202\","
            + "\"name\": \"Smartphone Case\","
            + "\"quantity\": 2,"
            + "\"price\": 15.99"
            + "}"
            + "],"
            + "\"payment\": {"
            + "\"method\": \"credit_card\","
            + "\"transaction_id\": \"txn_67890\","
            + "\"amount\": 111.97,"
            + "\"currency\": \"USD\""
            + "},"
            + "\"shipping\": {"
            + "\"method\": \"standard\","
            + "\"cost\": 5.99,"
            + "\"estimated_delivery\": \"2024-11-15\""
            + "},"
            + "\"order_status\": \"processing\","
            + "\"created_at\": \"2024-11-07T12:00:00Z\""
            + "}";
    }
}
