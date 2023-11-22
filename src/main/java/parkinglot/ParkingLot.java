package parkinglot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private Set<Vehicle> cars;

    private Owner owner;

    private int capacity;
    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        cars = new HashSet<>();
        this.owner = owner;
    }

    public void park(Vehicle vehicle) throws ParkingFullException, VehicleAlreadyParkedException {
        if (isParked(vehicle)) throw new VehicleAlreadyParkedException();
        if (cars.size() == capacity) throw new ParkingFullException();
        cars.add(vehicle);

        if (cars.size() == capacity) owner.notifyParkingFull();
    }

    public void unpark(Vehicle vehicle) {
        if (cars.size() == capacity) owner.notifyParkingAvailable();
        cars.remove(vehicle);
    }

    public boolean isParked(Vehicle vehicle) {
        return cars.contains(vehicle);
    }
}
