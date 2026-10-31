package Main;
import java.util.ArrayList;
import Agents.*;
import MarsColonies.MarsColony;
import Missions.*;
import Simulation.Simulation;
import enums.EnvironmentCondition;
import enums.EquipmentType;
import enums.MissionState;
import Locations.*;
import Equipments.*;

public class ScenarioExecuter {

    public static void scenario1() {
        MarsColony colony = new MarsColony();
        ColonyFacility habitat = new Habitat("Hab1");           // oxygen
        ColonyFacility storage = new StorageFacility("Store1"); // water
        ColonyFacility power   = new PowerStation("Power1");    // fuel, battery
        ColonyFacility lab1= new Laboratory("Lab1");
        Location mars = colony.getMarsSurface();
        colony.addLocations(mars);
        colony.addLocations(lab1);
        colony.addLocations(habitat);
        colony.addLocations(storage);
        colony.addLocations(power);
        
        //Create the required agents

        Scientist scientist = new Scientist("Salma","SCI01",lab1,10);
        ExplorationRover rover = new ExplorationRover(
            mars,"ROV01",
            100,
            new Equipment[]{new Equipment(EquipmentType.DRILL), new Equipment(EquipmentType.SAMPLE_COLLECTOR)});
    
        //Add agents to the colony
        colony.addAgent(scientist);
        colony.addAgent(rover);

        //Create the mission
        ExplorationMission mission = new ExplorationMission(mars,colony);
        // Create the simulation
        Simulation simulation = new Simulation();

        //Submit/register the mission
        simulation.addTimeOperation(mission);

        //Display state before execution
        System.out.println("Before execution:");
        System.out.println("----------------------------------------------------------------------------------------------------");
        mission.printMissionInfo();

        // 8. Advance time
        simulation.advanceTime(3);
        System.out.println("After execution:");
        System.out.println("----------------------------------------------------------------------------------------------------");
        mission.printMissionInfo();
    }

    public static void scenario2() {
    // Mission Rejection: unsuitable agent
    MarsColony colony = new MarsColony();
    ColonyFacility habitat = new Habitat("Hab1");           // oxygen
    ColonyFacility storage = new StorageFacility("Store1"); // water
    ColonyFacility power   = new PowerStation("Power1");    // fuel, battery
    Location mars = colony.getMarsSurface();
    colony.addLocations(mars);
    colony.addLocations(habitat);
    colony.addLocations(storage);
    colony.addLocations(power);

    // Only an unsuitable agent: an engineer cannot do VEHICLE_OPERATION
    Engineer engineer = new Engineer("Shery", "E01", habitat, 10);
    ExplorationRover rover = new ExplorationRover( //valid rover (the mission is supposed to fail because engineer doesnt have the required skill )
        mars,"ROV01",100,
         new Equipment[]{new Equipment(EquipmentType.DRILL), new Equipment(EquipmentType.SAMPLE_COLLECTOR)});
    colony.addAgent(engineer);

    // Exploration needs VEHICLE_OPERATION + a robot with DRILL and SAMPLE_COLLECTOR
    ExplorationMission mission = new ExplorationMission(mars, colony);


    Simulation simulation = new Simulation();
    simulation.addTimeOperation(mission);

    System.out.println("Before execution:");
    System.out.println("----------------------------------------------------------------------------------------------------");
    mission.printMissionInfo();

    simulation.advanceTime(3);

    System.out.println("After execution:");
    System.out.println("----------------------------------------------------------------------------------------------------");
    System.out.println("Mission cannot be assigned. Reason: " + mission.getFailureReason());
    System.out.println("Mission state: " + mission.getMissionState());
    System.out.println("Mission ever became active: "
            + (mission.getMissionState() == MissionState.ACTIVE
               || !mission.getConsumedResources().isEmpty()));
    mission.printMissionInfo();
}

    public static void scenario3() {
        // Resource Shortage
    }

    public static void scenario4() {
        // Emergency Handling
    }

    public static void scenario5() {
        // Environmental Effect
    }
}