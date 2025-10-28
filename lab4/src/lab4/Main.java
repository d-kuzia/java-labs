package lab4;

import lab4.person.*;
import lab4.vehicle.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Demo lab4: транспорт + пасажири (generics)");

        Bus bus = new Bus(3);
        Taxi taxi = new Taxi(2);
        FireTruck fireTruck = new FireTruck(2);
        PoliceCar policeCar = new PoliceCar(2);

        Person p = new RegularPassenger("Іван");
        Firefighter f1 = new Firefighter("Петро");
        Policeman m1 = new Policeman("Андрій");

        bus.boardPassenger(p);
        bus.boardPassenger(f1);
        bus.boardPassenger(m1);

        taxi.boardPassenger(new RegularPassenger("Олег"));
        taxi.boardPassenger(new Policeman("Юрій"));

        fireTruck.boardPassenger(f1);
        policeCar.boardPassenger(m1);

        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        System.out.println("Людей на ділянці дороги: " + road.getCountOfHumans());

        lab4.tests.Lab4Tests.runAll();
    }
}
