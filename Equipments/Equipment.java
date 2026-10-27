package Equipments;
import enums.EquipmentType;

public class Equipment{
    private EquipmentType type;
    private Boolean isOperational;
    public Equipment(EquipmentType type){
        this.type = type;
    }

    public boolean isUsable(){
        return isOperational;
    }
    public EquipmentType getType(){
        return type;
    }
    public void damage(){
        if(isOperational){
            isOperational = false;
        }
    }
    public void repair(){
        if(!isOperational){
            isOperational = true;
        }
    }
}