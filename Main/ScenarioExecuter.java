package Main;
import Agents.ExplorationRover;
import Agents.Scientist;
import MarsColonies.MarsColony;
import Missions.ExplorationMission;
import Simulation.Simulation;

public class ScenarioExecuter {

    public static void scenario1() {
        // Successful Exploration
        // 1. Create the colony
        MarsColony colony = new MarsColony(...);

        // 2. Create the required agents
        Scientist scientist = new Scientist(...);
        ExplorationRover rover = new ExplorationRover(...);

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