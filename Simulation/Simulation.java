package Simulation;
import TimeConcept.*;
import MarsColonies.MarsColony;
import Missions.*;
import java.util.ArrayList;
import java.util.Arrays;
import Agents.*;
public class Simulation {

    private ArrayList<TimeOperation> timeOperations;

    public Simulation() {
        timeOperations = new ArrayList<>();
    }

    public void addTimeOperation(TimeOperation operation) {
        if (operation != null && !timeOperations.contains(operation)) {
            timeOperations.add(operation);
        }
    }

    public void advanceTime(int units) {
        if (units < 0) {
            return;
        }
        for (int i = 0; i < units; i++) {
            for (TimeOperation operation : timeOperations) {
                operation.timePass();
            }
        }
    }

}