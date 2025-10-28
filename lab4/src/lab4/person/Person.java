package lab4.person;

public abstract class Person {
    private final String name;

    protected Person(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Ім'я не може бути порожнім");
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + name + "}";
    }
}

