package Agents;
import enums.Skill;

abstract class HumanPersonnel extends Agents
{
    // --------------------------- Attributes -------------------------
    private String name;
    private Skill[] skills;
    private int fatigueLevel;

    // ------------------------------ Methods -------------------------------
    public Skill getSkills() {return skills;}
    public String getName() {return name;}
    public int getFatigueLevel() {return fatigueLevel;}

    public void setName(String name){this.name = name;}

    abstract boolean hasSkill(Skill skill);
}