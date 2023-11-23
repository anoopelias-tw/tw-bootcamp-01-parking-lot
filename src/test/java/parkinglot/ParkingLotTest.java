package parkinglot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

public class ParkingLotTest {

    private ParkingLotObserver observer;

    @BeforeEach
    void setUp() {
        observer = mock(ParkingLotObserver.class);
    }

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

    @Test
    void ownerShouldBeNotifiedWhenParkingLotIsFull() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.addObserver(observer);

        parkingLot.park(new Vehicle());
        parkingLot.park(new Vehicle());
        verify(observer, times(1)).notifyParkingFull();
    }

    @Test
    void ownerShouldBeNotifiedWhenParkingLotIsAvailableAfterFull() throws ParkingFullException, VehicleAlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.addObserver(observer);
        Vehicle vehicleOne = new Vehicle();
        Vehicle vehicleTwo = new Vehicle();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        parkingLot.unpark(vehicleOne);
        verify(observer, times(1)).notifyParkingAvailable();
        parkingLot.unpark(vehicleTwo);
        verify(observer, times(1)).notifyParkingAvailable();
    }
}
