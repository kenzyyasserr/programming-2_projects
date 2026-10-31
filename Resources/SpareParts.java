package Resources;
import enums.ResourceType;


public class SpareParts extends Resource {
    private final static int MAX = 100;
    public SpareParts(int quantity) {
        super(quantity, MAX, ResourceType.SPARE_PARTS);
    }
}