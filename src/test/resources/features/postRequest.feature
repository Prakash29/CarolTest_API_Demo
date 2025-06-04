Feature: POST API Testing

  Scenario: Validate POST API response with order payload
    Given I send a POST request with order details
    Then I validate response includes customer and payment info