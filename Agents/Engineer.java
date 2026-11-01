package Agents;
import Emergencies.*;
import Equipments.CanBeFixed;
import Locations.ColonyFacility;
import enums.*;

public class Engineer extends HumanPersonnel implements RepairAbility,EmergencyResponder
{
    private static final Skill[] ENG_SKILLS = {Skill.ELECTRONICS, Skill.WELDING,Skill.MECHANICS};
    // ------------------------- Constructor -------------------------
    public Engineer(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel, ENG_SKILLS);
    }

    // ---------------- Methods Implementation -----------------
    @Override
    public void repair(CanBeFixed itemToBeFixed){
        if(itemToBeFixed!=null){
            itemToBeFixed.repair();
            fatigueLevel++;
            healthAnalysis(fatigueLevel);
        }
    }
    @Override
    public boolean isAvailable() {return this.isUsable() && !this.onMission;}

    @Override
    public boolean canHandleEmergency(EmergencyType type) {return type == EmergencyType.ROVER_BREAKDOWN;}

    @Override
    public void handleEmergency(Emergency incident)
    {
        if (this.canHandleEmergency(incident.getEmergencyType()) && this.isAvailable())
        {
            System.out.println("Engineer " + this.getId() + " is handling the emergency: " + incident.getEmergencyType());
            incident.setEmergencyStatus(EmergencyStatus.RESOLVED); // Mark the emergency as resolved
        } 
        else {
            System.out.println("Engineer " + this.getId() + " cannot handle the emergency: " + incident.getEmergencyType());
        }
    }

    @Override
    public ResponderType getResponderType() {
        return ResponderType.ENGINEER;
    }

}