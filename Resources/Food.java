package Resources;
import enums.ResourceType;


public class Food extends Resource {
    public Food(int quantity, int max) {
        super(quantity, max, ResourceType.FOOD);
    }
}