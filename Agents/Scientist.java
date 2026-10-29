package Agents;
import Locations.ColonyFacility;
import enums.HealthStatus;
import enums.MissionType;
import enums.Skill;

public class Scientist extends HumanPersonnel
{
    private static final Skill[] SCIENTIST_SKILLS = {Skill.BIOLOGY, Skill.CHEMISTRY,Skill.GEOLOGY};
    // -------------------------- Constructor ----------------------
    public Scientist(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel,SCIENTIST_SKILLS);
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
                    if(!onMission){
                        System.out.println("Scientist is usable for mission as they are skilled, healthy and available");
                        usable = true;
                    }
                    else{
                        System.out.println("Scientist cannot perform mission as they are on another mission");
                        usable = false;
                    }
                    break;

                default:
                    System.out.println("Scientist cannot perform mission as they are unhealthy");
                    usable = false;
            }
        }
        else{
            System.out.println("Scientist is unusable as they are not skilled enough");
            usable = false;
        }
        return usable;
    }

    void research()
    {
        fatigueLevel++;
        healthAnalysis(fatigueLevel);
        setOnMission(MissionType.RESEARCH);
    }
}