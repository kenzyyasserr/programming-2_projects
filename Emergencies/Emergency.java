package Emergencies;
import Agents.Agent;
import Locations.Location;
import enums.*;
import MarsColonies.*;
import TimeConcept.TimeOperation;
import java.util.ArrayList;

public abstract class Emergency implements TimeOperation{
    private ResponderType[] requiredResponders;
    private Agent[] assignedResponders;
    private String failureReason;
    private ResourceType[] requiredResources;
    private int[] requiredAmountsOfResources;
    private Location EmergencyLocation;
    private EmergencyType emergencytype;
    private EmergencyStatus status;


    public Emergency(ResponderType[] requiredResponders,ResourceType[] requiredResources, 
                      int [] requiredAmountsOfResources, 
                          Location EmergencyLocation,EmergencyType emergencytype){

        this.requiredResponders=requiredResponders;
        this.requiredResources=requiredResources;
        this.requiredAmountsOfResources=requiredAmountsOfResources;
        this.EmergencyLocation=EmergencyLocation;
        this.emergencytype=emergencytype;
        this.status=EmergencyStatus.ACTIVE;


    }

    public EmergencyType getEmergencyType(){
        return emergencytype;
    }
    public EmergencyStatus getEmergencyStatus(){
        return status;
    }

    public void setEmergencyStatus(EmergencyStatus status){
        this.status=status;
    }

    public void setFailureReason(String reason){
        failureReason=reason;
    }
    public void fail(String reason){
        setEmergencyStatus(EmergencyStatus.UNRESOLVED);
        setFailureReason(reason);
    }

    public boolean isEmergencyResolved(){
        return this.status == EmergencyStatus.RESOLVED;
    }

    public boolean handleEmergency(MarsColony colony){
        if (!isEmergencyResolved()){
            applyInitialImpact(colony);
            boolean respondersRequested=requestResponders(colony);
            if (!respondersRequested){
                fail("No responders available");
                setEmergencyStatus(EmergencyStatus.UNRESOLVED);
                return false;
            }
            resolve(colony);
            handle(colony);
            setEmergencyStatus(EmergencyStatus.RESOLVED);
            return true;
        }
        return false;
    }


    public boolean requestResponders(MarsColony colony){
        
        Agent[] availableAgents = colony.getColonyAgents();
        ArrayList<Agent> temp = new ArrayList<>();
        int numRequiredResponders = requiredResponders.length;
        assignedResponders = new Agent[numRequiredResponders];
    
        for (ResponderType requiredResponder : requiredResponders) {
            boolean responderAssigned = false;
            for (Agent agent : availableAgents) {
                if(agent instanceof EmergencyResponder) {
                    EmergencyResponder emergencyResponder = (EmergencyResponder) agent;
                    if (emergencyResponder.getResponderType() == requiredResponder 
                        && emergencyResponder.canHandleEmergency(this.getEmergencyType()) && emergencyResponder.isAvailable()) {
                        temp.add(agent);
                        responderAssigned = true;
                        break; // Move to the next required responder
                    }
                }   
            }
            if (!responderAssigned) {
                return false; // Not enough responders available
            }
        }
        assignedResponders = temp.toArray(new Agent[0]);
        return true; // All required responders assigned

       }
   }

   public boolean hasEnoughResources(MarsColony colony){ {
        for (int i = 0; i < this.requiredResources.length; i++) {
            int totalAvailable = colony.getTotalQuantityOfResources(this.requiredResources[i]);

            if (totalAvailable < this.requiredAmountsOfResources[i]) {
                setFailureReason("Not enough " + this.requiredResources[i]);
                return false;
            }
        }
        return true;
    }

    protected abstract void applyInitialImpact(MarsColony colony);
    public abstract void resolve(MarsColony colony);
    public abstract void handle(MarsColony colony);
    
}