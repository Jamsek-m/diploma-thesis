# Diploma thesis
> My diploma thesis for undergraduate study of computer and information science at University of Ljubljana.

## Information

**Title**: Zasnova in razvoj platforme za spremljanje metrik spletnih aplikacij HTML5 (Design and implementation of platform for monitoring metrics of HTML5 web applications)

**Author**: Miha Jamšek

**Educational organization**: Faculty of computer and information science, University of Ljubljana, Slovenia

## Abstract (SL)

Spremljanje metrik spletnih aplikacij dandanes postaja vedno bolj pomembna aktivnost. S spremljanjem metrik  dobimo povratne informacije o kvaliteti aplikacije, intuitivnosti njenega uporabniškega vmesnika in o njenih uporabnikih, ki jih lahko uporabimo za izboljšanje naše aplikacije in storitve.

V diplomski nalogi se ukvarjamo s problematiko spremljanja metrik. Pri tem se osredotočamo na pasivno spremljanje metrik, ki spremljanje izvaja na celotni množici uporabnikov aplikacije. Podrobneje raziščemo eno izmed tehnologij pasivnega spremljanja – spremljanje metrik v realnem času in uporabo te tehnologije v enostranskih spletnih aplikacijah (ang. single page application), ki so dandanes zelo popularne, s svojim drugačnim načinom delovanja od klasičnih spletnih strani pa zahtevajo spremembe tudi pri zbiranju metrik.

Rezultat diplomske naloge je generična platforma za spremljanje metrik, prilagojena za enostranske spletne aplikacije. Platforma nam omogoča zajemanje metrik, pomembnih za enostranske spletne aplikacije, v realnem času in neodvisno od uporabljenega ogrodja za izdelavo takih aplikacij.

## Abstract (EN)

Nowadays metrics monitoring is becoming increasingly important activity. With monitoring of various metrics, developers get feedback about application quality, intuitiveness of user interface and application's users, which developers can use to improve their application and service.

In diploma thesis we deal with the topic of monitoring metrics. We are focusing on passive monitoring that is monitoring whole set of application users. Further, we are researching one of the technologies of passive monitoring – real user monitoring and its usage in single page applications. Today very popular single page applications, with their different way of working than classical web pages, demand some changes in the way we collect metrics.

Result of diploma thesis is a generic platform for metrics monitoring, adjusted for single page applications. Platform enables monitoring of metrics, important for single page applications, in real time and independently of used framework for development of such applications.

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
