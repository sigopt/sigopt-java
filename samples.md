# Sigopt Java API Lib Samples & Info

## Models / Resources

### All models have builders and constructors

Every model has a constructor that expect all params, or if you want to only use some params you can use a builder. Eg:

```java
Parameter p1 = new Parameter("param1", "double", new Bounds(0.0, 10.0), new ArrayList<CategoricalValue>(), null, null);
```

```java
Parameter p2 = new Parameter.Builder()
    .name("fake-param")
    .type("double")
    .bounds(new Bounds(0.0, 10.0))
	.build();
```

The builders tend to be more practical in Java (since you rarely set every possibly attribute), but either works.

### All models with an ID have a constructor that just takes the id

For example, you can create a new Experiment with the following:

```java
Experiment e = new Experiment("id-here");
```

The purpose of this is to facilitate with nested resource lookups. Eg:

```java
Observation bestObs = new Experiment("1").bestObservation().call();
```

## Authentication

Authentication is done by setting your user_token and/or client_token.

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";
```

Once these are set they will be used for all API calls. Example code shows this being done for every sample, but only to create a completely functional and isolated sample. You do *not* need to set these every API call.


## API Calls vs Getters

All methods that are getters begin with `get`. Eg `getId()` would get an id. Anything that **does not** start with `get` is likely an API call, which will return an `APIMethodCaller<T>`.

To proceed with the api call, simply use the `.call()` method on the `APIMethodCaller<T>` and it will return an object of type `T`. This is done so it is simpler to add params and headers to API calls as necessary. Eg, to add an extra param to a call you could do the following:

```java
// Assume experiment is already initialized with data
Experiment.create(new Experiment(), "client_id")
	.addHeader("Some Header", "custom-value")
	.addParam("other_data", "some-other-param")
	.call();
```

This makes it easy to add params before an API call is made without forcing developers to build maps for everything.



## Code Samples for API endpoints


### Clients

#### Retrieve a client

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String clientId = "1";
Client client = Client.retrieve(clientId).call();
```

#### List all experiments for a client

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String clientId = "1";
List<Experiment> experiments = new Client(clientId).experiments().call();
```


### Experiments

#### Retrieve an Experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = Experiment.retrieve(experimentId).call();
```

#### Create an Experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String clientId = "1";

Parameter parameter = new Parameter.Builder()
    .name("Price Per Month")
    .type("double")
    .bounds(new Bounds(10.0, 100.0))
    .build();
List<Parameter> params = new ArrayList<Parameter>();
params.add(parameter);

Metric metric = new Metric("Total Revenue");

Experiment experiment = new Experiment.Builder()
    .type("offline")
    .name("Offline Experiment")
    .parameters(params)
    .metric(metric)
    .build();
experiment = experiment.create(clientId).call();
// Alternatively:
// experiment = Experiment.create(experiment, clientId).call();
```

#### Create an offline_cohort Experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String clientId = "1";

Parameter parameter1 = new Parameter.Builder()
    .name("Price Per Month")
    .type("double")
    .bounds(new Bounds(10.0, 100.0))
    .build();
Parameter parameter2 = new Parameter.Builder()
    .name("Experiments Per Month")
    .type("int")
    .bounds(new Bounds(10.0, 100.0))
    .build();
List<Parameter> params = new ArrayList<Parameter>();
params.add(parameter1);
params.add(parameter2);

Suggestion suggestion1 = new Suggestion.Builder()
    .addAssignment("Price Per Month", 55.5)
    .addAssignment("Experiments Per Month", 20)
    .build();
Cohort cohort1 = new Cohort.Builder()
    .name("First Cohort")
    .suggestion(suggestion1)
    .allocation(0.25)
    .successes(5)
    .attempts(10)
    .state("active")
    .build();

Suggestion suggestion2 = new Suggestion.Builder()
    .addAssignment("Price Per Month", 45.0)
    .addAssignment("Experiments Per Month", 15)
    .build();
Cohort cohort2 = new Cohort.Builder()
    .name("Second Cohort")
    .suggestion(suggestion2)
    .allocation(0.75)
    .successes(100)
    .attempts(105)
    .state("active")
    .build();
List<Cohort> cohorts = new ArrayList<Cohort>();
cohorts.add(cohort1);
cohorts.add(cohort2);

Metric metric = new Metric("Total Revenue");

Experiment experiment = new Experiment.Builder()
    .type("offline_cohort")
        .name("Offline Cohort Experiment")
        .parameters(params)
        .metric(metric)
        .cohorts(cohorts)
    .build();
experiment = experiment.create(clientId).call();
```

