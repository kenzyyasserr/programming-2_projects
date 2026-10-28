package Locations;

import Emergencies.Emergency;

public interface Location {
    boolean isIndoorsFacility(); // repair robots
    boolean isOnMarsSurface(); // rovers
    boolean isGroundVehicleAccessible(); // not accessible -> drone
    boolean setEmergency(Emergency emergency); //returns true if emergency set successfull. it is abstract as certain emergencies can only occur in certain locations
    boolean isEmergencyOccuring(); //returns true if an emergency is occuring in a specific location
}