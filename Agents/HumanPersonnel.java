package Agents;
import Locations.ColonyFacility;
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