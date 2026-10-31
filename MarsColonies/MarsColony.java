package MarsColonies;
import Agents.*;
import Emergencies.*;
import Locations.*;
import Missions.*;
import enums.*;
import java.util.ArrayList;


public class MarsColony
{
    // ------------------------ Attributes ---------------------------------
    private Emergency currentEmergency; //no emergency if null
    private EnvironmentCondition environmentCondition;//normal by default
    private ArrayList<ColonyFacility> facilities; 
    private MarsSurface marsSurface;
    private ArrayList<Location> locations; //arraylist which includes facilities and the martian surface;
    private Mission activeMission;
    private ArrayList<Agent> agents;
    private Mission reservedMission;   // mission that holds the colony (ASSIGNED or ACTIVE)
    public MarsColony() {
        facilities = new ArrayList<>();
        locations = new ArrayList<>();
        marsSurface = new MarsSurface();
        agents = new ArrayList<>();
        environmentCondition = EnvironmentCondition.NORMAL;
    }

    // ---------------------------------- Methods ----------------------------------
    public int getTotalQuantityOfResources(ResourceType resourceType){ //total resources across all facilities
        int total = 0;
        for(ColonyFacility facility: facilities){
            total+= facility.getResourceQuantity(resourceType,environmentCondition);
        }
        return total;
    }

    public Mission getActiveMission() {return activeMission;}
    public MarsSurface getMarsSurface(){return marsSurface;}
    public EnvironmentCondition getEnvironmentCondition() {return environmentCondition;}
    public ColonyFacility[] getFacilities() {return facilities.toArray(new ColonyFacility[0]);}
    public Location[] getLocations() {return locations.toArray(new Location[0]);}
    public Agent[] getColonyAgents() {return agents.toArray(new Agent[0]);}

    public boolean setActiveMission(Mission mission)
    {
        if (mission == null || activeMission == null)
        {
            activeMission = mission;
            return true;
        }
        return false;
    }

    public boolean reserve(Mission mission) {
        if (reservedMission == null) {
            reservedMission = mission;
            return true;
        }
        return false;
    }

    public void release(Mission mission) {
        if (reservedMission == mission) {
            reservedMission = null;
        }
    }

    public Mission getReservedMission() {
        return reservedMission;
    }
    public void addAgent(Agent agent) {
    if (agent != null && !agents.contains(agent)) {
        agents.add(agent);
    }
    }

    public void addLocations(Location location)
    {
        if (location != null && !locations.contains(location))
        {
            locations.add(location);
            if (location instanceof ColonyFacility)
            {
                ColonyFacility facility = (ColonyFacility) location;
                facilities.add(facility);
            }
        }
    }
    public void setEnvironmentCondition(EnvironmentCondition condition) {
    if (condition != null) {
        this.environmentCondition = condition;
    }
}
}