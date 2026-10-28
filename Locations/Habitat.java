package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
public class Habitat extends ColonyFacility{
    public Habitat(int capacity, Resource[] resources, equipment[] equipment){
        super(capacity,resources,equipment);
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
        //for habitat: if oxygen > 10, it is operational:
        for(int i=0;i<.length;i++){
            if()
        }
    }
    public abstract void printLocationDetails();
}
