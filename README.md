# Пример RESTful веб-сервиса на Java с использованием сервлетов и JDBC

Этот проект демонстрирует пример простого RESTful веб-сервиса на Java с использованием сервлетов и JDBC для взаимодействия с базой данных PostgreSQL. Проект реализует два сервлета для работы с сущностями: `EntityOneServlet` и `EntityTwoServlet`, каждый из которых обрабатывает HTTP-запросы для выполнения операций CRUD (Create, Read, Update, Delete) с соответствующими сущностями.

## Структура проекта

Проект содержит следующие пакеты и классы:

- **com.example.dao**: Пакет, содержащий интерфейсы и их реализации для доступа к данным с использованием JDBC.
    - `EntityOneDAO`: Интерфейс для работы с сущностью 1.
    - `EntityOneDAOImpl`: Реализация интерфейса `EntityOneDAO`.
    - `EntityTwoDAO`: Интерфейс для работы с сущностью 2.
    - `EntityTwoDAOImpl`: Реализация интерфейса `EntityTwoDAO`.
- **com.example.model**: Пакет, содержащий модели сущностей.
    - `EntityOne`: Модель сущности 1.
    - `EntityTwo`: Модель сущности 2.
- **com.example.servlets**: Пакет, содержащий сервлеты для работы с сущностями.
    - `EntityOneServlet`: Сервлет для работы с сущностью 1.
    - `EntityTwoServlet`: Сервлет для работы с сущностью 2.
- **com.example.util**: Пакет, содержащий утилитарные классы.
    - `DatabaseConnection`: Класс для установления соединения с базой данных PostgreSQL.

## Требования

Для запуска данного проекта необходимо наличие следующего:

- JDK (Java Development Kit) версии 8 или выше.
- Apache Maven для сборки проекта.
- Сервер приложений, поддерживающий спецификацию сервлетов (например, Apache Tomcat).
- База данных PostgreSQL.

## Как использовать

1. Склонируйте репозиторий на ваш локальный компьютер:

```bash
git clone https://github.com/yourusername/your-repository.git
```

2. Создайте базу данных PostgreSQL и выполните скрипт schema.sql для создания необходимых таблиц.
3. Укажите параметры подключения к базе данных в файле `DatabaseConnection.java`.
4. Соберите проект с помощью Maven:
```bash
mvn clean package
```
5. Разверните WAR-файл на вашем сервере приложений (например, `Apache Tomcat`).
6. Обратитесь к сервлетам через URL-адрес вашего сервера приложений:
* `http://localhost:8080/your-application/entity1` для работы с сущностью 1.
* `http://localhost:8080/your-application/entity2` для работы с сущностью 2.