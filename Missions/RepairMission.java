package Missions;

import java.util.ArrayList;

import Agents.*;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;
import Equipments.*;

public class RepairMission extends Mission {
    private CanBeFixed itemToBeFixed;
    public RepairMission(Location targetLocation,
        ResourceType[] neededTypes, int[] neededAmounts, 
        Skill[] requiredSkills, EquipmentType[] requiredRobotEquipment,
        EquipmentType[] requiredFacilityEquipment, MarsColony colony,CanBeFixed itemToBeFixed){
            super(targetLocation,neededTypes,neededAmounts,requiredSkills,requiredRobotEquipment,requiredFacilityEquipment,colony);
            this.itemToBeFixed = itemToBeFixed;
        }

    @Override
    protected boolean executeMission() {
        Agent[] agents = getAssignedAgents();
        if (agents == null) {
            return false;
        }
        boolean found = false;
        for (Agent agent : agents) {
            if (agent instanceof RepairAbility) {
                RepairAbility fixer = (RepairAbility) agent;
                fixer.repair(itemToBeFixed);
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
