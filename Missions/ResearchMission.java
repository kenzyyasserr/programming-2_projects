package Missions;
import Agents.Agent;
import Agents.Scientist;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;


public class ResearchMission extends Mission {
    private static final ResourceType[] RESEARCH_RESOURCES = {ResourceType.LAB_SUPPLIES,ResourceType.WATER};
    private static final int[] NEEDED_AMOUNTS = {10, 100};
    private static final Skill[] RESEARCH_MISSION_SKILLS = {Skill.BIOLOGY,Skill.GEOLOGY,Skill.CHEMISTRY};
    private static final EquipmentType[] REQUIRED_ROBOT_EQUIPMENT = {};
    private static final EquipmentType[] REQUIRED_FACILITY_EQUIPMENT = {EquipmentType.MICROSCOPE, EquipmentType.SAMPLE_ANALYZER};

    public ResearchMission(Location targetLocation,MarsColony colony){
            super(targetLocation,RESEARCH_RESOURCES,NEEDED_AMOUNTS,RESEARCH_MISSION_SKILLS,REQUIRED_ROBOT_EQUIPMENT,REQUIRED_FACILITY_EQUIPMENT,colony);
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
}
