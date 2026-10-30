package Emergencies;
import enums.EmergencyType;

public interface EmergencyResponder {
   public boolean isAvailable();
   public boolean canHandleEmergency(EmergencyType type);
   public void handleEmergency(Emergency incident);


}