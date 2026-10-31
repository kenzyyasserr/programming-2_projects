package Locations;
import Resources.*;
import enums.*;
import Equipments.*;
import Emergencies.*;
import java.util.Arrays;

public abstract class ColonyFacility implements Location,CanBeFixed{
    private LocationType type;
    private int capacity; //in mars colony, check capacity first before adding new agents to a facility
    private boolean isDamaged;
    private Resource[] resources;
    private Equipment[] equipment;
    private String name;
    protected Emergency emergency; //null if no emergency is occuring in the location

    public ColonyFacility(String name, LocationType type,int capacity, Resource[] resources, Equipment[] equipment){ 
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.resources = resources;
        this.equipment = equipment;
    }
    @Override
    public Emergency getOngoingEmergency(){
        return emergency;
    }
    public void repair(){
        if(isDamaged){
            isDamaged = false;
        }
    }
    public String getName(){
        return name;
    }
    public void setName(String newName){
        name = newName;
    }
    @Override
    public LocationType getLocationType(){
        return type;
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
        this.resources = Arrays.copyOf(newResources, newResources.length);
    }
    public Equipment[] getEquipment(){
        return Arrays.copyOf(equipment,equipment.length);
    }
    public void setEquipment(Equipment[] newEquipments){
        this.equipment = Arrays.copyOf(newEquipments,newEquipments.length);
    }
    @Override
    public int getResourceQuantity(ResourceType type,EnvironmentCondition env){
        //if facility unavailable (not operational) its resources don't count
        if(!isOperational(env)) return 0;
        for(int i=0;i<resources.length;i++){
            if(resources[i].getType() == type){
                return resources[i].getQuantity();
            }
        }
        return 0;
    }
    
    public boolean consumeResource(ResourceType type, int amount){ //return true if resource consumed 
       for(int i=0;i<resources.length;i++){
        if(resources[i].getType()==type){
            if(resources[i].getQuantity()>=amount){
                resources[i].reduceResource(amount); /*if total amount found in a resource, use the resource*/
                return true;
            }
        }
       }
       return false;
    }
    public void printResourcesInFacility(){
        for(int i=0; i<resources.length; i++){
            System.out.printf("%s : %d units\n",resources[i].getType(),resources[i].getQuantity());
        }
    }
    @Override
    public void clearEmergency(){
        this.emergency = null;
    }
    public abstract boolean isOperational(EnvironmentCondition env); //will check environment to check operation of certain facilities
    public void printLocationDetails(){
        System.out.printf("Facility name: %s\nType: %s\nCapacity: %d\nResources: %s\nEquipment: %s\n",name,type,capacity,Arrays.toString(resources),Arrays.toString(equipment));
    }
    public boolean hasEquipment(EquipmentType equipmentType){
        if(equipment!=null){
            for(int i=0;i<equipment.length;i++){
                if(equipment[i].getType()==equipmentType && equipment[i].isOperational()){
                    return true;
                }
            }
        }
        return false;
    }
    //-----------------------OVERRIDING TO STRING:-------------------------------
    @Override
    public String toString() {
        return name+" ("+type+")";
    }
}
/*note: isDamaged is used to indicate that facility is not operational due to an emergency
where as isOperational(EnvironmentCondition) checks as well the environment to check whether a facility
is not available due to an environmental condition */
