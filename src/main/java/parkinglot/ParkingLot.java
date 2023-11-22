package parkinglot;

public class ParkingLot {
    private int noOfCars;
    private int capacity;
    public ParkingLot(int capacity) {
        this.capacity = capacity;
        noOfCars = 0;
    }

    public void park(Vehicle vehicle) throws ParkingFullException {
        if (noOfCars == capacity) throw new ParkingFullException();
        noOfCars++;
    }
}
