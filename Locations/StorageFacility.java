package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import Emergencies.*;
public class StorageFacility extends ColonyFacility{
    public StorageFacility(String name,int capacity, Resource[] resources, Equipment[] equipment){
        super(name,LocationType.STORAGE_FACILITY,capacity,resources,equipment);
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
        return false; //No emergency occurs in storage facility
    }
    @Override 
    public boolean isEmergencyOccurring(){
        return false; //No emergency occurs in storage facility
    }
}