#### Look up all Experiments

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String clientId = "1";
List<Experiment> experiments = Experiment.all(clientId).call();
```

#### Update an Experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = Experiment.retrieve(experimentId).call();

experiment.getMetric().setName("new metric name");
experiment.getParameters().get(0).setBounds(new Bounds(25.0, 5000.0));
experiment = experiment.save().call();
```

#### Reset an Experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or retrieve the experiment via api.
experiment.reset().call();
```

#### Delete an Experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or retrieve the experiment via api.
experiment.delete().call();
```


### Cohorts

#### List all Cohorts for an experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or retrieve the experiment via api.
List<Cohort> cohorts = experiment.cohorts().call();
// or if you want to match your existing docs:
// List<Cohort> cohorts = experiment.allocate().call();
```

#### Create a Cohort

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Suggestion suggestion = new Suggestion.Builder()
    .addAssignment("Price Per Month", 55.0)
    .addAssignment("Experiments Per Month", 15)
    .build();
Cohort cohort = new Cohort.Builder()
    .name("Fifth Cohort")
    .suggestion(suggestion)
    .allocation(0.1)
    .successes(18)
    .attempts(25)
    .state("active")
    .build();

Cohort created = Cohort.create(cohort, experimentId).call();
// Alternatives:
// Cohort created = cohort.create(experimentId).call();
// Cohort created = new Experiment(experimentId).createCohort(cohort).call();
```

#### Update a Cohort

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";

Cohort cohort = experiment.cohorts().call().get(0); // get the first cohort
cohort.setName("new name");
cohort.setState("inactive");
Cohort updated = cohort.save(experimentId).call();

// Alternatively
// Experiment experiment = new Experiment(experimentId);
// Cohort updated = experiment.updateCohort(cohort).call();
```



### Observations

#### Retrieve the best observation from an experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or retrieve the experiment via api.
Observation observation = experiment.bestObservation().call();
```

#### Report an observation for an experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or retrieve the experiment via api.
Observation observation = new Observation.Builder()
    .addAssignment("Price Per Month", 40)
    .value(500.0)
    .build();
experiment.report(observation).call();

// You can also add things like worker_id like this:
experiment.report(observation).addParam("worker_id", workerId).call();
```

#### Report multiple observations for an experiment

This is identical to reporting one observation. Just pass in multiple and the lib handles everything else.

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or retrieve the experiment via api.
Observation observation = new Observation.Builder()
    .addAssignment("Price Per Month", 45)
    .value(750.0)
    .build();
Observation observation2 = new Observation.Builder()
    .addAssignment("Price Per Month", 55)
    .value(900.0)
    .build();
experiment.report(observation, observation2).call();
```


### Suggestions

#### Retrieve a Suggestion from an Experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or retrieve the experiment via api.
Suggestion suggestion = experiment.suggestion().call();
```

#### Retrieve multiple suggestions from an "on_demand" Experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or retrieve the experiment via api.
List<Suggestion> suggestions = experiment.suggestions(5).call();
```


### Workers


#### List all current workers for an Experiment

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId);
List<Worker> workers = experiment.workers().call();
```

#### Delete a worker from an experiment

This released a worker and frees up all suggestions it claimed to be taken by other workers.

```java
Sigopt.userToken = "sample_user_token";
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
String workerId = "super-hard-worker";
Experiment experiment = new Experiment(experimentId);

// Make sure we have a worker to release
Suggestion suggestion = experiment.suggestion()
    .addParam("worker_id", workerId).call();

List<Worker> workers = experiment.workers().call();
Worker toRelease = workers.get(0);
toRelease.release(experimentId).call();
```
