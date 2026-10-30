package Emergencies;
import Agents.Agent;
import Locations.Location;
import enums.*;

public abstract class Emergency{
    private ResponderType[] requiredResponders;
    private Agent[] assignedResponders;
    private String failureReason;
    private Skill [] requiredSkills;
    private ResourceType[] requiredResources;
    private int[] requiredAmountsOfResources;
    private Location EmergencyLocation;
    private EmergencyType type;
    private EmergencyStatus status;


    public Emergency(ResponderType[] requiredResponders, ResourceType[] requiredResource, int [] requiredAmountsOfResources, Location EmergencyLocation, EmergencyType type){
        this.requiredResponders=requiredResponders.clone();


    }

    public EmergencyType getEmergencyType(){
        return type;
    }
    public EmergencyStatus getEmergencyStatus(){
        return status;
    }

    public void setEmergencyStatus(EmergencyStatus status){
        this.status=status;
    }
}