package Agents;
import Equipments.CanBeFixed;
import Equipments.Equipment;
import Locations.ColonyFacility;
import enums.*;

public class Engineer extends HumanPersonnel implements RepairAbility
{
    private static final Skill[] ENG_SKILLS = {Skill.ELECTRONICS, Skill.WELDING,Skill.MECHANICS};
    // ------------------------- Constructor -------------------------
    public Engineer(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel, ENG_SKILLS);
    }

    // ---------------- Methods Implementation -----------------
    public void repair(CanBeFixed itemToBeFixed){
        if(itemToBeFixed!=null){
            itemToBeFixed.repair();
            fatigueLevel++;
            healthAnalysis(fatigueLevel);
        }
    }
}