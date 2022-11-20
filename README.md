- # SeleniumRestAssuredTests

### Содержание

- [Документация](#Документация)
- [Запуск тестов](#Запуск-тестов)
    - [Команды](#Команды)
- [Запуск тестов в docker контейнере](#Запуск-тестов-в-docker-контейнере)
---

## Документация

Для тестирования используются инструменты SeleniumWebDriver и RestAssured:

- RestAssured используется для api тестов
  - [Документация по RestAssured](https://rest-assured.io/)
- SeleniumWebDriver используется для ui тестов
  - [Документация по SeleniumWebDriver](https://www.selenium.dev/documentation/)

## Запуск тестов

### Окружение

- Автотесты разрабатывались и тестировались на `macOS Monterey 12.4`, `java 15.0.2`, `maven 3.8.6`, `Google chrome 107.0.5304.110`
- Если с локальным запуском проблемы из-за совместимостей - можно запустить через докер, тестировал на версии `docker 20.10.14`, `docker-compose 2.5.0`

### Команды

- Запустить тесты и хостануть отчет `mvn clean test -Dtest=ApiTests,UiTests && mvn allure:serve`
- Сгенерировать отчет после прогона тестов `mvn allure:serve` по пути `target/site/allure-maven-plugin`
- Хостануть сгенерированный отчет. Если не установлен http-server - установить командой `npm install http-server -g || brew install http-server -g`, для старта хостинга использовать `http-server target/site/allure-maven-plugin`

## Запуск тестов в docker контейнере

- В корне директории SeleniumRestAssuredTests хранится файл Dockerfile, который
  содержит информацию необходимую для создания образа, и docker-compose.yaml, в
  котором указана информация для определения и запуска контейнера локально.
- Для создания образа и запуска контейнера в папке SeleniumRestAssuredTests
  необходимо выполнить команду `docker-compose up --build`
- Для запуска тестов выполните `docker-compose up`