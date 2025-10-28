package lab4.vehicle;

import lab4.person.Person;

public abstract class Car<T extends Person> extends Vehicle<T> {
    protected Car(int capacity) {
        super(capacity);
    }
}

