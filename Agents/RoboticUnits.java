package Agents;


public abstract class RoboticUnits extends Agent {
    protected double batteryLevel;
    Equipment [] equipment;

    public RoboticUnits(ColonyFacility currentLocation, String id, double BatteryLevel,boolean onMission, Equipment[] equipment) {
        super(currentLocation, id, onMission);
        this.batteryLevel = BatteryLevel; // Start with full battery
        this.equipment = equipment;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public Equipment[] getEquipment(){
        return equipment;
    }

    public void setEquipment(Equipment[] equipment){
        this.equipment = equipment;
    }

    public void changeBatteryLevel(double increasOrDecrease) {
        if (increasOrDecrease < 0) {
            if(batteryLevel + increasOrDecrease < 0) {
                batteryLevel = 0;
            } else {
                batteryLevel -= increasOrDecrease;
            }
        }
        else {
            if(batteryLevel + increasOrDecrease > 100) {
                batteryLevel = 100;
            } else {
                batteryLevel += increasOrDecrease;
            }
        }
    }

     public abstract boolean canOperateIn(Location loc);

}