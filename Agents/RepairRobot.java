package Agents;
import Equipments.Equipment;
import Locations.Location;

public class RepairRobot extends RoboticUnits implements EmergencyResponder{
    public RepairRobot(Location currentLocation, String id, double BatteryLevel,boolean onMission, Equipment[] equipment) {
        super(currentLocation, id, BatteryLevel,equipment);
    }

    public void repair(){
        this.changeBatteryLevel(-15); // Decrease battery level by 15 units
        this.onMission = true; // Set the robot on a mission
    }
    
    @Override
    public boolean canOperateIn(Location loc) {
        return loc.isIndoorsFacility();
    }
}