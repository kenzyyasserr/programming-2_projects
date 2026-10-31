package Locations;
import Emergencies.*;
import Equipments.*;
import Resources.*;
import enums.*;


public class CommunicationStation extends ColonyFacility
{
    // ------------------------- Attributes -------------------------
    private static final int CAPACITY = 500;

    // --------------------- Constructor -------------------------------
    public CommunicationStation(String name)
    {
        super(
            name,
            LocationType.COMMUNICATION_STATION,
            CAPACITY,
            new Resource[] {new BatteryPower(100)},
            new Equipment[] { new Equipment(EquipmentType.ANTENNA)}
        );
    }

    // --------------------------- Methods ---------------------------
    @Override
    public boolean isIndoorsFacility() {return true;}

    @Override
    public boolean isOnMarsSurface() {return false;}  

    @Override
    public boolean isGroundVehicleAccessible(){return false;}

    @Override
    public boolean isOperational(EnvironmentCondition environmentCondition)
    {
        if(!isEmergencyOccurring() && !getIsDamaged() && environmentCondition!=EnvironmentCondition.COMMUNICATION_DISRUPTION)
        {
            return true;
        }
        else return false;
    }

    @Override 
    public boolean setEmergency(Emergency emergency){
        return false; //no emergencies occur in communication stations
    }
    
    @Override 
    public boolean isEmergencyOccurring(){
        return false; //no emergencies occur in communication stations
    }
}
