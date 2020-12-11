# Software Engineering API Integration Test - Courtney Peverley's Implementation

## How to Run the API

### Start the server:

```
mvn spring-boot:run
```

### Acquire an access token (same procedure as usual):

```
curl -s --insecure -u CLIENT_ID:CLIENT_SECRET https://auth.qa.fitpay.ninja/oauth/token?grant_type=client_credentials
```

### Access the new composite API using a userId in the URL (as well as the token from above). creditCardState and deviceState are optional:
```
curl -s --insecure -H "Authorization: Bearer TOKEN" "localhost:8080/compositeUsers/:userId?creditCardState=CCSTATE&deviceState=DS"
```
  
## Running the Unit Tests

### Run the Maven test command:

```
mvn test
```
