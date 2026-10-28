package Locations;

import Equipments.Equipment;
import Resources.Resource;
import enums.EnvironmentCondition;
import enums.FacilityType;

public class CommunicationStation extends ColonyFacility{
    public CommunicationStation(String name,int capacity, Resource[] resources, Equipment[] equipment){
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
        //is not operational when dust storm is happening
    }
}
