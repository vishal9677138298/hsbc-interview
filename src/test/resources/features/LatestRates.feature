@latestExchangeRates
Feature: Latest Foreign Exchange Rates
  As a user of this public API I should be able to get the exchange rates for all the currencies for the current date.
  There should also be a provision to fetch the exchange rates based on a currency against desired currencies

  @smoke @regression
  Scenario: Response code validation
    When making a call to latest rate endpoint
    Then the response code must be 200