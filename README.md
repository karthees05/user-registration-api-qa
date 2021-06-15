# user-api-qa


## Run the API Tests

#### Run the API Tests through Cucumber Runner class

Steps to follow:

- Import gradle Dependencies

- Check the BDD Feature files are available in:

```> src/test/resources/features```

- In IDE Go to **src> test> java> runner> RunCuke**

- Right click and Run by setting the below runtime parameter

The Run time parameter needs to be updated based on which environment we are running.

The following parameters are valid if we run through runner class for local profile
-Dservice.uri=http://localhost:8080 -Dservice.profile=local


#### Run the API Tests through gradle task or Pipeline or standalone Jenkins job

The Cucumber tests can be executed along with a stage of this API tests in Pipeline or Standalone Jenkins job

From IDE's terminal we can use the below parameters to run API scenarios
Template: ./gradle clean cucumber -Dservice.uri=uri to enter -Dservice.profile=environment
Example: ./gradle clean cucumber -Dservice.uri=http://localhost:8080 -Dservice.profile=local

Similarly, we can run for other environments by configuring/providing the above 2 parameters in runtime on Pipeline or standalone Jenkins Job.

So this can be integrated easily with CI/CD Pipeline, and it can be executed whenever we want by adding this in a Pipeline stage.
