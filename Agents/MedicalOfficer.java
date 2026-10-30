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
                System.out.println("Medical Officer is skilled for this mission");
                skilled = true;
                break;

            case FIRST_AID:
                System.out.println("Medical Officer is skilled for this mission");
                skilled = true;
                break;
            default:
                System.out.println("Medical Officer is not skilled enough for this mission");
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