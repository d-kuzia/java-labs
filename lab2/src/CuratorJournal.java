import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String surname;
    private String name;
    private LocalDate birthDate;
    private String phone;
    private String street;
    private String house;
    private String apartment;

    public Student(String surname, String name, LocalDate birthDate, String phone, String street, String house, String apartment) {
        this.surname = surname;
        this.name = name;
        this.birthDate = birthDate;
        this.phone = phone;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    @Override
    public String toString() {
        return "Student{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                ", phone='" + phone + '\'' +
                ", address='" + street + ", " + house + ", " + apartment + '\'' +
                '}';
    }
}

public class CuratorJournal {
    private static List<Student> journal = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Додати запис");
            System.out.println("2. Показати всі записи");
            System.out.println("3. Вихід");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                addRecord();
            } else if (choice.equals("2")) {
                showAllRecords();
            } else if (choice.equals("3")) {
                break;
            }
        }
    }

    private static void addRecord() {
        String surname = inputValid("Прізвище: ", s -> !s.isEmpty());
        String name = inputValid("Ім'я: ", s -> !s.isEmpty());
        LocalDate birthDate = inputValidDate("Дата народження (yyyy-MM-dd): ");
        String phone = inputValid("Телефон: ", s -> s.matches("\\d+"));
        String street = inputValid("Вулиця: ", s -> !s.isEmpty());
        String house = inputValid("Будинок: ", s -> !s.isEmpty());
        String apartment = inputValid("Квартира: ", s -> !s.isEmpty());

        Student student = new Student(surname, name, birthDate, phone, street, house, apartment);
        journal.add(student);
        System.out.println("Запис додано.");
    }

    private static String inputValid(String prompt, Validator<String> validator) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if (validator.validate(input)) {
                return input;
            }
            System.out.println("Неправильний формат. Спробуйте ще.");
        }
    }

    private static LocalDate inputValidDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return LocalDate.parse(input, dateFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Неправильний формат дати. Спробуйте ще.");
            }
        }
    }

    private static void showAllRecords() {
        if (journal.isEmpty()) {
            System.out.println("Журнал порожній.");
        } else {
            for (Student s : journal) {
                System.out.println(s);
            }
        }
    }

    @FunctionalInterface
    interface Validator<T> {
        boolean validate(T t);
    }
}