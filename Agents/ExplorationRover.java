package Agents;

import Equipments.*;
import Locations.Location;

public class ExplorationRover extends RoboticUnits {
    public ExplorationRover(Location currentLocation, String id, double BatteryLevel, Equipment[] equipment) {
        super(currentLocation, id, BatteryLevel, equipment);
    }

    public void explore(){
        this.changeBatteryLevel(-10); // Decrease battery level by 10 units
    }

    @Override
    public boolean canOperateIn(Location loc) {
        return loc.isGroundVehicleAccessible() && !loc.isIndoorsFacility();
    }

}