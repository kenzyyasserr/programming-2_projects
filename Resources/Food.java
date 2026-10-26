package Resources;

public class Food extends Resource {
    public Food(int quantity, int max) {
        super(quantity, max, ResourceType.FOOD);
    }
}