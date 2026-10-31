package Resources;
import enums.ResourceType;


public class Food extends Resource {
    private final static int MAX = 300;
    public Food(int quantity) {
        super(quantity, MAX, ResourceType.FOOD);
    }
}