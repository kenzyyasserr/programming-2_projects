package Agents;
import Locations.ColonyFacility;
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

    public boolean isUsable()
    {
        HealthStatus status = getHealth();
        return (status == HealthStatus.HEALTHY ||status == HealthStatus.MODERATE)&& !onMission;
    }

    @Override
    public boolean canPerformMission(Skill requiredSkill)
    {
        if (!isUsable())
        {
            return false;
        }
        if (!hasSkill(requiredSkill))
        {
            return false;
        }
        return true;
    }
    public boolean hasSkill(Skill skill) {
        if (skills == null) return false;
        for (Skill s : skills) {
            if (s == skill) return true;
        }
        return false;
    }
    
    // ---------------------- Constructor ------------------------------
    public HumanPersonnel(String name, String id,ColonyFacility currentLocation,int fatigueLevel,Skill[] skills)
    {
        super(currentLocation,id);
        this.name = name;
        this.skills = skills;
        this.fatigueLevel = fatigueLevel;
    }
}
