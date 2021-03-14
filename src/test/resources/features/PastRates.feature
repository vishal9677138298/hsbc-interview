@pastExchangeRates
Feature: Past Foreign Exchange Rates
  As a user of this public API I should be able to get the exchange rates for all the currencies for the past date.
  There should also be a provision to fetch the exchange rates based on a currency against desired currencies


  @smoke @regression
  Scenario: Response code validation
    Given when 2020-03-24 is set as the past date
    When making a call to latest rate endpoint
    Then the response code must be 200

  @smoke @regression
  Scenario: Get exchange rates for current date
    Given when 2020-03-24 is set as the past date
    When making a call to latest rate endpoint
    Then the exchange rates must be fetched for the current date, for various currencies based on EUR

  @smoke @regression
  Scenario: Get exchange rates only for specific currencies
    Given when 2020-03-24 is set as the past date
    When making a call to latest rate api with several currencies as query param
      | USD |
      | INR |
      | AUD |
      | CNY |
    Then the response must only have the exchange rates for the requested currencies

  @smoke @regression
  Scenario: Get exchange rates based on a currency
    Given when 2020-03-24 is set as the past date
    When making a call to latest rate api with USD as the base currency
    Then the response should have rates based on the requested currency

  @smoke @regression
  Scenario: Get exchange rates based on a currency only for specific currencies
    Given when 2020-03-24 is set as the past date
    Given INR is set as the base currency
    When firing a request to latest rate api with the below currency list as query param
      | JPY |
      | IDR |
      | THB |
      | ILS |
    Then the results should have exchange rates for requested currencies based on the requested base currency

  @negative @regression
  Scenario: Invalid currency symbols
    Given when 2020-03-24 is set as the past date
    When making a call to latest rate api with several currencies as query param
      | USD |
      | INR |
      | AUD |
      | INVALID |
    Then response should be 400 with an appropriate error message for INVALID_SYMBOL

  @negative @regression
  Scenario: Invalid base currency
    Given when 2020-03-24 is set as the past date
    When making a call to latest rate api with INVALID as the base currency
    Then response should be 400 with an appropriate error message for INVALID_BASE
