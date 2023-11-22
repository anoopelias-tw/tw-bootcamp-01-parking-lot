package parkinglot;

public class Owner {
    private boolean isParkingFull;
    private boolean isParkingAvailable;

    public void notifyParkingFull() {
        isParkingFull = true;
    }

    public void notifyParkingAvailable() {
        isParkingAvailable = true;
    }

    public boolean isParkingFull() {
        return isParkingFull;
    }

    public boolean isParkingAvailable() {
        return isParkingAvailable;
    }
}
