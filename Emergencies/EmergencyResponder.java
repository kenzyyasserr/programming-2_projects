package Emergencies;
import enums.*;

public interface EmergencyResponder {
   public boolean isAvailable();
   public boolean canHandleEmergency(EmergencyType type);
   public void handleEmergency(Emergency incident);
   public ResponderType getResponderType();


}