package Emergencies;
import Agents.*;
import Locations.*;
import enums.*;
import MarsColonies.*;


public class RoverBreakdown extends Emergency {

    private static final ResponderType[] requiredResponders = {ResponderType.REPAIR_ROBOT, ResponderType.ENGINEER};
    private static final ResourceType[] requiredResources = {ResourceType.SPARE_PARTS, ResourceType.BATTERY};
    private static final int[] requiredAmountsOfResources = {5, 10}; // Example amounts, adjust as needed
    private ExplorationRover rover; // This should be set to the specific rover that has broken down

   

    public RoverBreakdown(Location EmergencyLocation) {
        super(requiredResponders, requiredResources, requiredAmountsOfResources, EmergencyLocation, EmergencyType.ROVER_BREAKDOWN);
        
    }

    @Override
    public void applyInitialImpact() {
        // Implement the logic to apply the initial impact of the rover breakdown
        // For example, you might want to set the rover's status to "broken" or reduce its functionality
        rover.changeHealth(HealthStatus.UNHEALTHY); // Example action, adjust as needed
        
    }

    @Override
    public void handle() {
        // Implement the logic to handle the rover breakdown
        // For example, you might want to repair the rover or replace its parts 
        if(isEmergencyResolved()){
            rover.changeHealth(HealthStatus.HEALTHY); // Example action, adjust as needed
        }
    }


}
