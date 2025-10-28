package lab4.vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lab4.person.Person;

public abstract class Vehicle<T extends Person> {
    private final int capacity;
    private final List<T> passengers = new ArrayList<>();

    protected Vehicle(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Місткість має бути > 0");
        }
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public List<T> getPassengers() {
        return Collections.unmodifiableList(passengers);
    }

    public void boardPassenger(T passenger) {
        if (getOccupiedSeats() >= capacity) {
            throw new IllegalStateException("Усі місця зайнято");
        }
        passengers.add(passenger);
    }

    public void disembarkPassenger(T passenger) {
        boolean removed = passengers.remove(passenger);
        if (!removed) {
            throw new IllegalArgumentException("Цей пасажир не знаходиться у транспорті");
        }
    }
}

