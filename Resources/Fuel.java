package Resources;
import enums.ResourceType;

public class Fuel extends Resource {
    private final static int MAX = 500;
    public Fuel(int quantity) {
        super(quantity, 500, ResourceType.FUEL);
    }
}