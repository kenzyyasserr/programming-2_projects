package Agents;
import enums.HealthStatus;
import enums.MissionType;
import enums.Skill;
import Equipments.Equipment;
import Locations.ColonyFacility;

public class MedicalOfficer extends HumanPersonnel
{
    private static final Skill[] MEDICAL_OFFICER_SKILLS = {Skill.SURGERY, Skill.FIRST_AID};
    // ------------------------- Constructor -------------------------
    public MedicalOfficer(String name, String id,ColonyFacility currentLocation,int fatigueLevel){
        super(name,id,currentLocation, fatigueLevel, MEDICAL_OFFICER_SKILLS);
    }

    // ---------------- Methods Implementation -----------------
    @Override
    public boolean hasSkill(Skill skill)
    {
        boolean skilled;
        switch (skill) {
            case SURGERY:
                System.out.println("Medical Officer is skilled for this mission");
                skilled = true;
                break;

            case FIRST_AID:
                System.out.println("Medical Officer is skilled for this mission");
                skilled = true;
                break;
            default:
                System.out.println("Medical Officer is not skilled enough for this mission");
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
                        System.out.println("Medical Officer is usable for mission as they are skilled, healthy and available");
                        usable = true;
                    }
                    else{
                        System.out.println("Medical Officer cannot perform mission as they are on another mission");
                        usable = false;
                    }
                    break;

                default:
                    System.out.println("Medical Officer cannot perform mission as they are unhealthy");
                    usable = false;
            }
        }
        else{
            System.out.println("Medical Officer is unusable as they are not skilled enough");
            usable = false;
        }
        return usable;
    }

    
}