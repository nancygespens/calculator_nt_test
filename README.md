* **[Техническое задание](https://github.com/nancygespens/calculator_nt_test/blob/main/TechnicalAssignment.md)**
* **[Техническое задание](https://github.com/nancygespens/calculator_nt_test/blob/main/TechnicalAssignment.md)**
---

1. **Клонирование репозитория проекта**
---
1.1. Откройте терминал (командную строку).
1.2. Перейдите в каталог, где вы хотите разместить проект.
1.3. Выполните команду клонирования репозитория:

```
git clone https://github.com/nancygespens/calculator_nt_test.git
```

2. **Открыть проект в IntelliJ IDEA**

3. **Команды**
---
* Запуск приложения::

```
mvn spring-boot:run
```

* Установка зависимостей:

```
mvn install
```

* Очистка кэша и временных файлов:

```
mvn clean
```

* Остановка приложения:
`Ctrl + C` для Windows
`Control + C` для Mac

4. **Insomnia**
---
4.1. Запустите Insomnia.
4.2. Создайте новый запрос с методом POST и адресом `http://localhost:8080/calculate`.
4.3. В теле запроса вставьте JSON для проведения калькуляции, например:
```
{
  "number_1": "10",
  "number_2": "5",
  "operation": "+"
}
```

3.4. Отправьте запрос и убедитесь, что получен верный ответ:
{
  "result": "-1"
}


**Описание проекта** 
---
Проект представляет собой REST-приложение на основе Java и фреймворка Spring, реализующее функции калькулятора. Приложение работает в режиме сервера и принимает запросы в формате JSON с параметрами: два числа и арифметическую операцию. Ответ также предоставляется в формате JSON с результатом выполненной операции. При вводе некорректных данных в запросе возвращается сообщение об ошибке.