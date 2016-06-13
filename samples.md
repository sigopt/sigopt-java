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

Authentication is done by setting your client_token.

```java
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
Sigopt.clientToken = "sample_client_token";

String clientId = "1";
Client client = Client.fetch(clientId).call();
```

#### List all experiments for a client

```java
Sigopt.clientToken = "sample_client_token";

String clientId = "1";
List<Experiment> experiments = new Client(clientId).experiments().call();
```


### Experiments

#### Retrieve an Experiment

```java
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = Experiment.fetch(experimentId).call();
```

#### Create an Experiment

```java
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
experiment = experiment.insert(clientId).call();
// Alternatively:
// experiment = Experiment.create(experiment, clientId).call();
```

#### Update an Experiment

```java
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = Experiment.fetch(experimentId).call();

experiment.getMetric().setName("new metric name");
experiment.getParameters().get(0).setBounds(new Bounds(25.0, 5000.0));
experiment = experiment.save().call();
```

#### Delete an Experiment

```java
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or fetch the experiment via api.
experiment.delete().call();
```


### Observations

#### Retrieve the best observation from an experiment

```java
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or fetch the experiment via api.
Observation observation = experiment.bestObservation().call();
```

#### Report an observation for an experiment

```java
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or fetch the experiment via api.
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
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or fetch the experiment via api.
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
Sigopt.clientToken = "sample_client_token";

String experimentId = "1";
Experiment experiment = new Experiment(experimentId); // or fetch the experiment via api.
Suggestion suggestion = experiment.suggestion().call();
```
