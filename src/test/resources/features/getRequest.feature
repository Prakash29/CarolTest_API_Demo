Feature: GET API Testing

  Scenario: Validate GET API response
    Given I send a GET request to the echo API
    Then I validate response contains path, ip and headers