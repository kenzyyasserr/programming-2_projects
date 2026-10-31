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
    private EnvironmentCondition environmentCondition;//normal by default
    private ArrayList<ColonyFacility> facilities; 
    private MarsSurface marsSurface;
    private ArrayList<Location> locations; //arraylist which includes facilities and the martian surface;
    private Mission activeMission;
    private ArrayList<Agent> agents;
    public MarsColony() {
        facilities = new ArrayList<>();
        locations = new ArrayList<>();
        marsSurface = new MarsSurface();
        agents = new ArrayList<>();
        environmentCondition = EnvironmentCondition.NORMAL;
    }
    public int getTotalQuantityOfResources(ResourceType resourceType){ //total resources across all facilities
        int total = 0;
        for(ColonyFacility facility: facilities){
            total+= facility.getResourceQuantity(resourceType,environmentCondition);
        }
        return total;
    }
    public Mission getActiveMission(){
        return activeMission;
    }
    public boolean setActiveMission(Mission mission) {
        if (mission == null || activeMission == null) {
            activeMission = mission;
            return true;
        }
        return false;
    }
    public MarsSurface getMarsSurface(){
        return marsSurface;
    }
    public EnvironmentCondition getEnvironmentCondition(){
        return environmentCondition;
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
    public void addAgent(Agent agent) {
    if (agent != null && !agents.contains(agent)) {
        agents.add(agent);
    }
    }
    private boolean canAddAgent(ColonyFacility facility) {
    int count = 0;

    for (Agent agent : agents) {
        if (agent.getCurrentLocation() == facility) {
            count++;
        }
    }

    return count < facility.getCapacity();
}

    public void addLocations(Location location) {
        if (location != null && !locations.contains(location)) {
            locations.add(location);

            if (location instanceof ColonyFacility) {
                ColonyFacility facility = (ColonyFacility) location;
                facilities.add(facility);
            }
        }
    }
}