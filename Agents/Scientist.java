package Agents;

import Locations.ColonyFacility;
import enums.*;
import Emergencies.*;

public class Scientist extends HumanPersonnel implements EmergencyResponder {

    private static final Skill[] SCIENTIST_SKILLS = {Skill.BIOLOGY, Skill.CHEMISTRY, Skill.GEOLOGY, Skill.VEHICLE_OPERATION};

    // -------------------------- Constructor ----------------------
    public Scientist(String name, String id, ColonyFacility currentLocation, int fatigueLevel) {
        super(name, id, currentLocation, fatigueLevel, SCIENTIST_SKILLS);
    }

    // ------------------ Methods Implementation ------------------------
    public void research() {
        fatigueLevel += 5;
        healthAnalysis(fatigueLevel);
    }

    public void explore() {
        fatigueLevel += 5;
        healthAnalysis(fatigueLevel);
    }
    
    @Override
    public boolean isAvailable() {
        return this.isUsable() && !this.onMission;
    }

    @Override
    public boolean canHandleEmergency(EmergencyType type) {
        return type == EmergencyType.MEDICAL || type == EmergencyType.OXYGEN_LEAK;
    }

    @Override
    public void handleEmergency(Emergency incident)
    {
        if (this.canHandleEmergency(incident.getEmergencyType()) && this.isAvailable())
        {
            System.out.println("Scientist " + this.getId() + " is handling the emergency: " + incident.getEmergencyType());
            incident.setEmergencyStatus(EmergencyStatus.RESOLVED); // Mark the emergency as resolved
        } 
        else {
            System.out.println("Scientist " + this.getId() + " cannot handle the emergency: " + incident.getEmergencyType());
        }
    }


    @Override
    public ResponderType getResponderType() {
        return ResponderType.SCIENTIST;
    }
}
