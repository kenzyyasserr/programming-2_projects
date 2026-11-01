package Emergencies;
import Agents.Agent;
import Locations.*;
import enums.*;
import MarsColonies.*;

public class MedicalEmergency extends Emergency {

    private static final ResponderType[] requiredResponders = {ResponderType.MEDICAL_OFFICER, ResponderType.SCIENTIST};
    private static final ResourceType[] requiredResources = {ResourceType.OXYGEN, ResourceType.FUEL};
    private static final int[] requiredAmountsOfResources = {5, 10}; // Example amounts, adjust as needed
    private MedicalBay EmergencyLocation; // This should be set to the specific habitat where the oxygen leak occurs
    private Agent patient; // This should be set to the specific agent who is in medical emergency

    public MedicalEmergency(MedicalBay EmergencyLocation, Agent patient) {
        super(requiredResponders, requiredResources, requiredAmountsOfResources, EmergencyLocation, EmergencyType.MEDICAL_EMERGENCY);
        this.patient = patient;
    }


    @Override
    public boolean handleEmergency(MarsColony colony) {
            if (!isEmergencyResolved()) {
                applyInitialImpact();
                boolean respondersRequested = requestResponders(colony);
                if (!respondersRequested) {
                    fail("No responders available");
                    setEmergencyStatus(EmergencyStatus.UNRESOLVED);
                    return false;
                }
                boolean resourcesConsumed = consumeResources(colony);
                if (!resourcesConsumed) {
                    fail("Not enough resources");
                    setEmergencyStatus(EmergencyStatus.UNRESOLVED);
                    return false;
                }
                if(EmergencyLocation.isOperational()){
                    fail("Medical Bay is not operational");
                    setEmergencyStatus(EmergencyStatus.UNRESOLVED);
                    return false;
                }
                setEmergencyStatus(EmergencyStatus.RESOLVED);
                handle();
                return true;
            }
            return false;
        }

    @Override
    public void applyInitialImpact() {
        patient.changeHealth(HealthStatus.UNHEALTHY); // Example action, adjust as needed
    }

    @Override
    public void handle() {
        if(isEmergencyResolved()){
            patient.changeHealth(HealthStatus.HEALTHY); // Example action, adjust as needed
        }
    }

}
