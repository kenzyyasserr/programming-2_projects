package Missions;
import Agents.*;
import enums.*;
import Locations.*;
import MarsColonies.MarsColony;
import Equipments.*;

public class RepairMission extends Mission {
    private static final ResourceType[] REPAIR_RESOURCES = {ResourceType.SPARE_PARTS};
    private static final int[] NEEDED_AMOUNTS = {5};
    private static final Skill[] REPAIR_MISSION_SKILLS = {Skill.ELECTRONICS,Skill.WELDING,Skill.MECHANICS};
    private static final EquipmentType[] REQUIRED_ROBOT_EQUIPMENT = {EquipmentType.TOOLKIT, EquipmentType.WELDING_TORCH};
    private static final EquipmentType[] REQUIRED_FACILITY_EQUIPMENT = {};

    private CanBeFixed itemToBeFixed;
    public RepairMission(Location targetLocation,MarsColony colony,CanBeFixed itemToBeFixed){
            super(targetLocation,REPAIR_RESOURCES,NEEDED_AMOUNTS,REPAIR_MISSION_SKILLS,REQUIRED_ROBOT_EQUIPMENT,REQUIRED_FACILITY_EQUIPMENT,colony);
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
        return false;
    }
    @Override 
    public boolean requiresCommunication(){
        return false;
    }
    @Override
    //has extra info to print because it also prints the information of item that willbe fixed
    public void printMissionInfo() {
        super.printMissionInfo();

        System.out.println("Item being repaired: " + itemToBeFixed);
        if (itemToBeFixed instanceof Equipment equipment) {
            System.out.println("Operational: " + equipment.isOperational());
        }
        else if (itemToBeFixed instanceof ColonyFacility facility) {
            System.out.println("Damaged: " + facility.isOperational(colony.getEnvironmentCondition()));
        }
    }
}
