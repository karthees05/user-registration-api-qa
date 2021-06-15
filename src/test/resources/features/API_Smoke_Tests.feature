@api_smoke_all_scenarios
Feature: Validate the User API is available to use

  @api_smoke_test_valid_user_data
  Scenario Outline: Validate the POST Request of User registration API
    Given the API end point eligibility-check is available to use
    And API request contains the correct schema structure
    When user POST a request with valid data <name> , <address> , <email>
    Then user gets success response 200 code
    And API response contains the correct schema structure
    And the response contains card type <eligibleCards>

    Examples:
      | name    | address        | email                   | eligibleCards |
      | Boris   | test address 1 | iamboris@testmail.com   | C1            |
      | Angela  | test address 2 | iamangela@testmail.com  | C1, C2        |
      | Theresa | test address 3 | iamtheresa@testmail.com | C2            |

  @api_smoke_test_missing_mandatory_data
  Scenario Outline: Validate the POST Request of User registration API with missing mandatory data
    Given the API end point eligibility-check is available to use
    And API request contains the correct schema structure
    When user POST a request without a valid field <name> , <address> , <email>
    Then user gets success response 400 code

    Examples:
      | name  | address        | email                 |
      |       | test address 1 | iamboris@testmail.com |
      | Boris |                | iamboris@testmail.com |
      | Boris | test address 1 |                       |
      |       |                | iamboris@testmail.com |
      |       | test address 1 |                       |
      | Boris |                |                       |
      |       |                |                       |
