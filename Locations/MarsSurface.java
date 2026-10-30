package Locations;

import Emergencies.Emergency;
import enums.EmergencyType;
import enums.EnvironmentCondition;
import enums.LocationType;
import enums.ResourceType;

public class MarsSurface implements Location{
    private Emergency emergency;
    private LocationType type;
    public MarsSurface(){
        this.type = LocationType.MARS_SURFACE;
    }
    public boolean isIndoorsFacility(){
        return false;
    }
    public boolean isOnMarsSurface(){
        return true;
    }
    public boolean isGroundVehicleAccessible(){
        return true;
    }
    @Override 
    public LocationType getLocationType(){
        return type;
    }
    public boolean setEmergency(Emergency emergency){
        if(emergency!=null && getOngoingEmergency()==null){
            if(emergency.getEmergencyType()==EmergencyType.ROVER_BREAKDOWN){
                this.emergency = emergency;
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean isEmergencyOccurring(){
        if (getOngoingEmergency()!=null){
            return true;
        }
        return false;
    }
    @Override
    public void clearEmergency(){
        this.emergency = null;
    }
    @Override
    public Emergency getOngoingEmergency(){
        return emergency;
    }
    @Override 
    public int getResourceQuantity(ResourceType type,EnvironmentCondition env){
        return 0; //no resources on martianSurface
    }
}
