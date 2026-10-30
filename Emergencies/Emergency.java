package Emergencies;
import enums.*;

public class Emergency{
    private EmergencyType type;
    private EmergencyStatus status;

    public EmergencyType getEmergencyType(){
        return type;
    }
    public EmergencyStatus getEmergencyStatus(){
        return status;
    }

    public void setEmergencyStatus(EmergencyStatus status){
        this.status=status;
    }
}