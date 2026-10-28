package Agents;
import enums.HealthStatus;
import Missions.*;
import Locations.ColonyFacility;
import Locations.Location;


abstract class Agent
{
    // --------------------------- Attributes ----------------------------------
    private ColonyFacility currentLocation;
    private HealthStatus healthStatus;
    private String id;
    private boolean onMission;

    // -------------------------- Constructors ---------------------------------------
    public Agent(ColonyFacility currentLocation, String id, boolean onMission)
    {
        this.currentLocation = currentLocation;
        this.healthStatus = HealthStatus.HEALTHY; // Default health status
        this.id = id;
        this.onMission = onMission;
    }

    public HumanPersonnel(String name, Skill[] skills, int fatigueLevel)
    {
        this.name = name;

    }

    // --------------------------- Abstract Methods ----------------------------------
    public abstract boolean canPerformMission(Mission mission);
    public abstract boolean isUsable();

    // ------------------------ Concrete Methods ---------------------------------------
    public HealthStatus getHealth() {return healthStatus;}
    public Location getCurrentLocation() {return currentLocation;}
    public String getId() {return id;}

    public void setCurrentLocation(Location location) {currentLocation = location;}

    public void setOnMission(Mission mission) {Mission = mission;}

    public void changeHealth(String status)
    {

    }

    public boolean isAvailable()
    {

    }
}