package Resources;
import enums.ResourceType;


public class MedicalSupplies extends Resource{
    public MedicalSupplies(int quantity, int max) {
        super(quantity, max, ResourceType.MEDICAL_SUPPLIES);
    }
}