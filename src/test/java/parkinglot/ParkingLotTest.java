package parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class ParkingLotTest {

    private Owner owner;

    @BeforeEach
    void setUp() {
        owner = mock(Owner.class);
    }

    @Test
    public void parkAVehicle() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(0, owner);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
    }

    @Test
    void shouldThrowExceptionWhenParkingLotIsFull() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(1, owner);
        parkingLot.park(new Vehicle());
        assertThrows(ParkingFullException.class, () -> parkingLot.park(new Vehicle()));
    }

    @Test
    void shouldThrowExceptionWhenTheCarIsAlreadyParked() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        assertThrows(VehicleAlreadyParkedException.class, () -> parkingLot.park(vehicle));
    }

    @Test
    void shouldUnparkTheCar() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        parkingLot.unpark(vehicle);
        parkingLot.park(vehicle);
        parkingLot.park(new Vehicle());
    }

    @Test
    void shouldSayIfACarIsParkedInTheLot() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        assertTrue(parkingLot.isParked(vehicle));
    }

    @Test
    void shouldSayIfCarIsNotParkedInTheLot() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        assertFalse(parkingLot.isParked(new Vehicle()));
    }

    @Test
    void ownerShouldBeNotifiedWhenParkingLotIsFull() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, owner);

        parkingLot.park(new Vehicle());
        parkingLot.park(new Vehicle());
        verify(owner, times(1)).notifyParkingFull();
    }

    @Test
    void ownerShouldBeNotifiedWhenParkingLotIsAvailableAfterFull() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        parkingLot.unpark(vehicleOne);
        verify(owner, times(1)).notifyParkingAvailable();
        parkingLot.unpark(vehicleTwo);
        verify(owner, times(1)).notifyParkingAvailable();
    }
}
