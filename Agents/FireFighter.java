package Agents;
import enums.HealthStatus;
import enums.MissionType;
import enums.Skill;
import Equipments.Equipment;
import Locations.ColonyFacility;

public class FireFighter extends HumanPersonnel
{
    // ------------------------- Constructor -------------------------
    public FireFighter(){
        super(name, skills, fatigueLevel);
    }

    // ---------------- Methods Implementation -----------------
    @Override
    public boolean hasSkill(Skill skill)
    {
        boolean skilled;
        switch (skill) {
            case FIREFIGHTING:
                System.out.println("Fire fighter is skilled for this mission");
                skilled = true;
                break;

            default:
                System.out.println("Fire fighter is not skilled enough for this mission");
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
                        System.out.println("Fire fighter is usable for mission as they are skilled, healthy and available");
                        usable = true;
                    }
                    else{
                        System.out.println("Fire fighter cannot perform mission as they are on another mission");
                        usable = false;
                    }
                    break;

                default:
                    System.out.println("Fire fighter cannot perform mission as they are unhealthy");
                    usable = false;
            }
        }
        else{
            System.out.println("Fire fighter is unusable as they are not skilled enough");
            usable = false;
        }
        return usable;
    }
}