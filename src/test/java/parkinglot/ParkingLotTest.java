package parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {

    @Test
    public void parkAVehicle() throws ParkingFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
    }

    @Test
    void shouldThrowExceptionWhenParkingLotIsFull() throws ParkingFullException {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Vehicle());
        assertThrows(ParkingFullException.class, () -> parkingLot.park(new Vehicle()));
    }
}
