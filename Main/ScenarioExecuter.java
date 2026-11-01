package Main;
import java.util.ArrayList;
import Agents.*;
import MarsColonies.MarsColony;
import Missions.*;
import Resources.BatteryPower;
import Resources.Fuel;
import Resources.Resource;
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

    // an engineer cannot do VEHICLE_OPERATION
    Engineer engineer = new Engineer("Shery", "E01", habitat, 10);
    ExplorationRover rover = new ExplorationRover( //valid rover (the mission is supposed to fail because engineer doesnt have the required skill )
        mars,"ROV01",100,
         new Equipment[]{new Equipment(EquipmentType.DRILL), new Equipment(EquipmentType.SAMPLE_COLLECTOR)});
    colony.addAgent(engineer);

    // Exploration needs VEHICLE_OPERATION + a robot with DRILL and SAMPLE_COLLECTOR
    ExplorationMission mission = new ExplorationMission(mars, colony);


    runMission(mission);
}

    public static void scenario3() {
    // fuel is insufficient
    MarsColony colony = new MarsColony();
    ColonyFacility habitat = new Habitat("Hab1");           // oxygen
    ColonyFacility storage = new StorageFacility("Store1"); // water
    ColonyFacility power   = new PowerStation("Power1");    // fuel, battery
    ColonyFacility lab1    = new Laboratory("Lab1");
    Location mars = colony.getMarsSurface();

    //  only 80 available but exploration mission needs 100
    power.setResources(new Resource[]{
        new Fuel(80),
        new BatteryPower(100)
    });

    colony.addLocations(mars);
    colony.addLocations(lab1);
    colony.addLocations(habitat);
    colony.addLocations(storage);
    colony.addLocations(power);

    // Suitable agents so only reason for failure is resources
    Scientist scientist = new Scientist("Salma", "SCI01", lab1, 10);
    ExplorationRover rover = new ExplorationRover(
        mars, "ROV01", 100,
        new Equipment[]{new Equipment(EquipmentType.DRILL), new Equipment(EquipmentType.SAMPLE_COLLECTOR)});
    colony.addAgent(scientist);
    colony.addAgent(rover);

    ExplorationMission mission = new ExplorationMission(mars, colony);
    runMission(mission);
}

    public static void scenario4() {
        // Emergency Handling
    }

    public static void scenario5() {
        // dust storm blocks a surface mission
        MarsColony colony = new MarsColony();
        ColonyFacility habitat = new Habitat("Hab1");           // oxygen
        ColonyFacility storage = new StorageFacility("Store1"); // water
        ColonyFacility power   = new PowerStation("Power1");    // fuel, battery
        ColonyFacility lab1    = new Laboratory("Lab1");
        Location mars = colony.getMarsSurface();
        colony.addLocations(mars);
        colony.addLocations(lab1);
        colony.addLocations(habitat);
        colony.addLocations(storage);
        colony.addLocations(power);

        // Suitable agents and enough resources so only the environment can cause failure
        Scientist scientist = new Scientist("Salma", "SCI01", lab1, 10);
        ExplorationRover rover = new ExplorationRover(
            mars, "ROV01", 100,
            new Equipment[]{new Equipment(EquipmentType.DRILL), new Equipment(EquipmentType.SAMPLE_COLLECTOR)});
        colony.addAgent(scientist);
        colony.addAgent(rover);

        // introduce the environmental condition
        colony.setEnvironmentCondition(EnvironmentCondition.DUST_STORM);
        System.out.println("Environment condition: " + colony.getEnvironmentCondition());

        ExplorationMission mission = new ExplorationMission(mars, colony);
        runMission(mission);
    }
    public static void scenario6() {
        // Multi-facility consumption: resources are drawn from several facilities
        MarsColony colony = new MarsColony();
        ColonyFacility habitat = new Habitat("Hab1");
        ColonyFacility storage = new StorageFacility("Store1");
        ColonyFacility power1  = new PowerStation("Power1");
        ColonyFacility power2  = new PowerStation("Power2");
        ColonyFacility lab1    = new Laboratory("Lab1");
        Location mars = colony.getMarsSurface();

        power1.setResources(new Resource[]{ new Fuel(40), new BatteryPower(30) });
        power2.setResources(new Resource[]{ new Fuel(60), new BatteryPower(100) });

        colony.addLocations(mars);
        colony.addLocations(lab1);
        colony.addLocations(habitat);
        colony.addLocations(storage);
        colony.addLocations(power1);
        colony.addLocations(power2);

        Scientist scientist = new Scientist("Salma", "SCI01", lab1, 10);
        ExplorationRover rover = new ExplorationRover(mars, "ROV01", 100,
            new Equipment[]{new Equipment(EquipmentType.DRILL), new Equipment(EquipmentType.SAMPLE_COLLECTOR)});
        colony.addAgent(scientist);
        colony.addAgent(rover);

        runMission(new ExplorationMission(mars, colony));

        System.out.println("Power1 after mission:");
        power1.printResourcesInFacility();
        System.out.println("Power2 after mission:");
        power2.printResourcesInFacility();
        }
    public static void scenario7() {
        // Research mission in the laboratory
        MarsColony colony = new MarsColony();
        ColonyFacility habitat = new Habitat("Hab1");
        ColonyFacility storage = new StorageFacility("Store1");
        ColonyFacility lab1    = new Laboratory("Lab1");
        colony.addLocations(lab1);
        colony.addLocations(habitat);
        colony.addLocations(storage);

        Scientist scientist = new Scientist("Salma", "SCI01", habitat, 10);
        colony.addAgent(scientist);

        runMission(new ResearchMission(lab1, colony));
    }
    public static void scenario8() {
        //fix a damaged facility
        MarsColony colony = new MarsColony();
        ColonyFacility storage = new StorageFacility("Store1");
        ColonyFacility lab1    = new Laboratory("Lab1");
        colony.addLocations(storage);
        colony.addLocations(lab1);

        lab1.setIsDamaged(true);

        Engineer engineer = new Engineer("Karim", "ENG01", storage, 10);
        RepairRobot robot = new RepairRobot(storage, "REP01", 100,
            new Equipment[]{new Equipment(EquipmentType.TOOLKIT), new Equipment(EquipmentType.WELDING_TORCH)});
        colony.addAgent(engineer);
        colony.addAgent(robot);

        System.out.println("Lab1 damaged before mission: " + lab1.getIsDamaged());
        runMission(new RepairMission(lab1, colony, lab1));
        System.out.println("Lab1 damaged after mission: " + lab1.getIsDamaged());
}
    public static void scenario9() {
        // Rover cannot operate indoors mission rejected
        MarsColony colony = new MarsColony();
        ColonyFacility habitat = new Habitat("Hab1");
        ColonyFacility storage = new StorageFacility("Store1");
        ColonyFacility power   = new PowerStation("Power1");
        ColonyFacility lab1    = new Laboratory("Lab1");
        Location mars = colony.getMarsSurface();
        colony.addLocations(mars);
        colony.addLocations(lab1);
        colony.addLocations(habitat);
        colony.addLocations(storage);
        colony.addLocations(power);

        colony.addAgent(new Scientist("Salma", "SCI01", lab1, 10));
        colony.addAgent(new ExplorationRover(mars, "ROV01", 100,
            new Equipment[]{new Equipment(EquipmentType.DRILL), new Equipment(EquipmentType.SAMPLE_COLLECTOR)}));

        ExplorationMission mission = new ExplorationMission(lab1, colony); // indoor target
        runMission(mission);
        System.out.println("Reason: " + mission.getFailureReason());    
    }
    public static void scenario10() {
    // Only one mission at a time
        MarsColony colony = new MarsColony();
        ColonyFacility habitat= new Habitat("Hab1");
        ColonyFacility storage = new StorageFacility("Store1");
        ColonyFacility power = new PowerStation("Power1");
        ColonyFacility lab1 = new Laboratory("Lab1");
        Location mars = colony.getMarsSurface();
        colony.addLocations(mars);
        colony.addLocations(lab1);
        colony.addLocations(habitat);
        colony.addLocations(storage);
        colony.addLocations(power);

        colony.addAgent(new Scientist("Salma", "SCI01", lab1, 10));
        colony.addAgent(new ExplorationRover(mars, "ROV01", 100,
            new Equipment[]{new Equipment(EquipmentType.DRILL), new Equipment(EquipmentType.SAMPLE_COLLECTOR)}));
        colony.addAgent(new Scientist("Sara", "SCI02", lab1, 10));

        ExplorationMission missionA = new ExplorationMission(mars, colony);
        ResearchMission missionB = new ResearchMission(lab1, colony);

        Simulation simulation = new Simulation();
        simulation.addTimeOperation(missionA);   // A must be registered first
        simulation.addTimeOperation(missionB);

        //Pass 1: both missions try to start
        simulation.advanceTime(1);
        System.out.println("***After pass 1***");
        System.out.println("Mission A (" + missionA.getMissionType() + "): " + missionA.getMissionState());
        System.out.println("Mission B (" + missionB.getMissionType() + "): " + missionB.getMissionState());
        System.out.println("Mission B reason: " + missionB.getFailureReason());
        System.out.println("Colony active mission is A: " + (colony.getActiveMission()==missionA));

        // Let A finish
        simulation.advanceTime(2);
        System.out.println("***After A finished***");
        System.out.println("Mission A: " + missionA.getMissionState());
        System.out.println("Colony active mission: " + colony.getActiveMission());

        // Now a new research mission can run
        ResearchMission missionB2 = new ResearchMission(lab1, colony);
        simulation.addTimeOperation(missionB2);
        simulation.advanceTime(3);
        System.out.println("=== Resubmitted research mission ===");
        System.out.println("Mission B2: " + missionB2.getMissionState());
}
    private static void runMission(Mission mission) {
        Simulation simulation = new Simulation();
        simulation.addTimeOperation(mission);

        System.out.println("Before execution:");
        System.out.println("----------------------------------------------------------------------------------------------------");
        mission.printMissionInfo();

        simulation.advanceTime(3);

        System.out.println("After execution:");
        System.out.println("----------------------------------------------------------------------------------------------------");
        mission.printMissionInfo();
    }
}