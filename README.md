# Приложение для учета калорий

## Описание

Это веб-приложение, разработанное с использованием Spring Boot, которое позволяет пользователям:

- Вести учет своих данных (возраст, вес, рост, цель)
- Автоматически рассчитывать дневную норму калорий по формуле Харриса-Бенедикта
- Добавлять блюда с калорийностью, белками, жирами и углеводами
- Добавлять приемы пищи
- Получать отчеты: сколько калорий было потреблено, уложился ли в дневную норму, история питания

---

## Технологии

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven
- Lombok
- JUnit 5 + Mockito
- Контейнеризация: Docker, Docker Compose

---

## Как запустить

1. Клонирование репозитория
   ```
    git clone https://github.com/GoncharMV/calorieCount.git
    cd calorieCount
   ```

2. Запуск с помощью Docker Compose
   ```
    mvnw clean install -DskipTests
    docker-compose up --build
   ```
   
4. После успешного запуска API будет доступно по адресу: http://localhost:8080

## Основные функции API
### Пользователи (users)
POST /users - Добавление нового пользователя с расчетом дневной нормы калорий

Пример json запроса:
```
{
  "email": "email@example.com",
  "name": "Your Name",
  "age": 30,
  "weight": 55,
  "height": 178,
  "activityLevel": "LOW" (or MILD, AVG, HIGH, SUPER),
  "gender": "FEMALE" (or MALE),
  "goal": "KEEP" (or LOSS, GAIN)
}
```

GET /users/{userId} - Получение пользователя по ID

### Блюда (dish)
POST /dish - Добавление блюда с указанием калорий и БЖУ

Пример json запроса:
```
{
  "name": "Some Dish Name",
  "caloriesPerServing": 222,
  "proteins": 15.5,
  "fats": 33.3,
  "carbs": 66.6
}
```

### Прием пищи (meals)
POST /users/{userId}/meals - Добавление приема пищи (один или несколько блюд)

Пример json запроса:
```
{
  "dishIds": [1, 2]
}
```

### Отчеты
GET /users/{userId}/reports/daily/{date} - Сколько калорий потребил пользователь за день

GET /users/{userId}/reports/check/{date} - Проверка, уложился ли в дневную норму

GET /users/{userId}/history?startDate=yyyy-mm-dd&endDate=yyyy-mm-dd - История потребления калорий по дням

## Работа с Postman
Файл calcount.postman_collection.json находится в корне проекта. Его можно импортировать в Postman для тестирования API.
