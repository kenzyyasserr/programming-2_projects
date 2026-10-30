package Missions;

import Agents.Agent;
import Agents.ExplorationRover;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;


public class ExplorationMission extends Mission {
    private static final ResourceType[] EXPLORATION_RESOURCES = {ResourceType.OXYGEN,ResourceType.WATER,ResourceType.FUEL,ResourceType.BATTERY_POWER};
    private static final int[] NEEDED_AMOUNTS = {200,100,100,50};
    private static final Skill[] EXPLORATION_MISSION_SKILLS = {};
    private static final EquipmentType[] REQUIRED_ROBOT_EQUIPMENT = {EquipmentType.DRILL, EquipmentType.SAMPLE_COLLECTOR};
    private static final EquipmentType[] REQUIRED_FACILITY_EQUIPMENT = {};

    public ExplorationMission(Location targetLocation,
        ResourceType[] neededTypes, int[] neededAmounts, 
        Skill[] requiredSkills, EquipmentType[] requiredRobotEquipment,
        EquipmentType[] requiredFacilityEquipment, MarsColony colony){
            super(targetLocation,EXPLORATION_RESOURCES,NEEDED_AMOUNTS,EXPLORATION_MISSION_SKILLS,REQUIRED_ROBOT_EQUIPMENT,REQUIRED_FACILITY_EQUIPMENT,colony);
        }
    @Override
    protected boolean executeMission() {
        Agent[] agents = getAssignedAgents();
        if (agents == null) {
            return false;
        }
        boolean roverFound = false;
        for (Agent agent : agents) {
            if (agent instanceof ExplorationRover) {
                ExplorationRover rover = (ExplorationRover) agent;
                rover.explore();
                roverFound = true;
            }
        }
        return roverFound;
        }

    @Override
    public boolean requiresSurfaceAccess(){
        return true;
    }
    @Override 
    public boolean requiresCommunication(){
        return false;
    }
}
