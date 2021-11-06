## Val(hiem) power!

Turns on and off an EC2 instance with a React web UI powered by a Micronaut backend.

## Environment Configuration
Before you run it, make sure to set AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY environment variables.
Your IAM account needs permission to read EC2 status, start, and stop instances

## How to run it
Change Constants.java to your region and instance-id. (TODO micronaut properties)
```
./mvnw clean install
java -jar valpower-0.1.jar
```

## Docker works too...
```
./mvn clean install
docker build .
```
