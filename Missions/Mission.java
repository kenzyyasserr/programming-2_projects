package Missions;

import java.util.ArrayList;
import java.util.Arrays;
import Agents.Agent;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;
import TimeConcept.TimeOperation;

public abstract class Mission implements TimeOperation
{
    private ResourceType[] neededTypes; 
    private int[] neededAmounts; 
    private MissionState state = MissionState.PENDING;
    private MissionType type;
    private Skill[] requiredSkills;
    private Agent[] assignedAgents;
    private EquipmentType[] requiredRobotEquipment;
    protected EquipmentType[] requiredFacilityEquipment;
    protected Location targetLocation;
    private final int duration=3; //duration time for mission is 3 for all missions because there are three stages
    private String failureReason;
    protected MarsColony colony;
    private Location[] originalAgentLocations;
    private boolean executionSuccessful; //used to track if execution was successful
    private int currentStage = 0;

    public Mission(Location targetLocation,
         ResourceType[] neededTypes, int[] neededAmounts, 
        Skill[] requiredSkills, EquipmentType[] requiredRobotEquipment,
        EquipmentType[] requiredFacilityEquipment, MarsColony colony){

            this.targetLocation = targetLocation;
            this.neededTypes = neededTypes;
            this.neededAmounts = neededAmounts;
            this.requiredSkills = requiredSkills;
            this.requiredRobotEquipment = requiredRobotEquipment;
            this.requiredFacilityEquipment = requiredFacilityEquipment;
            this.colony = colony;
        }

