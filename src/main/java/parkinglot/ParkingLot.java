package parkinglot;

import java.util.HashSet;
import java.util.Set;

public class ParkingLot {
    private Set<Vehicle> cars;
    private int capacity;
    public ParkingLot(int capacity) {
        this.capacity = capacity;
        cars = new HashSet<>();
    }

    public void park(Vehicle vehicle) throws ParkingFullException, VehicleAlreadyParkedException {
        if (cars.contains(vehicle)) throw new VehicleAlreadyParkedException();
        if (cars.size() == capacity) throw new ParkingFullException();
        cars.add(vehicle);
    }
}
