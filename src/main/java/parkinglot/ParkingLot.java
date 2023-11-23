package parkinglot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private Set<Vehicle> cars;

    private ParkingLotObserver observer;

    private int capacity;
    public ParkingLot(int capacity, ParkingLotObserver observer) {
        this.capacity = capacity;
        cars = new HashSet<>();
        this.observer = observer;
    }

    public void park(Vehicle vehicle) throws ParkingFullException, VehicleAlreadyParkedException {
        if (isParked(vehicle)) throw new VehicleAlreadyParkedException();
        if (cars.size() == capacity) throw new ParkingFullException();
        cars.add(vehicle);

        if (cars.size() == capacity) observer.notifyParkingFull();
    }

    public void unpark(Vehicle vehicle) {
        if (cars.size() == capacity) observer.notifyParkingAvailable();
        cars.remove(vehicle);
    }

    public boolean isParked(Vehicle vehicle) {
        return cars.contains(vehicle);
    }
}
