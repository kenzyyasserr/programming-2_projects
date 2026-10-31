package Agents;
import Equipments.Equipment;
import Locations.Location;
import enums.EquipmentType;
import enums.HealthStatus;


public abstract class RoboticUnits extends Agent
{
    // ---------------------- Attributes --------------------------
    protected double batteryLevel;
    Equipment[] equipment;

    // --------------------- Constructor --------------------------------
    public RoboticUnits(Location currentLocation, String id, double BatteryLevel, Equipment[] equipment) {
        super(currentLocation, id);
        this.batteryLevel = BatteryLevel; // Start with full battery
        this.equipment = equipment;
    }

    // ------------------------ Abstract Methods -----------------------
    public abstract boolean canOperateIn(Location loc);

    // ----------------------- Concrete Methods ----------------------
    public double getBatteryLevel() {return batteryLevel;}
    public Equipment[] getEquipment() {return equipment;}

    public void setEquipment(Equipment[] equipment) {this.equipment = equipment;}

    public void changeBatteryLevel(double increasOrDecrease)
    {
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

    @Override
    public boolean canPerformMission(EquipmentType equipmentType)  // checks isUsable
    {
        if (!isUsable())
        {
        System.out.println("Robotic unit is unusable.");
        return false;
        }
        if (!hasEquipment(equipmentType))
        {
            System.out.println("Robotic unit does not have the required equipment.");
            return false;
        }

        System.out.println("Personnel can perform the mission.");
        return true;
    }

    public boolean hasEquipment(EquipmentType equipmentType)
    {
        if(equipment!=null){
            for (Equipment equipment1 : equipment) {
                if (equipment1.getType() == equipmentType && equipment1.isOperational()) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isUsable()
    {
        return this.batteryLevel > 20 && this.healthStatus == HealthStatus.HEALTHY && !this.onMission; 
    }
}