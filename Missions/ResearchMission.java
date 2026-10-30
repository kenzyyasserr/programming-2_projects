package Missions;

import java.util.ArrayList;

import Agents.Agent;
import Equipments.Equipment;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;

public class ResearchMission extends Mission {

    
    public boolean hasRequiredFacilityEquipment(){ //used in lab
        ColonyFacility lab = (ColonyFacility) targetLocation;
        Equipment[] labEquipment = lab.getEquipment();
        for(int i=0; i<labEquipment.length;i++){
            if(labEquipment[i].getType()==requiredFacilityEquipment && labEquipment[i].isUsable()){
                return true;
            }
        }
    } 
    return false;
}
