# Lab4 — Транспорт і пасажири (Generics)

Завдання: 4.1. Реалізувати із застосуванням узагальненого програмування ієрархію Java-класів
для транспортних засобів, які можуть перевозити різні типи пасажирів.

Реалізовано також модульні тести на `assert`.

## Структура

- `src/lab4/person` — ієрархія пасажирів (`Person`, `RegularPassenger`, `Firefighter`, `Policeman`).
- `src/lab4/vehicle` — ієрархія транспорту (`Vehicle<T>`, `Car<T>`, `Bus`, `Taxi`, `FireTruck`, `PoliceCar`).
- `src/lab4/Road.java` — дорога з `List<Vehicle<? extends Person>>` і підрахунком людей.
- `src/lab4/tests/Lab4Tests.java` — прості тести на `assert` + “OK”-вивід.
- `src/lab4/Main.java` — демо і виклик тестів.

## Запуск у VS Code

1. Відкрити `Main.java` і запустити.
2. Для тестів (`assert`) треба додати VM аргументи:
   Через `.vscode/launch.json`:
   ```json
   {
     "version": "0.2.0",
     "configurations": [
       {
         "type": "java",
         "name": "Run lab4.Main",
         "request": "launch",
         "mainClass": "lab4.Main",
         "vmArgs": "-ea -Dfile.encoding=UTF-8"
       }
     ]
   }
   ```

## Тести

- Тести написані через `assert` у `Lab4Tests.runAll()` і викликаються з `Main`.
- `-ea` (enable assertions) обов’язковий, інакше перевірки ігноруються.
- В консолі очікується “OK”-вивід для кожного підтесту. При збої буде `AssertionError`.
