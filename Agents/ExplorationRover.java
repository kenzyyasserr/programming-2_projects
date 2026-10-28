package Agents;

import Equipment.Equipment;
import Locations.Location;

public class ExplorationRover extends RoboticUnits {
    public ExplorationRover(Location currentLocation, String id, double BatteryLevel,boolean onMission, Equipment[] equipment) {
        super(currentLocation, id, BatteryLevel, onMission, equipment);
    }

    public void explore(){
        this.changeBatteryLevel(-10); // Decrease battery level by 10 units
        this.onMission = true; // Set the rover on a mission
    }

    @Override
    public boolean canOperateIn(Location loc) {
        return loc.isGroundAccessible() && !loc.isIndoorsFacility();
    }

}