    public boolean consumeMissionResources() {
    if (!hasEnoughResources()) {
        return false;
    }
    Location[] locations = colony.getLocations();
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

    private boolean assignAgents() {
    ArrayList<Agent> temp = new ArrayList<>();
    for (Skill skill : requiredSkills) {
        boolean found = false;
        for (Agent agent : colony.getColonyAgents()) {
            if (agent != null && agent.canPerformMission(skill, null)) {
                if (!temp.contains(agent)) {
                    temp.add(agent);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            failureReason = "No available agent for skill: " + skill;
            return false;
        }
    }
    // Assign agents for required robotic equipment
    for (EquipmentType equipment : requiredRobotEquipment) {
        boolean found = false;
        for (Agent agent : colony.getColonyAgents()) {

            if (agent != null && agent.canPerformMission(null, equipment)) {
                if (!temp.contains(agent)) {
                    temp.add(agent);
                }
                found = true;
                break;
            }
        }
        if (!found) {
            failureReason = "No available robot with equipment: " + equipment;
            return false;
        }
    }
    assignedAgents = temp.toArray(new Agent[0]);
    // Update their states
    for (Agent agent : assignedAgents) {
        agent.setOnMission(this);
    }
    return true;
}


    private void fail(String reason) {
        failureReason = reason;
        setMissionState(MissionState.FAILED);
    }

/*---------------------------------------------------------------------------------
CHECKING REQUIRMENTS
 ---------------------------------------------------------------------------------*/
    private boolean noOtherActiveMissions(){
        if(colony.getActiveMission()!=null){
            failureReason = "Another mission is already active";
            return false;
        }
        return true;
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

    private boolean checkEnvironment() {
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

    private boolean hasRequiredFacilityEquipment() {
        if (requiredFacilityEquipment == null || requiredFacilityEquipment.length == 0) {
            return true;
        }
        if (!targetLocation.isIndoorsFacility()) {
            return true; // Outdoor locations have no facility equipment
        }
        ColonyFacility facility = (ColonyFacility) targetLocation;
        for (EquipmentType equipment : requiredFacilityEquipment) {
            if (!facility.hasEquipment(equipment)) {
                failureReason = "Required facility equipment not available: " + equipment;
                return false;
            }
        }
        return true;
    }
    //checks all the requirements
    public boolean canStart(){
        if(!noOtherActiveMissions()){
            fail(failureReason);
            return false;
        }
        if(!checkEnvironment()){
            fail(failureReason);
            return false;
        }
        if(!hasRequiredAgents()){
            fail(failureReason);
            return false;
        }
        if(!hasRequiredFacilityEquipment()){
            fail(failureReason);
            return false;
        }
        if(!hasEnoughResources()){
            fail(failureReason);
            return false;
        }
        return true;
    }

/*----------------------------------------------------------------
MISSION STEPS:
-----------------------------------------------------------------*/
    public boolean start() {
        if (!canStart()) {
            return false;
        }
        if (!assignAgents()) {
            return false;
        }
        // Save original locations before moving agents
        originalAgentLocations = new Location[assignedAgents.length];
        for (int i = 0; i < assignedAgents.length; i++) {
            originalAgentLocations[i] = assignedAgents[i].getCurrentLocation();
            assignedAgents[i].setCurrentLocation(targetLocation);
            assignedAgents[i].setOnMission(this);
        }
        setMissionState(MissionState.ASSIGNED);
        return true;
    }

    public boolean activateMission() {
        if (state != MissionState.ASSIGNED) {
            return false;
        }
        if (!consumeMissionResources()) {
            return false;
        }
        if (!colony.setActiveMission(this)) {
            failureReason = "Another mission is already active";
            return false;
        }
        setMissionState(MissionState.ACTIVE);
        executionSuccessful = executeMission();
        return executionSuccessful;
    }

    private void finishMission() {
        if (executionSuccessful && state!=MissionState.FAILED) {
            onComplete();
            setMissionState(MissionState.COMPLETED);
        } else {
            setMissionState(MissionState.FAILED);
        }
        // Return agents to their original locations
        for (int i = 0; i < assignedAgents.length; i++) {
            assignedAgents[i].setCurrentLocation(originalAgentLocations[i]);
            assignedAgents[i].setOnMission(null);
        }
        colony.setActiveMission(null);
    }
    protected abstract boolean executeMission(); //the actual mission execution depending on the mission type
    protected abstract void onComplete(); //what changes after mission completes (aka if something gets fixed, change isOperational and bring back agents to original location if mission successful)
    public boolean isFinished(){
        return (state==MissionState.COMPLETED || state==MissionState.FAILED);
    }
/*----------------------------------------------------------------------------------
Check properties of mission (aka require surface access or no and communication or no)
 -------------------------------------------------------------------------------------*/
    public abstract boolean requiresSurfaceAccess();
    public abstract boolean requiresCommunication();
  
/*--------------------------------------------------------------------------------------------
TIME HANDLING
 ---------------------------------------------------------------------------------------------*/
 
    public void timePass(){
        if (isFinished()) {
            return;
        }
        // Check environment while mission is active
        if (state == MissionState.ACTIVE && !checkEnvironment()) {
            fail(failureReason);
            finishMission();
            return;
        }
        switch (currentStage) {
            case 0:
                if (!start()) {
                    fail(failureReason);
                    return;
                }
                break;
            case 1:
                if (!activateMission()) {
                    fail(failureReason);
                    finishMission();
                    return;
                }
                break;

            case 2:
                finishMission();
                return;
        }
        currentStage++;
    }
    public void setMissionState(MissionState state){
        this.state = state;
    }
    public void printMissionInfo(){
        System.out.printf("Mission Type: %s\nMission State: %s\nLocation: %s\nAgents: %s\n\n",type,state,targetLocation.getLocationType(),Arrays.toString(assignedAgents));
    }
    /*-----------------------------------------------------
    GETTERS:
    ---------------------------------------------------- */
    public String getFailureReason() {
        return failureReason;
    }
    public Agent[] getAssignedAgents(){
        return Arrays.copyOf(assignedAgents,assignedAgents.length);
    }
    public MissionType getMissionType(){
        return type;
    }
    public MissionState getMissionState(){
        return state;
    }
}