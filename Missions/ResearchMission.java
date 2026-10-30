package Missions;

import java.util.ArrayList;

import Agents.Agent;
import Agents.ExplorationRover;
import Agents.Scientist;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;


public class ResearchMission extends Mission {
    private static final Skill[] RESEARCH_MISSION_SKILLS = {Skill.BIOLOGY,Skill.GEOLOGY,Skill.CHEMISTRY};
    public ResearchMission(Location targetLocation,
         ResourceType[] neededTypes, int[] neededAmounts, 
        Skill[] requiredSkills, EquipmentType[] requiredRobotEquipment,
        EquipmentType[] requiredFacilityEquipment, MarsColony colony){
            super(targetLocation,neededTypes,neededAmounts,RESEARCH_MISSION_SKILLS,requiredRobotEquipment,requiredFacilityEquipment,colony);
        }
    @Override
    protected boolean executeMission() {
        Agent[] agents = getAssignedAgents();
        if (agents == null) {
            return false;
        }
        boolean scientistFound = false;
        for (Agent agent : agents) {
             if (agent instanceof ExplorationRover) {
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
