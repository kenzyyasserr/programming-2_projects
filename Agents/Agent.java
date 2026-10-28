package Agents;
import enums.HealthStatus;
import Missions.Mission;
import Locations.ColonyFacility;
import Locations.Location;


public abstract class Agent
{
    // --------------------------- Attributes ----------------------------------
    protected ColonyFacility currentLocation;
    protected HealthStatus healthStatus;
    protected String id;
    protected boolean onMission;

    // -------------------------- Constructor ---------------------------------------
    protected Agent(ColonyFacility currentLocation, String id, boolean onMission)
    {
        this.currentLocation = currentLocation;
        this.healthStatus = HealthStatus.HEALTHY; // Default health status
        this.id = id;
        this.onMission = onMission;
    }

    // --------------------------- Abstract Methods ----------------------------------
    public abstract boolean isUsable();  // health status, fatigue levels and isAvailable

    // ------------------------ Concrete Methods ---------------------------------------
    public HealthStatus getHealth() {return healthStatus;}
    public Location getCurrentLocation() {return currentLocation;}
    public String getId() {return id;}

    public void setCurrentLocation(ColonyFacility location) {this.currentLocation = location;}

    public void setOnMission(Mission mission)
    {
        if(mission != null)
            this.onMission = true;
        else
            this.onMission = false;
    }

    public void changeHealth(String status)
    {

    }

    public boolean canPerformMission(Mission mission)  // checks isUsable and skills
    {

    }
}