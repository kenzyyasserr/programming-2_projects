package Emergencies;

import Agents.*;
import Locations.*;
import MarsColonies.*;
import TimeConcept.TimeOperation;
import enums.*;
import java.util.ArrayList;



      public abstract class Emergency implements TimeOperation {

        private ResponderType[] requiredResponders;
        private Agent[] assignedResponders;
        private String failureReason;
        private ResourceType[] requiredResources;
        private int[] requiredAmountsOfResources;
        private Location EmergencyLocation;
        private EmergencyType emergencytype;
        private EmergencyStatus status;
        private int currentStage = 0;
        private MarsColony colony;

        public Emergency(ResponderType[] requiredResponders, ResourceType[] requiredResources,
                int[] requiredAmountsOfResources,
                Location EmergencyLocation, EmergencyType emergencytype, MarsColony colony) {

            this.requiredResponders = requiredResponders;
            this.requiredResources = requiredResources;
            this.requiredAmountsOfResources = requiredAmountsOfResources;
            this.EmergencyLocation = EmergencyLocation;
            this.emergencytype = emergencytype;
            this.status = EmergencyStatus.ACTIVE;
            this.colony = colony;

        }

        public EmergencyType getEmergencyType() {
            return emergencytype;
        }

        public EmergencyStatus getEmergencyStatus() {
            return status;
        }

        public void setEmergencyStatus(EmergencyStatus status) {
            this.status = status;
        }

        public void setFailureReason(String reason) {
            failureReason = reason;
        }

        public void fail(String reason) {
            setEmergencyStatus(EmergencyStatus.UNRESOLVED);
            setFailureReason(reason);
        }

        public boolean isEmergencyResolved() {
            return this.status == EmergencyStatus.RESOLVED;
        }

        public boolean handleEmergency(MarsColony colony) {
            if (!isEmergencyResolved()) {
                applyInitialImpact();
                boolean respondersRequested = requestResponders(colony);
                if (!respondersRequested) {
                    fail("No responders available");
                    setEmergencyStatus(EmergencyStatus.UNRESOLVED);
                    return false;
                }
                boolean resourcesConsumed = consumeResources(colony);
                if (!resourcesConsumed) {
                    fail("Not enough resources");
                    setEmergencyStatus(EmergencyStatus.UNRESOLVED);
                    return false;
                }
                setEmergencyStatus(EmergencyStatus.RESOLVED);
                handle();
                return true;
            }
            return false;
        }

        public boolean requestResponders(MarsColony colony) {

            Agent[] availableAgents = colony.getColonyAgents();
            ArrayList<Agent> temp = new ArrayList<>();
            int numRequiredResponders = requiredResponders.length;
            assignedResponders = new Agent[numRequiredResponders];

            for (ResponderType requiredResponder : requiredResponders) {
                boolean responderAssigned = false;
                for (Agent agent : availableAgents) {
                    if (agent instanceof EmergencyResponder) {
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
    

    public boolean hasEnoughResources(MarsColony colony) {
        for (int i = 0; i < this.requiredResources.length; i++) {
            int totalAvailable = colony.getTotalQuantityOfResources(this.requiredResources[i]);

            if (totalAvailable < this.requiredAmountsOfResources[i]) {
                setFailureReason("Not enough " + this.requiredResources[i]);
                return false;
            }
        }
        return true;
    }

    public boolean consumeResources(MarsColony colony) {
        if (!hasEnoughResources(colony)) {
            setFailureReason("Not enough resources to consume");
            return false;
        }
        // If we reach here, it means we have enough resources, so we can consume them
        for (int i = 0; i < this.requiredResources.length; i++) {
            int amountToConsume = this.requiredAmountsOfResources[i];
            for (ColonyFacility facility : colony.getFacilities()) {
                int availableInFacility = facility.getResourceQuantity(this.requiredResources[i], colony.getEnvironmentCondition());
                if (availableInFacility >= amountToConsume) {
                    facility.consumeResource(this.requiredResources[i], amountToConsume);
                    break; // Move to the next resource
                } else {
                    facility.consumeResource(this.requiredResources[i], availableInFacility);
                    amountToConsume -= availableInFacility;
                }
            }
        }
        return true;
    }

    public void printEmergencyDetails() {
        System.out.println("Emergency Type: " + this.emergencytype);
        System.out.println("Emergency Status: " + this.status);
        System.out.println("Required Responders: ");
        for (ResponderType responder : this.requiredResponders) {
            System.out.println("- " + responder);
        }
        System.out.println("Required Resources: ");
        for (int i = 0; i < this.requiredResources.length; i++) {
            System.out.println("- " + this.requiredResources[i] + ": " + this.requiredAmountsOfResources[i]);
        }
        System.out.println("Emergency Location: " + this.EmergencyLocation);
    }

    @Override
    public void timePass() {
        // Implementation for time passing logic
        if(getEmergencyStatus() == EmergencyStatus.ACTIVE) {
            handleEmergency(colony);
            if(isEmergencyResolved()) {
                currentStage=2;
                System.out.println("Emergency resolved: " + this.emergencytype);
            } else {
                currentStage=1;
                System.out.println("Emergency still active: " + this.emergencytype);
            }
            
            // You can add more logic here based on the current stage of the emergency
        }

    }

    public abstract void applyInitialImpact();

    public abstract void handle();

}
