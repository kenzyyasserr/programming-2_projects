package Locations;

import Emergencies.Emergency;
import enums.EmergencyType;
import enums.EnvironmentCondition;
import enums.LocationType;
import enums.ResourceType;

public class MarsSurface implements Location
{
    // ----------------------- Attributes ------------------------------
    private Emergency emergency;
    private LocationType type;

    // ---------------------------- Constructor ----------------------
    public MarsSurface(){
        this.type = LocationType.MARS_SURFACE;
    }

    // ---------------------------- Methods --------------------------
    @Override
    public boolean isIndoorsFacility() {return false;}

    @Override
    public boolean isOnMarsSurface() {return true;}

    @Override
    public boolean isGroundVehicleAccessible() {return true;}

    @Override 
    public LocationType getLocationType() {return type;}

    @Override
    public boolean setEmergency(Emergency emergency)
    {
        if(emergency!=null && getOngoingEmergency()==null)
        {
            if(emergency.getEmergencyType()==EmergencyType.ROVER_BREAKDOWN)
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

    @Override
    public void clearEmergency() {this.emergency = null;}

    @Override
    public Emergency getOngoingEmergency() {return emergency;}

    @Override
    public int getResourceQuantity(ResourceType type,EnvironmentCondition env){return 0;}  //no resources on martianSurface

    @Override
    public String toString() {return "Mars Surface";}
}
