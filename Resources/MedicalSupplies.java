package Resources;

public class MedicalSupplies extends Resource{
    public MedicalSupplies(int quantity, int max) {
        super(quantity, max, ResourceType.MEDICAL_SUPPLIES);
    }
}