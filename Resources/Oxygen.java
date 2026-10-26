package Resources;
import enums.ResourceType;


public class Oxygen extends Resource {
    public Oxygen(int quantity, int max) {
        super(quantity, max, ResourceType.OXYGEN);
    }
}