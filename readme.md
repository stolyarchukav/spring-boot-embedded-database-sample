**Gas & Water Usage Monitoring Application**

## Usage

#### Run application from source:
~~~~
mvnw spring-bot:run
~~~~

#### API
##### Add measurement
~~~~
curl -H "Content-Type: application/json" --data "{\"userId\" : \"user_1\",\"gas\" : 55.0,\"coldWater\" : 1011.01,\"hotWater\" : 710.777}" http://localhost:8080/measure
~~~~
~~~~json
{
    "id": 3,
    "date": "2019-02-09T15:32:44.417+0000",
    "userId": "user_1",
    "gas": 55,
    "coldWater": 1011.01,
    "hotWater": 710.777
}
~~~~

##### Get list of measurements
~~~~
curl -i -X GET http://localhost:8080/measure?userId=user_1
~~~~
~~~~json
{
    "measurements": [
        {
            "id": 3,
            "date": "2019-02-09T15:45:26.312+0000",
            "userId": "user_1",
            "gas": 500,
            "coldWater": 0,
            "hotWater": 77.34
        },
        {
            "id": 2,
            "date": "2019-02-09T15:45:15.133+0000",
            "userId": "user_1",
            "gas": 0,
            "coldWater": 101.11,
            "hotWater": 10.33
        },
        {
            "id": 1,
            "date": "2019-02-09T15:44:58.373+0000",
            "userId": "user_1",
            "gas": 55,
            "coldWater": 1011.01,
            "hotWater": 710.78
        }
    ],
    "count": 3
}
~~~~

####B uild (compile, unit tests, jar)
~~~~
mvnw clean package
~~~~

#### Integration tests
~~~~
mvnw integration-test
~~~~

#### Postman project
_measure.postman_collection.json_
