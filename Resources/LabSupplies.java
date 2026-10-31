package Resources;
import enums.ResourceType;


public class LabSupplies extends Resource{
    private final static int MAX = 100;
    public LabSupplies(int quantity) {
        super(quantity,100, ResourceType.LAB_SUPPLIES);
    }
}