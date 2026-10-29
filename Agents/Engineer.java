package Agents;
import enums.HealthStatus;
import enums.MissionType;
import enums.Skill;
import Equipments.Equipment;
import Locations.ColonyFacility;

public class Engineer extends HumanPersonnel
{
    private static final Skill[] ENG_SKILLS = {Skill.ELECTRONICS, Skill.WELDING,Skill.MECHANICS};
    // ------------------------- Constructor -------------------------
    public Engineer(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel, ENG_SKILLS);
    }

    // ---------------- Methods Implementation -----------------
    @Override
    public boolean hasSkill(Skill skill)
    {
        boolean skilled;
        switch (skill) {
            case ELECTRONICS:
                System.out.println("Engineer is skilled for this mission");
                skilled = true;
                break;

            case WELDING:
                System.out.println("Engineer is skilled for this mission");
                skilled = true;
                break;

            case MECHANICS:
                System.out.println("Engineer is skilled for this mission");
                skilled = true;
                break;
            default:
                System.out.println("Engineer is not skilled enough for this mission");
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
                        System.out.println("Engineer is usable for mission as they are skilled, healthy and available");
                        usable = true;
                    }
                    else{
                        System.out.println("Engineer cannot perform mission as they are on another mission");
                        usable = false;
                    }
                    break;

                default:
                    System.out.println("Engineer cannot perform mission as they are unhealthy");
                    usable = false;
            }
        }
        else{
            System.out.println("Engineer is unusable as they are not skilled enough");
            usable = false;
        }
        return usable;
    }

    void repairEquipment(Equipment equipment)
    {
        fatigueLevel++;
        healthAnalysis(fatigueLevel);
        setOnMission(MissionType.REPAIR);


    }

    void repairFacility(ColonyFacility facility)
    {
        fatigueLevel++;
        healthAnalysis(fatigueLevel);
        setOnMission(MissionType.REPAIR);


    }
}