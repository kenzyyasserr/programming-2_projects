package Missions;

import java.util.ArrayList;

import Agents.Agent;
import Agents.ExplorationRover;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;


public class ExplorationMission extends Mission {
    public ExplorationMission(Location targetLocation,
         ResourceType[] neededTypes, int[] neededAmounts, 
        Skill[] requiredSkills, EquipmentType[] requiredRobotEquipment,
        EquipmentType[] requiredFacilityEquipment, MarsColony colony){
            super(targetLocation,neededTypes,neededAmounts,requiredSkills,requiredRobotEquipment,requiredFacilityEquipment,colony);
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
    @Override 
    public void onComplete(){
        //nothing gets fixed so nothing happens here
    } 
}
