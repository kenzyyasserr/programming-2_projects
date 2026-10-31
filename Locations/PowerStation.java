package Locations;
import Emergencies.*;
import Equipments.*;
import Resources.*;
import enums.*;


public class PowerStation extends ColonyFacility
{
    // ------------------------ Attribute --------------------------
    private static final int CAPACITY = 500;

    // ----------------------------- Constructor -----------------------
    public PowerStation(String name)
    {
        super(
            name,
            LocationType.POWER_STATION,
            CAPACITY,
            new Resource[] {new Fuel(500),new BatteryPower(100)},
            new Equipment[] { new Equipment(EquipmentType.AIR_SCRUBBER),new Equipment(EquipmentType.WATER_RECYCLER)}
        );
    }

    // ------------------------------ Methods ---------------------------
    @Override
    public boolean isIndoorsFacility() {return true;}

    @Override
    public boolean isOnMarsSurface() {return false;}

    @Override
    public boolean isGroundVehicleAccessible() {return false;}

    @Override
    public boolean isOperational(EnvironmentCondition environmentCondition)
    {
        if(!isEmergencyOccurring() && !getIsDamaged())
        {
            return true;
        }
        else return false;
    }

    @Override 
    public boolean setEmergency(Emergency emergency) {return false;}   //No emergency occurs in power station
    
    @Override 
    public boolean isEmergencyOccurring() {return false;}   //No emergency occurs in power station
}
