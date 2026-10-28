package Locations;

public interface Location {
    boolean isIndoorsFacility(); // repair robots
    boolean isOnMarsSurface(); // rovers
    boolean isGroundVehicleAccessible(); // not accessible -> drone
}