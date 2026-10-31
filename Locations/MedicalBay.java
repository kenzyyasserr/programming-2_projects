package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import Emergencies.*;
public class MedicalBay extends ColonyFacility{
    
    private static final int CAPACITY = 50;

    public MedicalBay(String name){
        super(
            name,
            LocationType.MEDICAL_BAY,
            CAPACITY,
            new Resource[] {new Oxygen(1000),new Food(300)},
            new Equipment[] { new Equipment(EquipmentType.AIR_SCRUBBER),new Equipment(EquipmentType.WATER_RECYCLER)}
        );
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
        return false; //No emergency occurs in medical bay
    }
    @Override 
    public boolean isEmergencyOccurring(){
        return false; //No emergency occurs in medical bay
    }
}
