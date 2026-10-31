package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import Emergencies.*;
public class StorageFacility extends ColonyFacility{
    private static final int CAPACITY = 500;

    public StorageFacility(String name){
        super(
            name,
            LocationType.STORAGE_FACILITY,
            CAPACITY,
            new Resource[] {new SpareParts(100),new Water(1000),new Food(300),new MedicalSupplies(100), new LabSupplies(100),new Oxygen(1000)},
            new Equipment[] { new Equipment(EquipmentType.CARGO_LOADER),new Equipment(EquipmentType.CONTAINERS)}
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
        return false; //No emergency occurs in storage facility
    }
    @Override 
    public boolean isEmergencyOccurring(){
        return false; //No emergency occurs in storage facility
    }
}

