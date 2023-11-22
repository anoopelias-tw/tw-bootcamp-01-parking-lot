package parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {

    @Test
    public void parkAVehicle() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1, new Owner());
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
    }

    @Test
    void shouldThrowExceptionWhenParkingLotIsFull() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1, new Owner());
        parkingLot.park(new Vehicle());
        assertThrows(ParkingFullException.class, () -> parkingLot.park(new Vehicle()));
    }

    @Test
    void shouldThrowExceptionWhenTheCarIsAlreadyParked() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, new Owner());
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    void shouldUnparkTheCar() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, new Owner());
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        parkingLot.unpark(vehicle);
        parkingLot.park(vehicle);
        parkingLot.park(new Vehicle());
    }

    @Test
    void shouldSayIfACarIsParkedInTheLot() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, new Owner());
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        assertTrue(parkingLot.isParked(vehicle));
    }

    @Test
    void shouldSayIfCarIsNotParkedInTheLot() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, new Owner());
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        assertFalse(parkingLot.isParked(new Vehicle()));
    }

    @Test
    void ownerShouldBeNotifiedWhenParkingLotIsFull() throws ParkingFullException, VehicleAlreadyParkedException {
        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        parkingLot.park(new Vehicle());
        assertFalse(owner.isParkingFull());
        parkingLot.park(new Vehicle());
        assertTrue(owner.isParkingFull());
    }

    @Test
    void ownerShouldBeNotifiedWhenParkingLotIsAvailableAfterFull() throws ParkingFullException, VehicleAlreadyParkedException {
        Owner owner = new Owner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(new Vehicle());
        parkingLot.park(vehicle);

        assertFalse(owner.isParkingAvailable());
        parkingLot.unpark(vehicle);
        assertTrue(owner.isParkingAvailable());


    }
}
