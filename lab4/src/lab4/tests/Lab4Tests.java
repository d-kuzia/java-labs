package lab4.tests;

import lab4.Road;
import lab4.person.*;
import lab4.vehicle.*;

public class Lab4Tests {
    public static void runAll() {
        testCapacityAndExceptions();
        testTypeRestrictions();
        testRoadCounting();
        testInvalidCapacity();
        testDuplicatePassengers();
        testUnmodifiablePassengersList();
        testEmptyRoadCount();
        System.out.println("Усі тести lab4 пройдено");
    }

    private static void testCapacityAndExceptions() {
        Bus bus = new Bus(2);
        Person a = new RegularPassenger("А");
        Person b = new RegularPassenger("Б");
        Person c = new RegularPassenger("В");

        bus.boardPassenger(a);
        bus.boardPassenger(b);
        assert bus.getCapacity() == 2;
        assert bus.getOccupiedSeats() == 2;
        System.out.println("OK: заповнення автобуса та лічильники");

        boolean thrown = false;
        try {
            bus.boardPassenger(c);
        } catch (IllegalStateException ex) {
            thrown = true;
            System.out.println("OK: очікуваний IllegalStateException при переповненні: " + ex.getMessage());
        }
        assert thrown : "Очікувалась помилка при переповненні";

        bus.disembarkPassenger(a);
        assert bus.getOccupiedSeats() == 1;
        System.out.println("OK: висадка зменшує кількість зайнятих місць");

        thrown = false;
        try {
            bus.disembarkPassenger(a);
        } catch (IllegalArgumentException ex) {
            thrown = true;
            System.out.println("OK: очікуваний IllegalArgumentException при висадці відсутнього пасажира: " + ex.getMessage());
        }
        assert thrown : "Очікувалась помилка при висадці відсутнього пасажира";
    }

    private static void testTypeRestrictions() {
        FireTruck fireTruck = new FireTruck(2);
        fireTruck.boardPassenger(new Firefighter("Пожежник 1"));

        PoliceCar policeCar = new PoliceCar(1);
        policeCar.boardPassenger(new Policeman("Поліцейський 1"));
        assert fireTruck.getOccupiedSeats() == 1;
        assert policeCar.getOccupiedSeats() == 1;
        System.out.println("OK: обмеження типів для спеціальних авто");
    }

    private static void testRoadCounting() {
        Road road = new Road();
        Bus bus = new Bus(3);
        Taxi taxi = new Taxi(2);

        bus.boardPassenger(new RegularPassenger("А"));
        bus.boardPassenger(new Firefighter("Б"));
        taxi.boardPassenger(new Policeman("В"));

        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);

        assert road.getCountOfHumans() == 3 : "Очікується 3 пасажири";
        System.out.println("OK: Road коректно рахує кількість людей");
    }

    private static void testInvalidCapacity() {
        boolean thrown = false;
        try {
            new Bus(0);
        } catch (IllegalArgumentException ex) {
            thrown = true;
            System.out.println("OK: очікуваний IllegalArgumentException для місткості 0: " + ex.getMessage());
        }
        assert thrown : "Очікувалась помилка для місткості 0";

        thrown = false;
        try {
            new Taxi(-1);
        } catch (IllegalArgumentException ex) {
            thrown = true;
            System.out.println("OK: очікуваний IllegalArgumentException для від'ємної місткості: " + ex.getMessage());
        }
        assert thrown : "Очікувалась помилка для від'ємної місткості";
    }

    private static void testDuplicatePassengers() {
        Bus bus = new Bus(2);
        Person p = new RegularPassenger("Дубль");
        bus.boardPassenger(p);
        bus.boardPassenger(p); // дубль об'єкта - дозволено
        assert bus.getOccupiedSeats() == 2 : "Очікується 2 місця, навіть якщо пасажир однаковий";
        System.out.println("OK: дублікати пасажирів дозволені і правильно рахуються");
    }

    private static void testUnmodifiablePassengersList() {
        Bus bus = new Bus(1);
        Person p = new RegularPassenger("Незмінність");
        bus.boardPassenger(p);
        boolean thrown = false;
        try {
            bus.getPassengers().add(p);
        } catch (UnsupportedOperationException ex) {
            thrown = true;
            System.out.println("OK: список пасажирів незмінний (UnsupportedOperationException)");
        }
        assert thrown : "Очікувався UnsupportedOperationException при модифікації списку пасажирів";
    }

    private static void testEmptyRoadCount() {
        Road road = new Road();
        assert road.getCountOfHumans() == 0 : "Очікується 0 для порожньої дороги";
        System.out.println("OK: порожня дорога рахується як 0");
    }
}

