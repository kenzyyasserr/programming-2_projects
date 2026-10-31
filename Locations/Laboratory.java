package Locations;
import Emergencies.*;
import Equipments.*;
import Resources.*;
import enums.*;


public class Laboratory extends ColonyFacility
{
    // ---------------------------- Attribute ----------------------------
    private static final int CAPACITY = 50;

    // --------------------------- Constructor --------------------------------
    public Laboratory(String name)
    {
        super(
            name,
            LocationType.LABORATORY,
            CAPACITY,
            new Resource[] {new LabSupplies(100)},
            new Equipment[] { new Equipment(EquipmentType.MICROSCOPE),new Equipment(EquipmentType.SAMPLE_ANALYZER)}
        );
    }

    // -------------------------------- Methods -------------------------------
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
    public boolean setEmergency(Emergency emergency)
    {
        if(emergency!=null && getOngoingEmergency()==null)
        {
            if(emergency.getEmergencyType()==EmergencyType.FIRE)
            {
                this.emergency = emergency;
                return true;
            }
        }
        return false;
    }

    @Override 
    public boolean isEmergencyOccurring()
    {
        if (getOngoingEmergency()!=null)
        {
            return true;
        }
        return false;
    }
}

