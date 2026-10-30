package Agents;
import Locations.ColonyFacility;
import Locations.Location;
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
    public abstract boolean canPerformMission(Skill skill,EquipmentType equipmentType);

    // ------------------------ Concrete Methods ---------------------------------------
    public HealthStatus getHealth() {return healthStatus;}
    public Location getCurrentLocation() {return currentLocation;}
    public String getId() {return id;}

    public void setCurrentLocation(ColonyFacility location) {this.currentLocation = location;}

    public void setOnMission(MissionType mission)
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