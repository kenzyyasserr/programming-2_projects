package enums;

public enum EnvironmentCondition {
    NORMAL,
    DUST_STORM,
    COMMUNICATION_DISRUPTION;

    public boolean allowsSurfaceExploration() {
        return this != DUST_STORM;
    }

    public boolean allowsRemoteDroneControl() {
        return this != COMMUNICATION_DISRUPTION;
    }

    public boolean allowsCommunicationDependentOperations() {
        return this != COMMUNICATION_DISRUPTION;
    }
}

