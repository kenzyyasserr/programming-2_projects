package Agents;
import enums.HealthStatus;
import enums.Skill;

public class Scientist extends HumanPersonnel
{
    // -------------------------- Constructor ----------------------
    public Scientist(){
        super(name, skills, fatigueLevel);
    }

    // ------------------ Methods Implementation ------------------------
    @Override
    public boolean hasSkill(Skill skill)
    {
        boolean skilled;
        switch (skill) {
            case BIOLOGY:
                System.out.println("Scientist is skilled for this mission");
                skilled = true;
                break;

            case CHEMISTRY:
                System.out.println("Scientist is skilled for this mission");
                skilled = true;
                break;

            case GEOLOGY:
                System.out.println("Scientist is skilled for this mission");
                skilled = true;
                break;
            default:
                System.out.println("Scientist is not skilled enough for this mission");
                skilled = false;
                break;
        }
        return skilled;
    }

    @Override
    public boolean isUsable(Skill skill)
    {
        HealthStatus status = getHealth();
        boolean usable;
        if(hasSkill(skill))
        {
            switch (status) {
                case HEALTHY:
                case MODERATE:
                    
                    break;
                default:
                    throw new AssertionError();
            }
        }
        else{
            System.out.println("Scientist is unusable as they are not skilled enough");
            usable = false;
        }
        return usable;
    }
}