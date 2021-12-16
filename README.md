## Val(hiem) power!

Turns on and off an EC2 instance with a React web UI powered by a Micronaut backend.

## Environment Configuration
You'll need to define the following environment variables to run this project:
```
AWS_ACCESS_KEY_ID=my_iam_key_id
AWS_SECRET_ACCESS_KEY=my_iam_secret
AWS_INSTANCE_ID=the_instance_id_to_control
AWS_REGION=region_of_the_instance
```
The IAM account you use will need permission to start, stop, and read the status of the EC2 instance

## Build and run

#### Backend
```
./mvnw clean install
java -jar target/valpower-0.1.jar
```
#### Frontend
```
cd src/main/ui
npm install
npm start
```

## Docker works too...
```
./mvn clean install
docker build .
```

## Infrastructure
Hosted on GCP Cloud Run. Interaction will trigger start/stop in AWS ec2