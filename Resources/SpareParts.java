package Resources;
import enums.ResourceType;


public class SpareParts extends Resource {
    public SpareParts(int quantity, int max) {
        super(quantity, max, ResourceType.SPARE_PARTS);
    }
}