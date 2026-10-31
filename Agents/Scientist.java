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