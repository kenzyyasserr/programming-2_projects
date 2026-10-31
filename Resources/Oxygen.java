package Resources;
import enums.ResourceType;


public class Oxygen extends Resource {
    private final static int MAX = 1000;
    public Oxygen(int quantity) {
        super(quantity, MAX, ResourceType.OXYGEN);
    }
}