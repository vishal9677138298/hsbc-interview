# hsbc-interview
Interview assignmment

## Exchange rates api 
This project is created to validate the [exchange rates api](https://ratesapi.io/documentation/) for several business use cases.

## Prerequisites
- Java 8 or later
- Maven

## Test run
The test scripts can be executed based on the below categories
- Latest exchange rates (@latestExchangeRates)
- Past exchange rates (@pastExchangeRates)
- Smoke (@smoke)
- Regression (@regression)
- Negative (@negative)

To execute the test scripts only for latest exchange rates endpoint below command must be executed
```
mvn clean test -Dcucumber.filter.tags="@latestExchangeRates"
```

To execute the test scripts only for past exchange rates endpoint below command must be executed
```
mvn clean test -Dcucumber.filter.tags="@pastExchangeRates"
```

To execute the smoke test scripts below command must be executed
```
mvn clean test -Dcucumber.filter.tags="@smoke"
```

To execute the regression test scripts below command must be executed
```
mvn clean test -Dcucumber.filter.tags="@regression"
```

To execute only the negative scenarios below command must be executed
```
mvn clean test -Dcucumber.filter.tags="@negative"
```
