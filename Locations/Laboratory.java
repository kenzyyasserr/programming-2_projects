package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import Emergencies.*;
public class Laboratory extends ColonyFacility{
    public Laboratory(String name,int capacity, Resource[] resources, Equipment[] equipment){
        super(name,LocationType.LABORATORY,capacity,resources,equipment);
    }
    @Override
    public boolean isIndoorsFacility(){
        return true;
    }
    @Override
    public boolean isOnMarsSurface(){
        return false; 
    }  
    @Override
    public boolean isGroundVehicleAccessible(){
        return false;
    }
    @Override
    public boolean isOperational(EnvironmentCondition environmentCondition){
        if(!isEmergencyOccurring() && !getIsDamaged()){
            return true;
        }
        else return false;
    }
    @Override 
    public boolean setEmergency(Emergency emergency){
        if(emergency!=null && getOngoingEmergency()==null){
            if(emergency.getEmergencyType()==EmergencyType.FIRE){
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
}

