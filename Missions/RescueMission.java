package Missions;

import java.util.ArrayList;

import Agents.*;
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
    @Override 
    public void onComplete(){
        //nothing gets fixed so nothing happens here
    } 
}
