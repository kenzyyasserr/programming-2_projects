package Missions;
import Agents.*;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;

public class RescueMission extends Mission {
    private static final ResourceType[] RESCUE_RESOURCES = {ResourceType.OXYGEN,ResourceType.MEDICAL_SUPPLIES,ResourceType.WATER};
    private static final int[] NEEDED_AMOUNTS = {100,10,50};
    private static final Skill[] RESCUE_MISSION_SKILLS = {Skill.FIRST_AID,Skill.FIREFIGHTING};
    private static final EquipmentType[] REQUIRED_ROBOT_EQUIPMENT = {};
    private static final EquipmentType[] REQUIRED_FACILITY_EQUIPMENT = {};

    public RescueMission(Location targetLocation, MarsColony colony){
            super(targetLocation,RESCUE_RESOURCES,NEEDED_AMOUNTS,RESCUE_MISSION_SKILLS,REQUIRED_ROBOT_EQUIPMENT,REQUIRED_FACILITY_EQUIPMENT,colony);
        }
    @Override
    protected boolean executeMission() {
        Agent[] agents = getAssignedAgents();
        if (agents == null) {
            return false;
        }
        boolean found = false;
        for (Agent agent : agents) {
            if (agent instanceof MedicalOfficer) {
                MedicalOfficer officer = (MedicalOfficer) agent;
                officer.rescue();
                found=true;
            }
            else if(agent instanceof FireFighter){
                FireFighter fighter = (FireFighter) agent;
                fighter.rescue();
                found=true;
            }
        }
        return found;
    }

    @Override
    public boolean requiresSurfaceAccess(){
        return true;
    }
    @Override 
    public boolean requiresCommunication(){
        return true;
    }
}
