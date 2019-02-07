# Sigopt Java API Client

You can sign up for a Sigopt experiment at https://sigopt.com.

Requirements
============

Sigopt-java requires Java 1.7

Installation
============

### Maven users

Update your project settings (commonly found at `~/.m2/settings.xml`) to include the SigOpt package.

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<settings xsi:schemaLocation='http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd'
xmlns='http://maven.apache.org/SETTINGS/1.0.0' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance'>
  <profiles>
    <profile>
      <repositories>
        <repository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>bintray-sigopt-maven</id>
          <name>bintray</name>
          <url>http://dl.bintray.com/sigopt/maven</url>
        </repository>
      </repositories>
      <pluginRepositories>
        <pluginRepository>
          <snapshots>
            <enabled>false</enabled>
          </snapshots>
          <id>bintray-sigopt-maven</id>
          <name>bintray-plugins</name>
          <url>http://dl.bintray.com/sigopt/maven</url>
        </pluginRepository>
      </pluginRepositories>
      <id>bintray</id>
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>bintray</activeProfile>
  </activeProfiles>
</settings>
```

More information on this step can be found on [BinTray](https://bintray.com/sigopt/maven/sigopt-java/view)

Then, add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.sigopt</groupId>
  <artifactId>sigopt-java</artifactId>
  <version>4.11.0</version>
</dependency>
```


### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.sigopt:sigopt-java:4.11.0"
```

### Others

You'll need to manually install the following JARs:

* The sigopt-java JAR.
* `commons-codec` from <http://repo1.maven.org/maven2/commons-codec/commons-codec/1.7/>.
* [Google Gson](http://code.google.com/p/google-gson/) from <http://repo1.maven.org/maven2/com/google/code/gson/gson/2.2.4/>.
* [OkHttp](http://square.github.io/okhttp/) from <http://repo1.maven.org/maven2/com/squareup/okhttp/okhttp/2.7.5/>.

Getting Started
===============

Sign up for an account at [https://sigopt.com](https://sigopt.com).
In order to use the API, you'll need your API token from the [API tokens page](https://sigopt.com/tokens).

To call the API, instantiate a connection with your token.

```java
import com.sigopt.Sigopt;
Sigopt.clientToken = "CLIENT_TOKEN";
```

## Issuing Requests
Then, you can use the connection to issue API requests. An example creating an experiment and running the
optimization loop:

```java
import java.util.Arrays;
import com.sigopt.Experiment;
Experiment experiment = (
  Experiment.create(
    new Experiment.Builder()
      .name("New Experiment")
      .parameters(Arrays.asList(
        new Parameter.Builder()
          .type("double")
          .name("gamma")
          .bounds(new Bounds.Builder()
            .max(1.0)
            .min(0.0001)
            .build())
          .build()))
      .build())
  .call()
);

Suggestion suggestion = experiment.suggestions().create().call();
double value = evaluateMetric(suggestion);  // Implement this
Observation observation = (
  experiment.observations().create(
    new Observation.Builder()
      .suggestion(suggestion.getId())
      .value(value)
      .build())
  .call()
);
```

For more examples, consult the `samples.md` file in this repository.

## Endpoints

Endpoints are grouped by objects.
For example, endpoints that interact with Experiments are under `Experiment`.
Here are example endpoints, and their corresponding REST endpoint.

```java
Experiment.fetch(id)    // GET /experiments/id
Experiment.list()       // GET /experiments
Experiment.create()     // POST /experiments
Experiment.update()     // PUT /experiments
Experiment.delete(id)   // DELETE /experiments/id
Experiment.deleteList() // DELETE /experiments/id
```

These methods will return an `APIMethodCaller`.
You can add parameters to an `APIMethodCaller` with `.addParam("key", "value")`.
You can also add JSON data to the body of the request with `.data(jsonBody)`.
When you are ready to issue the API call, just call `.call()`.

```java
Experiment.list()
  .addParam("state", "active")
  .call();

Experiment.create()
  .data(new Experiment.Builder().name(...))
  .call();
```

Just like in the resource urls, `suggestions` and `observations` are under `experiments`.
Access these objects with `(new Experiment(ID)).suggestions` and `(new Experiment(ID)).observations`.

```java
(new Experiment(id)).suggestions().fetch(sugg_id)   // GET /experiments/id/suggestions/sugg_id
(new Experiment(id)).suggestions().list()           // GET /experiments/id/suggestions
(new Experiment(id)).suggestions().create()         // POST /experiments/id/suggestions
(new Experiment(id)).suggestions().update()         // PUT /experiments/id/suggestions
(new Experiment(id)).suggestions().delete(sugg_id)  // DELETE /experiments/id/suggestions/sugg_id
(new Experiment(id)).suggestions().deleteList()     // DELETE /experiments/id/suggestions
```

Testing
=======

You must have Maven installed. To run the tests, simply run `mvn test`. You can run particular tests by passing `-D test=Class#method` -- for example, `-D test=APIResourceTest#constructResourceFromJson`.
