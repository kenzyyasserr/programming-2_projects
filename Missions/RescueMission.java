package Missions;

import java.util.ArrayList;

import Agents.Agent;
import Agents.ExplorationRover;
import Agents.Scientist;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;


public class RescueMission extends Mission {

    public RescueMission(Location targetLocation,
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
        boolean scientistFound = false;
        for (Agent agent : agents) {
            if (agent instanceof Scientist) {
                Scientist scientist = (Scientist) agent;
                scientist.research();
                scientistFound= true;
            }
        }
        return scientistFound;
        }

    @Override
    public boolean requiresSurfaceAccess(){
        return false;
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
