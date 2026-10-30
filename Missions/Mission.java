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
    private Skill[] requiredSkills;
    private Agent[] assignedAgents;
    private EquipmentType[] requiredRobotEquipment;
    protected EquipmentType requiredFacilityEquipment;
    protected Location targetLocation;
    private int duration;
    private String failureReason;
    protected MarsColony colony;

    public Mission(Location targetLocation,int duration,
         ResourceType[] neededTypes, int[] neededAmounts, 
        Skill[] requiredSkills, EquipmentType[] requiredRobotEquipment,
        EquipmentType requiredFacilityEquipment, MarsColony colony){
            this.targetLocation = targetLocation;
            this.duration = duration;
            this.neededTypes = neededTypes;
            this.neededAmounts = neededAmounts;
            this.requiredSkills = requiredSkills;
            this.requiredRobotEquipment = requiredRobotEquipment;
            this.requiredFacilityEquipment = requiredFacilityEquipment;
            this.colony = colony;
        }

    public boolean hasEnoughResources() {

    for (int i = 0; i < neededTypes.length; i++) {
        int totalAvailable = colony.getTotalQuantityOfResources(neededTypes[i]);

        if (totalAvailable < neededAmounts[i]) {
            failureReason = "Not enough " + neededTypes[i];
            return false;
        }
    }

    return true;
    }

    public boolean consumeMissionResources(ArrayList<Location> locations) {
    if (!hasEnoughResources()) {
        return false;
    }
    EnvironmentCondition environmentCondition = colony.getEnvironmentCondition();
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
    //checks that required agents with required skills or equipment are available
    private boolean hasRequiredAgents(){
    // Check required skills
    for (Skill skill : requiredSkills) {
        boolean covered = false;
        for (Agent agent : colony.getColonyAgents()) {
            if (agent != null && agent.canPerformMission(skill, null)) {
                covered = true;
                break;
            }
        }
        if (!covered) {
            failureReason = "No available agent can perform: " + skill;
            return false;
        }
    }
    // Check required equipment
    for (EquipmentType equipment : requiredRobotEquipment) {
        boolean covered = false;
        for (Agent agent : colony.getColonyAgents()) {
            if (agent != null && agent.canPerformMission(null, equipment)) {
                covered = true;
                break;
            }
        }
        if (!covered) {
            failureReason = "No available agent has operational equipment: " + equipment;
            return false;
        }
    }
    return true;
    }
    public abstract boolean executeMission();
    public void Agent[] assignAgents();

    public void timePass(){
        System.out.printf("Mission Status: %s\n",state);
    }

    public abstract boolean fail();

    public boolean checkEnvironment() {
    EnvironmentCondition condition = colony.getEnvironmentCondition();
    switch (condition) {
        case NORMAL:
            return true;
        case DUST_STORM:
            if (requiresSurfaceAccess()) {
                failureReason = "Cannot start mission during a dust storm.";
                return false;
            }
            return true;
        case COMMUNICATION_DISRUPTION:
            if (requiresCommunication()) {
                failureReason = "Cannot start mission during communication disruption.";
                return false;
            }
            return true;
        default:
            return false;
        }
    }
    public abstract boolean validateMission();
    public boolean isFinished(){
        return (state==MissionState.COMPLETED || state==MissionState.FAILED);
    }

    public abstract boolean checkEquipment(EquipmentType equipment);
    public abstract boolean requiresSurfaceAccess();
    public abstract boolean requiresCommunication();
    public abstract boolean Start();
    public void setMissionState(MissionState state){
        this.state = state;
    }
    public abstract boolean canAssign();
    public abstract void onComplete(); //what changes after mission completes (aka if something gets fixed, change isOperational)

        



}