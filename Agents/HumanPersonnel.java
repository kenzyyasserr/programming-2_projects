package Agents;
import Locations.ColonyFacility;
import enums.EquipmentType;
import enums.HealthStatus;
import enums.Skill;

public abstract class HumanPersonnel extends Agent
{
    // --------------------------- Attributes -------------------------
    protected String name;
    protected Skill[] skills;
    protected int fatigueLevel;

    // ------------------------------ Concrete Methods -------------------------------
    public Skill[] getSkills() {return skills;}
    public String getName() {return name;}
    public int getFatigueLevel() {return fatigueLevel;}

    public void setName(String name){this.name = name;}

    public boolean isUsable(){
        HealthStatus status = getHealth();
        if((status == HealthStatus.HEALTHY ||status == HealthStatus.MODERATE)&& !onMission)
        {
            System.out.println("This personnel's health allows him to perform the mission");
            return true;
        }
        return false;
    }
    
    @Override
    public boolean canPerformMission(Skill requiredSkill,EquipmentType equipmentType) {
    if (!isUsable()) {
        System.out.println("Personnel is unusable.");
        return false;
    }
        if (!hasSkill(requiredSkill)) {
            System.out.println("Personnel lacks a required skill.");
            return false;
        }

    System.out.println("Personnel can perform the mission.");
    return true;
}
    // ---------------------- Abstract Methods -----------------------------
    public abstract boolean hasSkill(Skill skill);
    // ---------------------- Constructor ------------------------------
    public HumanPersonnel(String name, String id,ColonyFacility currentLocation,int fatigueLevel,Skill[] skills)
    {
        super(currentLocation,id);
        this.name = name;
        this.skills = skills;
        this.fatigueLevel = fatigueLevel;
    }
}
