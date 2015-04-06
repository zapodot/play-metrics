play-metrics
============

[Metrics](https://github.com/codahale/metrics) plugin for the [Play Framework](http://www.playframework.com/) 2.1.X [![Build Status](https://travis-ci.org/zapodot/play-metrics.png?branch=master)](https://travis-ci.org/zapodot/play-metrics)

**This project is not actively maintained. Feel free to fork this repo and continue this effort if you actually need it**

Quickstart
==========

Add dependency
--------------

A snapshot version of the plugin has been uploaded to the Sonatype OSS snapshot repository.

Add the following to the "appDependencies" sequence in your project/Build.scala file:
```scala
"org.zapodot" % "play-metrics" %% "1.0-SNAPSHOT"
```

Add metrics
-----------
### Add @Metered to your controllers
You can either put it on your Controller class, thus enabling it for all controller methods in that class
or on the individual methods you wish to Time.
```java
@org.zapodot.metrics.Timed
@org.zapodot.metrics.Metered
public static Result index() {
    return ok(index.render("Your new application is ready."));
}
```
### Alternative: add the MetricsSetup Global to your configuration
If your project has not defined its own ``Global`` object, you may use the one provided by this plugin.
Add the following to your ``application.conf``-file
```
application.global=org.zapodot.metrics.MetricsSetup
```
This will intercept all requests made to valid URL-s in your application and time them. It will also intercept the normal
error handling by counting all Throwables that occur in your application. 
Note: this has been enabled in the ```samples/scala``` application.

### Expose the metrics as JSON
Add the following to your `routes` file to expose a JSON view of the metrics gathered by this plugin
```
GET     /metrics/timers             org.zapodot.controllers.metrics.MetricsController.timers()

GET     /metrics/meters             org.zapodot.controllers.metrics.MetricsController.meters()

GET     /metrics/counters           org.zapodot.controllers.metrics.MetricsController.counters()
```


Development status
==================
Completed 
---------
- Annotations that may be added to individual Controller classes or methods to add Metrics to them
- Controller class that may expose all metrics that is measured by your application
- The ability to add a Global listener that add time and meter metrics to all HTTP calls to your application

Work in progress
----------------
- Implement a way to use the TimerFilter also for applications have their own Global object

TODO
------
- Make reporting to SLF4j, CSV, Graphite or other mechanisms easy configurable



License
========

This software is licensed under the Apache 2 license, quoted below.

Copyright 2013 Sondre Eikanger Kvalø.

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this project except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0.

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
