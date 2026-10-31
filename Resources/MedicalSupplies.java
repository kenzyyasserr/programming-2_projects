package Resources;
import enums.ResourceType;


public class MedicalSupplies extends Resource{
    private final static int MAX = 100;
    public MedicalSupplies(int quantity) {
        super(quantity,100, ResourceType.MEDICAL_SUPPLIES);
    }
}