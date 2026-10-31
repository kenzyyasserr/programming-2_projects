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
    public void rescue()
    {
        fatigueLevel+=5;
        healthAnalysis(fatigueLevel);
    }    
}