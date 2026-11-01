package Emergencies;
import Agents.Agent;
import Locations.*;
import enums.*;
import MarsColonies.*;

public class OxygenLeak extends Emergency {

    private static final ResponderType[] requiredResponders = {ResponderType.REPAIR_ROBOT, ResponderType.SCIENTIST};
    private static final ResourceType[] requiredResources = {ResourceType.OXYGEN, ResourceType.FUEL};
    private static final int[] requiredAmountsOfResources = {5, 10}; // Example amounts, adjust as needed
    private static final Habitat EmergencyLocation; // This should be set to the specific habitat where the oxygen leak occurs
    private int oxygenLossAmount;

    public OxygenLeak(Habitat EmergencyLocation, int oxygenLossAmount) {
        super(requiredResponders, requiredResources, requiredAmountsOfResources, EmergencyLocation, EmergencyType.OXYGEN_LEAK);
        this.oxygenLossAmount = oxygenLossAmount;
    }

    public Habitat getAffectedHabitat() {
        return EmergencyLocation;
    }

    @Override
    public void applyInitialImpact(MarsColony colony) {
        Habitat affectedHabitat = getAffectedHabitat();
        affectedHabitat.reduceOxygenLevel(oxygenLossAmount);
    }

}
