package Agents;
import Locations.ColonyFacility;
import enums.*;

public class Scientist extends HumanPersonnel
{
    private static final Skill[] SCIENTIST_SKILLS = {Skill.BIOLOGY, Skill.CHEMISTRY,Skill.GEOLOGY,Skill.VEHICLE_OPERATION};
    // -------------------------- Constructor ----------------------
    public Scientist(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel,SCIENTIST_SKILLS);
    }

    // ------------------ Methods Implementation ------------------------
    @Override
    public boolean hasSkill(Skill skill)
    {
        boolean skilled;
        switch (skill) {
            case BIOLOGY:
                System.out.println("Scientist is skilled for this mission");
                skilled = true;
                break;

            case CHEMISTRY:
                System.out.println("Scientist is skilled for this mission");
                skilled = true;
                break;

            case GEOLOGY:
                System.out.println("Scientist is skilled for this mission");
                skilled = true;
                break;
            default:
                System.out.println("Scientist is not skilled enough for this mission");
                skilled = false;
                break;
        }
        return skilled;
    }

    public void research()
    {
        fatigueLevel+=5;
        healthAnalysis(fatigueLevel);
    }
    public void explore(){
        fatigueLevel+=5;
        healthAnalysis(fatigueLevel);
    }
}