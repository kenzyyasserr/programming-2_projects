package Agents;
import Locations.ColonyFacility;
import Locations.Location;
import enums.HealthStatus;
import enums.MissionType;
import enums.Skill;


public abstract class Agent
{
    // --------------------------- Attributes ----------------------------------
    protected ColonyFacility currentLocation;
    protected HealthStatus healthStatus;
    protected String id;
    protected boolean onMission;

    // -------------------------- Constructor ---------------------------------------
    protected Agent(ColonyFacility currentLocation, String id)
    {
        this.currentLocation = currentLocation;
        this.healthStatus = HealthStatus.HEALTHY; // Default health status
        this.id = id;
    }

    // --------------------------- Abstract Methods ----------------------------------
    public abstract boolean isUsable(Skill skill);  // health status, fatigue levels and isAvailable + recalls hasSkill

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

    public boolean canPerformMission(Skill skill)  // checks isUsable
    {
        if(isUsable(skill)){
            System.out.println("This personnel can perform mission as they are usable");
            return true;
        }
        else{
            System.out.println("This personnel cannot perform mission as they are unusable");
            return false;
        }
    }

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