package MarsColonies;
import Emergencies.*;
import enums.*;
import java.util.ArrayList;
import java.util.Arrays;
import Locations.*;
import Resources.*;
import Missions.*;

public class MarsColony{
    private Emergency currentEmergency; //no emergency if null
    private EnvironmentCondition environmentCondition = EnvironmentCondition.NORMAL; //normal by default
    private ArrayList<ColonyFacility> facilities; 
    private MarsSurface marsSurface;
    private ArrayList<Location> locations; //arraylist which includes facilities and the martian surface;
    private Mission activeMission;
    private EnvironmentCondition environment;
    public MarsColony() {
        facilities = new ArrayList<>();
        locations = new ArrayList<>();
        marsSurface = new MarsSurface();
        environment = EnvironmentCondition.NORMAL;
    }
    public int getTotalQuantityOfResources(ResourceType resourceType){ //total resources across all facilities
        int total = 0;
        for(ColonyFacility facility: facilities){
            total+= facility.getResourceQuantity(resourceType,environment);
        }
        return total;
    }
    public EnvironmentCondition getEnvironmentCondition(){
        return environment;
    }
    public ColonyFacility[] getFacilities(){
        return facilities.toArray(new ColonyFacility[0]);
    }

}