package Agents;

import Emergencies.*;
import Locations.ColonyFacility;
import enums.*;

public class FireFighter extends HumanPersonnel implements EmergencyResponder {

    private static final Skill[] FIRE_FIGHTER_SKILLS = {Skill.FIREFIGHTING, Skill.VEHICLE_OPERATION};

    // ------------------------- Constructor -------------------------
    public FireFighter(String name, String id, ColonyFacility currentLocation, int fatigueLevel) {
        super(name, id, currentLocation, fatigueLevel, FIRE_FIGHTER_SKILLS);
    }

    // ---------------- Methods Implementation -----------------
    public void rescue() {
        fatigueLevel += 5;
        healthAnalysis(fatigueLevel);
    }

    @Override
    public boolean isAvailable() {
        return this.isUsable() && !this.onMission;
    }

    @Override
    public boolean canHandleEmergency(EmergencyType type) {
        return type == EmergencyType.FIRE;
    }

    @Override
    public void handleEmergency(Emergency incident) {
        if (this.canHandleEmergency(incident.getEmergencyType()) && this.isAvailable()) {
            System.out.println("FireFighter " + this.getId() + " is handling the emergency: " + incident.getEmergencyType());
            incident.setEmergencyStatus(EmergencyStatus.RESOLVED); // Mark the emergency as resolved
        } else {
            System.out.println("FireFighter " + this.getId() + " cannot handle the emergency: " + incident.getEmergencyType());
        }
    }

    @Override
    public ResponderType getResponderType() {
        return ResponderType.FIRE_FIGHTER;
    }
}
