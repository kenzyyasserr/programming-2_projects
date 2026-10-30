package Agents;

import Equipment.Equipment;
import Locations.Location;
import enums.EmergencyType;
import enums.EmergencyStatus;
import Emergencies.*;

public class RepairRobot extends RoboticUnits implements EmergencyResponder {

    public RepairRobot(Location currentLocation, String id, double BatteryLevel, boolean onMission, Equipment[] equipment) {
        super(currentLocation, id, BatteryLevel, onMission, equipment);
    }

    public void repair(Equipment equipment) {
        equipment.repair();
        this.changeBatteryLevel(-15); // Decrease battery level by 15 units
        this.onMission = true; // Set the robot on a mission
    }

    @Override
    public boolean canOperateIn(Location loc) {
        return loc.isIndoorsFacility();
    }

    @Override
    public boolean isAvailable() {
        return this.isUsable() && !this.onMission;
    }


    @Override
    public boolean canHandleEmergency(EmergencyType type) {
        return type != EmergencyType.MEDICAL && type != EmergencyType.FIRE;
    }


    @Override
    public void handleEmergency(Emergency incident) {
        if (this.canHandleEmergency(incident.getType()) && this.isAvailable()) {
            System.out.println("RepairRobot " + this.getId() + " is handling the emergency: " + incident.getType());
            incident.status = EmergencyStatus.RESOLVED; // Mark the emergency as resolved
        } else {
            System.out.println("RepairRobot " + this.getId() + " cannot handle the emergency: " + incident.getType());
        }

    }
}
