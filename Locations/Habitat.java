package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import Emergencies.*;
public class Habitat extends ColonyFacility{
    public Habitat(String name,int capacity, Resource[] resources, Equipment[] equipment){
        super(name,FacilityType.HABITAT,capacity,resources,equipment);
    }
    @Override
    public boolean isIndoorsFacility(){
        return true;
    }
    @Override
    public boolean isOnMarsSurface(){ //aka, outdoors
        return false; 
    }  
    @Override
    public boolean isGroundVehicleAccessible(){
        return false;
    }
    @Override
    public boolean isOperational(EnvironmentCondition env){
        return true; //habitat always operational
    }
    @Override 
    public boolean setEmergency(Emergency emergency){
        if(emergency!=null){
            if(emergency.getEmergencyType()==EmergencyType.OXYGEN_LEAK){
                this.emergency = emergency;
                return true;
            }
        }
        return false;
    }
    @Override 
    public boolean isEmergencyOccuring(){
        if 
    }
}
