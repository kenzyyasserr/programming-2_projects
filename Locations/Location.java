package Locations;

import Emergencies.Emergency;
import enums.*;

public interface Location {
    boolean isIndoorsFacility(); // repair robots
    boolean isOnMarsSurface(); // rovers
    boolean isGroundVehicleAccessible(); // not accessible -> drone
    boolean setEmergency(Emergency emergency); //returns true if emergency set successfull. it is abstract as certain emergencies can only occur in certain locations
    boolean isEmergencyOccurring(); //returns true if an emergency is occuring in a specific location
    void clearEmergency(); //makes emergency in a place null, used when emergency is over
    Emergency getOngoingEmergency();
    int getResourceQuantity(ResourceType type,EnvironmentCondition env); //if facility unavailable (not operational) its resources don't count
    LocationType getLocationType();
}