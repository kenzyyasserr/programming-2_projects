package Resources;
import enums.ResourceType;

public class Water extends Resource {
    private final static int MAX = 1000;
    public Water(int quantity) {
        super(quantity, MAX, ResourceType.WATER);
    }
}