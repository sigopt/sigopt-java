# Sigopt Java Bindings

You can sign up for a Sigopt experiment at https://sigopt.com.

Requirements
============

Sigopt-java supports Android 2.3 and above. For Java, the minimum requirement is 1.7.

Installation
============

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
  <groupId>com.sigopt</groupId>
  <artifactId>sigopt-java</artifactId>
  <version>1.0.0</version>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "com.sigopt:sigopt-java:1.0.0"
```

### Others

You'll need to manually install the following JARs:

* The Sigopt JAR from https://github.com/sigopt/sigopt-java/releases/latest
* [Google Gson](http://code.google.com/p/google-gson/) from <http://google-gson.googlecode.com/files/google-gson-2.2.4-release.zip>.
* [OkHttp](http://square.github.io/okhttp/) from <https://search.maven.org/remote_content?g=com.squareup.okio&a=okio&v=LATEST>.

Testing
=======

You must have Maven installed. To run the tests, simply run `mvn test`. You can run particular tests by passing `-D test=Class#method` -- for example, `-D test=APIResourceTest#constructResourceFromJson`.
