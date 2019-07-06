# Diploma thesis
> My diploma thesis for undergraduate study of computer and information science at University of Ljubljana.

## Information

**Title**: Zasnova in razvoj platforme za spremljanje metrik HTML5 spletnih aplikacij (Design and development of the platform for monitoring metrics of HTML5 web applications)

**Author**: Miha Jamšek

**Educational organization**: Faculty of computer and information science, University of Ljubljana, Slovenia

## Abstract

The goal of this thesis is to design platform for monitoring metrics for single page applications (SPA). Platform consists of JavaScript library which collects metrics and of Java web service which persists metrics into database.

## Content

```
├───code
│   ├───client-library
│   ├───docs
│   ├───server
│   │   ├───api
│   │   ├───entities
│   │   ├───lib
│   │   └───services
│   └───test-samples
│       ├───angular-sample
│       └───plain-html-sample
└───thesis
```

## Technologies

Technologies used for platform:
* KumuluzEE - JavaEE web framework with Jetty Servlet
* Maven - Java dependencies manager
* Typescript - JavaScript transpiler
* Webpack - JavaScript bundler
* Apache Kafka - Event streaming service
* Zookeeper - Distributed configuration service
* Docker - Containerization tool
