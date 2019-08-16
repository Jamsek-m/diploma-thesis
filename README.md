# Diploma thesis
> My diploma thesis for undergraduate study of computer and information science at University of Ljubljana.

## Information

**Title**: Zasnova in razvoj platforme za spremljanje metrik HTML5 spletnih aplikacij (Design and development of the platform for monitoring metrics of HTML5 web applications)

**Author**: Miha Jamšek

**Educational organization**: Faculty of computer and information science, University of Ljubljana, Slovenia

## Abstract (SL)

Spremljanje metrik dandanes postaja vedno bolj pomembna aktivnost. S spremljanjem raznih metrik  dobimo povratne informacije o kvaliteti aplikacije, intuitivnosti njenega uporabniškega vmesnika in o njenih uporabnikih, ki jih lahko uporabimo za izboljšanje naše aplikacije in storitve.

Eden izmed načinov spremljanja metrik je spremljanje uporabnikov v realnem času. Meritve tako opravljamo na realnih uporabnikih in ne več na manjši množici testnih uporabnikov. Čeprav se metrike lahko spremlja pri vseh programih, se v nalogi osredotočim na spletne aplikacije, kjer so se z  vpeljavo aplikacij na eni strani (ang. single page applications) spremenile metrike, ki jih zajemamo in način kako jih zajemamo.

Cilj diplomske naloge je bil razviti generično rešitev za spremljanje metrik spletnih aplikacij, ki deluje neodvisno od uporabljenega ogrodja.

## Abstract (EN)

Nowadays metrics monitoring is becoming increasingly important activity. With monitoring of various metrics, we get feedback about application quality, how intuitive its user interface is and about its users, which we can use to improve our application and service.
 
One way to monitor metrics is by monitoring users in real time. Monitoring is done on real users and not anymore on smaller test group of users. Even though we can monitor metrics in all programs in my thesis I focus on web applications. With introduction of single page applications monitored metrics have changed together with the way we are monitoring them.

Goal of diploma thesis was to design generic solution for monitoring metrics of web applications, which works independently of used framework.

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
