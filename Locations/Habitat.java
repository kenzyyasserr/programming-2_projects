package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import Emergencies.*;
public class Habitat extends ColonyFacility{
    private static final int CAPACITY = 500;

    public Habitat(String name){
        super(
            name,
            LocationType.HABITAT,
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
    public boolean isOnMarsSurface(){ //aka, outdoors
        return false; 
    }  
    @Override
    public boolean isGroundVehicleAccessible(){
        return false;
    }
    @Override
    public boolean isOperational(EnvironmentCondition env){
        if(!isEmergencyOccurring() && !getIsDamaged()){
            return true;
        }
        else return false;
    }
    @Override 
    public boolean setEmergency(Emergency emergency){
        if(emergency!=null && getOngoingEmergency()==null){
            if(emergency.getEmergencyType()==EmergencyType.OXYGEN_LEAK){
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
