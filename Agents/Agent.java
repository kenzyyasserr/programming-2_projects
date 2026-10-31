package Agents;
import Locations.Location;
import Missions.*;
import enums.*;

public abstract class Agent
{
    // --------------------------- Attributes ----------------------------------
    protected Location currentLocation;
    protected HealthStatus healthStatus;
    protected String id;
    protected boolean onMission;

    // -------------------------- Constructor ---------------------------------------
    protected Agent(Location currentLocation, String id)
    {
        this.currentLocation = currentLocation;
        this.healthStatus = HealthStatus.HEALTHY; // Default health status
        this.id = id;
    }

    // --------------------------- Abstract Methods ----------------------------------
    public abstract boolean isUsable();  // health status, fatigue levels and isAvailable + recalls hasSkill
    // ------------------------ Concrete Methods ---------------------------------------
    public HealthStatus getHealth() {return healthStatus;}
    public Location getCurrentLocation() {return currentLocation;}
    public String getId() {return id;}
    public boolean canPerformMission(Skill skill) {
        return false; //if used on a human agent, this method will be called from the superclass
    }

    public boolean canPerformMission(EquipmentType equipmentType) {
        return false; //if used on a robotic unit, this method will be called from the superclass
    }
    public void setCurrentLocation(Location location) {this.currentLocation = location;}

    public void setOnMission(Mission mission)
    {
        if(mission != null)
            this.onMission = true;
        else
            this.onMission = false;
    }

    public void changeHealth(HealthStatus status) {healthStatus = status;}

    public void healthAnalysis(int fatigueLevel)
    {
        if(0 <= fatigueLevel && fatigueLevel <= 35){changeHealth(HealthStatus.HEALTHY);}
        else if(35 < fatigueLevel && fatigueLevel <= 70){changeHealth(HealthStatus.MODERATE);}
        else if(70 < fatigueLevel){changeHealth(HealthStatus.UNHEALTHY);}
        else
            System.out.println("Fatigue level can't be a negative value");
    }
    public boolean getIsOnMission(){
        return onMission;
    }
}