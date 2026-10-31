package Agents;
import Locations.ColonyFacility;
import enums.*;

public class FireFighter extends HumanPersonnel implements EmergencyResponder
{
    private static final Skill[] FIRE_FIGHTER_SKILLS = {Skill.FIREFIGHTING,Skill.VEHICLE_OPERATION};
    // ------------------------- Constructor -------------------------
    public FireFighter(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel, FIRE_FIGHTER_SKILLS);
    }

    // ---------------- Methods Implementation -----------------
    public void rescue()
    {
        fatigueLevel+=5;
        healthAnalysis(fatigueLevel);
    }
}