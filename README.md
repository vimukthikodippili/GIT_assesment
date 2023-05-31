# JIT Demo Project

## Endpoints

### Get Relevant Date

````
curl --location --request GET 'http://localhost:8080/api/service/jit/days/ending-date' \
--header 'Content-Type: application/json' \
--data '{

    "startingDay": "2023-05-30",
    "workingDays": 5
}'
````

### Add public holidays

````
curl --location --request PUT 'http://localhost:8080/api/service/jit/days/update-holidays' \
--header 'Content-Type: application/json' \
--data '{
    "publicHoliday": "2023-05-31"
}'
````

