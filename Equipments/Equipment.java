package Equipments;
import enums.EquipmentType;

public class Equipment implements CanBeFixed
{
    // ------------------ Attributes ---------------------------
    private EquipmentType type;
    private boolean isOperational = true;

    // ----------------- Methods ---------------------------------
    public Equipment(EquipmentType type) {this.type = type;}

    public boolean isOperational() {return isOperational;}

    public EquipmentType getType(){return type;}
    
    public void damage()
    {
        if(isOperational)
        {
            isOperational = false;
        }
    }

    public void repair()
    {
        if(!isOperational)
        {
            isOperational = true;
        }
    }
}