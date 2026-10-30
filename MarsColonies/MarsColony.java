package MarsColonies;
import Emergencies.*;
import enums.*;
import java.util.ArrayList;
import java.util.Arrays;
import Locations.*;
import Resources.*;
import Missions.*;
import Agents.*;

public class MarsColony{
    private Emergency currentEmergency; //no emergency if null
    private EnvironmentCondition environmentCondition = EnvironmentCondition.NORMAL; //normal by default
    private ArrayList<ColonyFacility> facilities; 
    private MarsSurface marsSurface;
    private ArrayList<Location> locations; //arraylist which includes facilities and the martian surface;
    private Mission activeMission;
    private EnvironmentCondition environment;
    private ArrayList<Agent> agents;
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
    public Mission getActiveMission(){
        return activeMission;
    }
    public boolean setActiveMission(Mission mission){
        if(activeMission==null){
            activeMission = mission;
            return true;
        }
        if(mission == null){
            activeMission = mission;
            return true;
        }
        return false;
    }
    public EnvironmentCondition getEnvironmentCondition(){
        return environment;
    }
    public ColonyFacility[] getFacilities(){
        return facilities.toArray(new ColonyFacility[0]);
    }
    public Location[] getLocations(){
        return locations.toArray(new Location[0]);
    }
    public Agent[] getColonyAgents(){
        return agents.toArray(new Agent[0]);
    }

}