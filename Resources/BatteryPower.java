package Resources;
import enums.ResourceType;

public class BatteryPower extends Resource {
    public BatteryPower(int quantity, int max) {
        super(quantity, max, ResourceType.BATTERY_POWER);
    }
}