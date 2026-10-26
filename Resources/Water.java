package Resources;
import enums.ResourceType;

public class Water extends Resource {
    public Water(int quantity, int max) {
        super(quantity, max, ResourceType.WATER);
    }
}