package Agents;
import Locations.ColonyFacility;
import enums.*;

public class MedicalOfficer extends HumanPersonnel implements EmergencyResponder
{
    private static final Skill[] MEDICAL_OFFICER_SKILLS = {Skill.SURGERY, Skill.FIRST_AID};
    // ------------------------- Constructor -------------------------
    public MedicalOfficer(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel, MEDICAL_OFFICER_SKILLS);
    }

    // ---------------- Methods Implementation -----------------
    @Override
    public boolean hasSkill(Skill skill)
    {
        boolean skilled;
        switch (skill) {
            case SURGERY:
                skilled = true;
                break;

            case FIRST_AID:
                skilled = true;
                break;
            default:
                skilled = false;
                break;
        }
        return skilled;
    }

    public void rescue()
    {
        fatigueLevel+=5;
        healthAnalysis(fatigueLevel);
    }    
}