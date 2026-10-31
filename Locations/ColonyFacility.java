package Locations;
import Emergencies.*;
import Equipments.*;
import Resources.*;
import enums.*;
import java.util.Arrays;

public abstract class ColonyFacility implements Location,CanBeFixed
{
    // ------------------------ Attributes -----------------------------
    private LocationType type;
    private int capacity; //in mars colony, check capacity first before adding new agents to a facility
    private boolean isDamaged;
    private Resource[] resources;
    private Equipment[] equipment;
    private String name;
    protected Emergency emergency; //null if no emergency is occuring in the location

    // --------------------------- Constructor ------------------------------
    public ColonyFacility(String name, LocationType type,int capacity, Resource[] resources, Equipment[] equipment){ 
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.resources = resources;
        this.equipment = equipment;
    }

    // -------------------------- Abstract Method ---------------------------
    public abstract boolean isOperational(EnvironmentCondition env); //will check environment to check operation of certain facilities
    
    // -------------------------------- Concrete Methods ----------------------------
    // --------------------------------- Getters ----------------------------------
    @Override
    public Emergency getOngoingEmergency() {return emergency;}
    @Override
    public LocationType getLocationType(){return type;}
    @Override
    public int getResourceQuantity(ResourceType type,EnvironmentCondition env)
    {
        //if facility unavailable (not operational) its resources don't count
        if(!isOperational(env)) return 0;

        for(int i=0;i<resources.length;i++)
        {
            if(resources[i].getType() == type){
                return resources[i].getQuantity();
            }
        }
        return 0;
    }

    public String getName() {return name;}
    public boolean getIsDamaged() {return isDamaged;} //used for repair missions
    public int getCapacity() {return capacity;}
    public Resource[] getResources() {return Arrays.copyOf(resources, resources.length);}
    public Equipment[] getEquipment(){return Arrays.copyOf(equipment,equipment.length);}

    // ------------------------------- Setters ----------------------------------
    public void setName(String newName) {name = newName;}
    public void setIsDamaged(boolean value){ //used when emergencies damage locations
        isDamaged = value; //if true, then facility is damaged and if false, then facility is not damaged
    }
    public void setResources(Resource[] newResources) {this.resources = Arrays.copyOf(newResources, newResources.length);}
    public void setEquipment(Equipment[] newEquipments) {this.equipment = Arrays.copyOf(newEquipments,newEquipments.length);}


    // ------------------------------ Others -------------------------------
    public void repair()
    {
        if(isDamaged){
            isDamaged = false;
        }
    }
    
    public boolean consumeResource(ResourceType type, int amount)   //return true if resource consumed 
    { 
       for(int i=0;i<resources.length;i++)
       {
        if(resources[i].getType()==type)
        {
            if(resources[i].getQuantity()>=amount)
            {
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

    public void printLocationDetails()
    {
        System.out.printf("Facility name: %s\nType: %s\nCapacity: %d\nResources: %s\nEquipment: %s\n",name,type,capacity,Arrays.toString(resources),Arrays.toString(equipment));
    }

    public boolean hasEquipment(EquipmentType equipmentType)
    {
        if(equipment!=null)
        {
            for(int i=0;i<equipment.length;i++)
            {
                if(equipment[i].getType()==equipmentType && equipment[i].isOperational())
                {
                    return true;
                }
            }
        }
        return false;
    }
}

/*note: isDamaged is used to indicate that facility is not operational due to an emergency
where as isOperational(EnvironmentCondition) checks as well the environment to check whether a facility
is not available due to an environmental condition */