package Resources;

import enums.*;
import Locations.*;

public class ResourceUsage {
    private ResourceType type;
    private int amount;
    private Location source;

    public ResourceUsage(ResourceType type, int amount, Location source) {
        this.type = type;
        this.amount = amount;
        this.source = source;
    }

    public ResourceType getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public Location getSource() {
        return source;
    }
}