package Agents;
import enums.Skill;

abstract class HumanPersonnel extends Agents
{
    // --------------------------- Attributes -------------------------
    private String name;
    private Skill[] skills;

    // ------------------------------ Methods -------------------------------
    public Skill[] getSkills() {return skills;}
    public String getName() {return name;}
    
}