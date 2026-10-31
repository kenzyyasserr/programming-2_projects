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
                skilled = true;
                break;

            case CHEMISTRY:
                skilled = true;
                break;

            case GEOLOGY:
                skilled = true;
                break;
            
            case VEHICLE_OPERATION:
                skilled = true;
                break;
            default:
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