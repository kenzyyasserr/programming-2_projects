package Emergencies;
import Agents.Agent;
import Locations.*;
import enums.*;
import MarsColonies.*;

public class Fire extends Emergency {

    private static final ResponderType[] requiredResponders = {ResponderType.FIRE_FIGHTER , ResponderType.MEDICAL_OFFICER};
    private static final ResourceType[] requiredResources = {ResourceType.WATER, ResourceType.LAB_SUPPLIES, ResourceType.OXYGEN};
    private static final int[] requiredAmountsOfResources = {5, 10, 15}; // Example amounts, adjust as needed
    private Laboratory EmergencyLocation; // This should be set to the specific habitat where the oxygen leak occurs
    

    public Fire(Laboratory EmergencyLocation) {
        super(requiredResponders, requiredResources, requiredAmountsOfResources, EmergencyLocation, EmergencyType.OXYGEN_LEAK);
       
    }

    public Laboratory getAffectedLaboratory() {
        return EmergencyLocation;
    }

   
    @Override
    public void applyInitialImpact() {
        Laboratory affectedLaboratory = getAffectedLaboratory();
        affectedLaboratory.setIsDamaged(true); // Example amount, adjust as needed
        Equipment[] affectedEquipment = affectedLaboratory.getEquipment();
        for (Equipment equipment : affectedEquipment) {
            equipment.damage(); // Example amount, adjust as needed
        }
    }

    @Override
    public void handle() {
        if(!isEmergencyResolved()){
            Laboratory affectedLaboratory = getAffectedLaboratory();
            affectedLaboratory.setIsDamaged(false); // Example amount, adjust as needed
            Equipment[] affectedEquipment = affectedLaboratory.getEquipment();
            for (Equipment equipment : affectedEquipment) {
                equipment.repair(); // Example amount, adjust as needed
            }
            
        }
    }

}
