package Agents;
import Emergencies.*;
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


    @Override
    public boolean isAvailable() {return this.isUsable() && !this.onMission;}

    @Override
    public boolean canHandleEmergency(EmergencyType type) {return type == EmergencyType.MEDICAL;}

    @Override
    public void handleEmergency(Emergency incident)
    {
        if (this.canHandleEmergency(incident.getEmergencyType()) && this.isAvailable())
        {
            System.out.println("MedicalOfficer " + this.getId() + " is handling the emergency: " + incident.getEmergencyType());
            incident.setEmergencyStatus(EmergencyStatus.RESOLVED); // Mark the emergency as resolved
        } 
        else {
            System.out.println("MedicalOfficer " + this.getId() + " cannot handle the emergency: " + incident.getEmergencyType());
        }
    }


    @Override
    public ResponderType getResponderType() {return ResponderType.MEDICAL_OFFICER;}
}