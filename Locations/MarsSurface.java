package Locations;

import Emergencies.Emergency;
import enums.EmergencyType;

public class MarsSurface implements Location{
    private Emergency emergency;

    public boolean isIndoorsFacility(){
        return false;
    }
    public boolean isOnMarsSurface(){
        return true;
    }
    public boolean isGroundVehicleAccessible(){
        return true;
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
}
