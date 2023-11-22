package parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void parkAVehicle() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
    }

    @Test
    void shouldThrowExceptionWhenParkingLotIsFull() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Vehicle());
        assertThrows(ParkingFullException.class, () -> parkingLot.park(new Vehicle()));
    }

    @Test
    void shouldThrowExceptionWhenTheCarIsAlreadyParked() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    void shouldUnparkTheCar() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        parkingLot.unpark(vehicle);
        parkingLot.park(vehicle);
        parkingLot.park(new Vehicle());
    }

    @Test
    void shouldSayIfACarIsParkedInTheLot() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        assertTrue(parkingLot.isParked(vehicle));
    }

    @Test
    void shouldSayIfCarIsNotParkedInTheLot() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        assertFalse(parkingLot.isParked(new Vehicle()));
    }
}
