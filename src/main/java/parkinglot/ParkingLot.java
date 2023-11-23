package parkinglot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingLot implements ParkingLotObservable {
    private Set<Vehicle> cars;

    private List<ParkingLotObserver> observers;

    private int capacity;
    public ParkingLot(int capacity) {
        this.capacity = capacity;
        cars = new HashSet<>();
        this.observers = new ArrayList<>();
    }

    public void park(Vehicle vehicle) throws ParkingFullException, VehicleAlreadyParkedException {
        if (isParked(vehicle)) throw new VehicleAlreadyParkedException();
        if (cars.size() == capacity) throw new ParkingFullException();
        cars.add(vehicle);

        if (cars.size() == capacity) {
            observers.stream().forEach(observer -> observer.notifyParkingFull());
        }
    }

    public void unpark(Vehicle vehicle) {
        if (cars.size() == capacity) {
            observers.stream().forEach(observer -> observer.notifyParkingAvailable());
        }
        cars.remove(vehicle);
    }

    public boolean isParked(Vehicle vehicle) {
        return cars.contains(vehicle);
    }

    @Override
    public void addObserver(ParkingLotObserver observer) {
        this.observers.add(observer);
    }
}
