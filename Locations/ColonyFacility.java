package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import java.util.Arrays;

public abstract class ColonyFacility implements Location{
    private int capacity; //in mars colony, check capacity first before adding new agents to a facility
    private boolean isDamaged;
    private Resource[] resources;
    private Equipment[] equipment;
    
    public ColonyFacility(int capacity, Resource[] resources, Equipment[] equipment){ 
        this.capacity = capacity;
        this.resources = resources;
        this.equipment = equipment;
    }
    public boolean getIsDamaged(){ //used for repair missions
        return isDamaged;
    }
    public int getCapacity(){
        return capacity;
    }
    public void setIsDamaged(boolean value){ //used when emergencies damage locations
        isDamaged = value; //if true, then facility is damaged and if false, then facility is not damaged
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
       for(int i=0;i<resources.length;i++){
        if(resources[i].getType()==type){
            if(resources[i].getQuantity()>=amount){
                resources[i].reduceResource(amount); /*if total amount found in a resource, use the resource*/
                return true;
            }
            else return false;
        }
       }
       return false;
    }
    public void printResourcesInFacility(){
        for(int i=0; i<resources.length; i++){
            System.out.printf("%s : %d units\n",resources[i].getType(),resources[i].getQuantity());
        }
    }
    public abstract boolean isOperational(EnvironmentCondition env); //will check environment to check operation of certain facilities
    public abstract void printLocationDetails();
}
/*note: isDamaged is used to indicate that facility is not operational due to an emergency
where as isOperational(EnvironmentCondition) checks as well the environment to check whether a facility
is not available due to an environmental condition */
