package Missions;

import java.util.ArrayList;

import Agents.Agent;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;


public class ExplorationMission extends Mission {

    public ExplorationMission(Location targetLocation,int duration,
         ResourceType[] neededTypes, int[] neededAmounts, 
        Skill[] requiredSkills, EquipmentType[] requiredEquipment,MarsColony colony){
            super(targetLocation,duration,neededTypes,neededAmounts,requiredSkills,requiredEquipment,colony);
        }
    public abstract boolean executeMission();
    public abstract boolean assignAgents();
    public boolean fail(){

    }

    public abstract boolean validateMission();
    @Override
    public boolean requiresSurfaceAccess(){
        return true;
    }
    @Override 
    public boolean requiresCommunication(){
        return false;
    }
    @Override 
    public boolean checkEquipment(EquipmentType equipment){
        //check that rover has 

    }
    public abstract boolean Start();
    public abstract boolean canAssign();
    public boolean usesRover(){
        return true;
    }
    public abstract void onComplete(); //what changes after mission completes (aka if something gets fixed, change isOperational)
}
