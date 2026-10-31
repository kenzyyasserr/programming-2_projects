package Resources;
import enums.ResourceType;



public class Resource{
    protected int quantity;
    protected int max;
    protected ResourceType type;

    public Resource(int quantity, int max, ResourceType type) {
        if(quantity > max){
            quantity = max;
        }
        if(quantity<0){
            quantity = -quantity;
        }
        this.quantity = quantity;
        this.max = max;
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }
    public int getMax() {
        return max;
    }
    public ResourceType getType() {
        return type;
    }
    public double getPercentage() {
        return ((double) quantity / max )* 100;
    }
    public void reduceResource(int amount) {
        if (quantity - amount < 0) {
            System.out.println("Not enough " + type + " to reduce by " + amount);
        } else {
            quantity -= amount;
        }
    }
    public void print(){
        System.out.println(type + ": " + quantity + "/" + max + " (" + getPercentage() + "%)");
    }

}