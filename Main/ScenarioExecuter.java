package Main;
import java.util.ArrayList;
import Agents.*;
import Agents.ExplorationRover;
import Agents.Scientist;
import Locations.ColonyFacility;
import Locations.Laboratory;
import Locations.MarsSurface;
import MarsColonies.MarsColony;
import Missions.ExplorationMission;
import Missions.Mission;
import Simulation.Simulation;
import enums.EnvironmentCondition;
import Locations.*;

public class ScenarioExecuter {

    public static void scenario1() {
        MarsColony colony = new MarsColony();

        ColonyFacility lab1= new Laboratory("Lab1");
        Location mars = colony.getMarsSurface();
        //Create the required agents

        Scientist scientist = new Scientist("Salma","SCI01",lab1,10);
        ExplorationRover rover = new ExplorationRover(
            mars,"ROV01",
            100,
            new Equipment[]{new Equipment(EquipmentType.)});

        // 3. Add agents and equipment to the colony
        colony.addAgent(scientist);
        colony.addAgent(rover);

        // 4. Create the mission
        ExplorationMission mission = new ExplorationMission(...);

        // 5. Create the simulation
        Simulation simulation = new Simulation();

        // 6. Submit/register the mission
        simulation.addTimeOperation(mission);

        // 7. Display state before execution
        System.out.println("Before execution:");

        // 8. Advance time
        simulation.advanceTime(3);

        // 9. Display state after execution
        System.out.println("After execution:");
    }

    public static void scenario2() {
        // Mission Rejection
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