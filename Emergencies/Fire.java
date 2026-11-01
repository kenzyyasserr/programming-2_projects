package Emergencies;
import Locations.Laboratory;
import enums.*;

public class Fire extends Emergency{

    public Fire(ResponderType[] requiredResponders, Skill [] requiredSkills ,ResourceType[] requiredResources, 
                 int [] requiredAmountsOfResources, Location EmergencyLocation){
                    super(ResponderType[] requiredResponders, Skill [] requiredSkills ,ResourceType[] requiredResources, 
                            int [] requiredAmountsOfResources, Location EmergencyLocation, LocationType.LABORATORY, EmergencyType.FIRE);
                    
                 }

}