Feature: Foreign Exchange Rates
  As a user of this public API I should be able to get the exchange rates for all the currencies for the
  current date and past date and there should also be a provision to fetch the exchange rates based on a
  currency against desired currencies

  @smoke @regression
  Scenario Outline: Response code validation
    Given when <date> is set as the date
    When making a call to the rates api
    Then the response code must be 200

    @latestExchangeRates
    Examples:
      | date |
      | currentDate |

    @pastExchangeRates
    Examples:
      | date |
      | 2020-03-24 |

  @smoke @regression
  Scenario Outline: Get exchange rates for current or past date
    Given when <date> is set as the date
    When making a call to the rates api
    Then the exchange rates must be fetched for the requested date for various currencies based on EUR

    @latestExchangeRates
    Examples:
      | date |
      | currentDate |

    @pastExchangeRates
    Examples:
      | date |
      | 2020-03-24 |

  @smoke @regression
  Scenario Outline: Get exchange rates only for specific currencies
    Given when <date> is set as the date
    When making a call to the rates api with several currencies as query param
      | USD |
      | INR |
      | AUD |
      | CNY |
    Then the response must only have the exchange rates for the requested currencies

    @latestExchangeRates
    Examples:
      | date |
      | currentDate |

    @pastExchangeRates
    Examples:
      | date |
      | 2020-03-24 |

  @smoke @regression
  Scenario Outline: Get exchange rates based on a currency
    Given when <date> is set as the date
    When making a call to rates api with USD as the base currency
    Then the response should have rates based on the requested currency

    @latestExchangeRates
    Examples:
      | date |
      | currentDate |

    @pastExchangeRates
    Examples:
      | date |
      | 2020-03-24 |

  @smoke @regression
  Scenario Outline: Get exchange rates based on a currency only for specific currencies
    Given when <date> is set as the date
    And INR is set as the base currency
    When firing a request to latest rate api with the below currency list as query param
      | JPY |
      | IDR |
      | THB |
      | ILS |
    Then the results should have exchange rates for requested currencies based on the requested base currency

    @latestExchangeRates
    Examples:
      | date |
      | currentDate |

    @pastExchangeRates
    Examples:
      | date |
      | 2020-03-24 |

  @negative @regression
  Scenario Outline: Invalid currency symbols
    Given when <date> is set as the date
    When making a call to the rates api with several currencies as query param
      | TEST |
      | INR |
      | AUD |
      | INVALID |
    Then response should be 400 with an appropriate error message for INVALID_SYMBOL

    @latestExchangeRates
    Examples:
      | date |
      | currentDate |

    @pastExchangeRates
    Examples:
      | date |
      | 2020-03-24 |

  @negative @regression
  Scenario Outline: Invalid base currency
    Given when <date> is set as the date
    When making a call to rates api with INVALID as the base currency
    Then response should be 400 with an appropriate error message for INVALID_BASE

  @latestExchangeRates
    Examples:
      | date |
      | currentDate |

  @pastExchangeRates
    Examples:
      | date |
      | 2020-03-24 |
