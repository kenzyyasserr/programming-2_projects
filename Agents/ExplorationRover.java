package Agents;

public class ExplorationRover extends RoboticUnits {
    public ExplorationRover(Location currentLocation, String id, double BatteryLevel,boolean onMission, Equipment[] equipment) {
        super(currentLocation, id, BatteryLevel, onMission, equipment);
    }

    public void explore(){

    }

    @Override
    public boolean canOperateIn(Location loc) {
        if(loc.isGroundAccessible() && !(loc.isIndoorsFacility())){
            return true;
        }
        else{
            return false;
        }
    }

}