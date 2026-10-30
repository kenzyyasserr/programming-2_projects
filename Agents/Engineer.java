package Agents;
import enums.*;
import Equipments.Equipment;
import Locations.ColonyFacility;

public class Engineer extends HumanPersonnel
{
    private static final Skill[] ENG_SKILLS = {Skill.ELECTRONICS, Skill.WELDING,Skill.MECHANICS};
    // ------------------------- Constructor -------------------------
    public Engineer(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel, ENG_SKILLS);
    }

    // ---------------- Methods Implementation -----------------
    @Override
    public boolean hasSkill(Skill skill)
    {
        boolean skilled;
        switch (skill) {
            case ELECTRONICS:
                System.out.println("Engineer is skilled for this mission");
                skilled = true;
                break;

            case WELDING:
                System.out.println("Engineer is skilled for this mission");
                skilled = true;
                break;

            case MECHANICS:
                System.out.println("Engineer is skilled for this mission");
                skilled = true;
                break;
            default:
                System.out.println("Engineer is not skilled enough for this mission");
                skilled = false;
                break;
        }
        return skilled;
    }

    void repairEquipment(Equipment equipment)
    {
        fatigueLevel++;
        healthAnalysis(fatigueLevel);
    }

    void repairFacility(ColonyFacility facility)
    {
        fatigueLevel++;
        healthAnalysis(fatigueLevel);
    }
}