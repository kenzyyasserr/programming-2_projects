package Agents;
import Locations.ColonyFacility;
import enums.*;

public class FireFighter extends HumanPersonnel implements EmergencyResponder
{
    private static final Skill[] FIRE_FIGHTER_SKILLS = {Skill.FIREFIGHTING};
    // ------------------------- Constructor -------------------------
    public FireFighter(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel, FIRE_FIGHTER_SKILLS);
    }

    // ---------------- Methods Implementation -----------------
    @Override
    public boolean hasSkill(Skill skill)
    {
        boolean skilled;
        switch (skill) {
            case FIREFIGHTING:
                System.out.println("Fire fighter is skilled for this mission");
                skilled = true;
                break;

            default:
                System.out.println("Fire fighter is not skilled enough for this mission");
                skilled = false;
                break;
        }
        return skilled;
    }
    public void rescue()
    {
        fatigueLevel+=5;
        healthAnalysis(fatigueLevel);
        this.onMission = true;
    }
}