package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import java.util.Arrays;

public abstract class ColonyFacility implements Location{
    private double capacity;
    private boolean isDamaged;
    private Resource[] resources;
    private Equipment[] equipment;
    
    public boolean getIsDamaged(){ //used for repair missions
        return isDamaged;
    } 
    public void setIsOperational(boolean value){
        isDamaged = !value; //is isOperational true, then facility is NOT damaged and if false, then facility is damaged
    }
    public Resource[] getResources(){
        return Arrays.copyOf(resources, resources.length);
    }
    public void setResources(Resource[] newResources){
        this.resources = newResources;
    }
    public Equipment[] getEquipment(){
        return Arrays.copyOf(equipment,equipment.length);
    }
    public void setEquipment(Equipment[] newEquipments){
        this.equipment = newEquipments;
    }
    public int getResourceQuantity(ResourceType type){
        int total=0;
        for(int i=0;i<resources.length;i++){
            if(resources[i].getType() == type){
                total+= resources[i].getQuantity();
            }
        }
        return total;
    }
    
    public boolean consumeResource(ResourceType type, int amount){ //return true if resource consumed 
       int total=0;
       boolean flag=false;
       for(int i=0;i<resources.length;i++){
        if(total>=amount){
            flag = true;
            break;
        }
        if(resources[i].getType()==type){
            total+= amount - resources[i].getQuantity();
        }
       }
       if(flag){
            int amountTaken=0;
            for(int i=0;i<resources.length;i++){
                if(amountTaken==amount){
                    return true;
                }
                if(resources[i].getType()==type){
                    amountTaken += resources[i].getQuantity();

                }
            }
       }
    }
     printResourcesInFacility()
    public boolean isOperational(EnvironmentCondition env) 

}
/*note: isDamaged is used to indicate that facility is not operational due to an emergency
where as isOperational(EnvironmentCondition) checks as well the environment to check whether a facility
is not available due to an environmental condition */
