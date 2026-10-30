package Agents;
import Equipments.Equipment;
import Locations.Location;

public class Drone extends RoboticUnits {
    public Drone(Location currentLocation, String id, double BatteryLevel,boolean onMission, Equipment[] equipment) {
        super(currentLocation, id, BatteryLevel,equipment);
    }

    public void aerialReconnaissance(){
        this.changeBatteryLevel(-20); // Decrease battery level by 20 units
    }

    @Override
    public boolean canOperateIn(Location loc) {
        return !loc.isGroundVehicleAccessible();
    }
}