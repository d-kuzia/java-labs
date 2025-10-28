package lab4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lab4.person.Person;
import lab4.vehicle.Vehicle;

public class Road {
    private final List<Vehicle<? extends Person>> carsInRoad = new ArrayList<>();

    public int getCountOfHumans() {
        int total = 0;
        for (Vehicle<? extends Person> v : carsInRoad) {
            total += v.getOccupiedSeats();
        }
        return total;
    }

    public void addCarToRoad(Vehicle<? extends Person> car) {
        carsInRoad.add(car);
    }

    public List<Vehicle<? extends Person>> getCarsInRoad() {
        return Collections.unmodifiableList(carsInRoad);
    }
}

