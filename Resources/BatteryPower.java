package Resources;
import enums.ResourceType;

public class BatteryPower extends Resource {
    private final static int MAX = 100;
    public BatteryPower(int quantity) {
        super(quantity,MAX, ResourceType.BATTERY_POWER);
    }
}