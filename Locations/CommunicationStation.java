package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import Emergencies.*;
public class CommunicationStation extends ColonyFacility{
    public CommunicationStation(String name,int capacity, Resource[] resources, Equipment[] equipment){
        super(name,FacilityType.COMMUNICATION_STATION,capacity,resources,equipment);
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
        if(!isEmergencyOccurring() && !getIsDamaged() && environmentCondition!=EnvironmentCondition.COMMUNICATION_DISRUPTION){
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
