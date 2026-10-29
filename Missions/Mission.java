package Missions;

import java.util.ArrayList;

import Agents.Agent;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;

public abstract class Mission
{
    private ResourceType[] neededTypes; 
    private int[] neededAmounts; 
    private MissionState state;
    private MissionType type;
    private AgentType[] requiredAgentTypes;
    private Skill[] requiredSkills;
    private Agent[] assignedAgents;
    private EquipmentType[] requiredEquipment;
    private Location targetLocation;
    private int duration;
    private String failureReason ;

    public Mission(Location targetLocation,int duration,
         ResourceType[] neededTypes, int[] neededAmounts, 
         AgentType[] requiredAgentTypes, Skill[] requiredSkills, EquipmentType[] requiredEquipment){
            this.targetLocation = targetLocation;
            this.duration = duration;
            this.neededTypes = neededTypes;
            this.neededAmounts = neededAmounts;
            this.requiredAgentTypes = requiredAgentTypes;
            this.requiredSkills = requiredSkills;
            this.requiredEquipment = requiredEquipment;
        }
        
    
    public boolean consumeMissionResources(MarsColony colony, ArrayList<Location> locations) {
        EnvironmentCondition environmentCondition = colony.getEnvironmentCondition();

        // First: check that all required resources are available
        for (int i = 0; i < neededTypes.length; i++) {
            int totalAvailable = colony.getTotalQuantityOfResources(neededTypes[i]);

            if (totalAvailable < neededAmounts[i]) {
                failureReason = "Not Enough Resources: " + neededTypes[i];
                return false;
            }
        }

        // Second: consume the resources only after all checks pass
        for (int i = 0; i < neededTypes.length; i++) {
            int remaining = neededAmounts[i];

            // Try the target location first
            int available = targetLocation.getResourceQuantity(
                neededTypes[i], environmentCondition
            );

            if (targetLocation instanceof ColonyFacility facility) {
                int toConsume = Math.min(available, remaining);

                if (toConsume > 0) {
                    if (!facility.consumeResource(neededTypes[i], toConsume)) {
                        failureReason = "Resource Consumption Failed";
                        return false;
                    }
                    remaining -= toConsume;
                }
            }

            // Get any remaining amount from other locations
            for (Location location : locations) {
                if (remaining == 0) {
                    break;
                }

                // Avoid consuming from the target location twice
                if (location == targetLocation) {
                    continue;
                }

                if (location instanceof ColonyFacility facility) {
                    available = facility.getResourceQuantity(
                        neededTypes[i], environmentCondition
                    );

                    int toConsume = Math.min(available, remaining);

                    if (toConsume > 0) {
                        if (!facility.consumeResource(neededTypes[i], toConsume)) {
                            failureReason = "Resource Consumption Failed";
                            return false;
                        }
                        remaining -= toConsume;
                    }
                }
            }

            // Safety check: ensure the full amount was consumed
            if (remaining > 0) {
                failureReason = "Not Enough Resources: " + neededTypes[i];
                return false;
            }
        }

        failureReason = null;
        return true;
    }


    public void timePass(EnvironmentCondition env){

    }
    public void fail(){
        System.out.println(failureReason);
    }
    public boolean isFinished(){

    }
    public boolean requiresSurface(){

    }
    public void setMissionState(MissionState state){
        this.state = state;
    }

    Public void onComplete() //what changes after mission completes (aka if something gets fixed, change is to operational. Mostly does nothing except for repair case)
    Public boolean usesRover() //loops over requiredAgents array 

        



}