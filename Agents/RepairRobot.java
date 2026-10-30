package Agents;

import Emergencies.*;
import Equipments.Equipment;
import Locations.Location;
import enums.EmergencyStatus;
import enums.EmergencyType;

public class RepairRobot extends RoboticUnits implements EmergencyResponder {

    public RepairRobot(Location currentLocation, String id, double BatteryLevel, Equipment[] equipment) {
        super(currentLocation, id, BatteryLevel, equipment);
    }

    public void repair(Equipment equipment) {
        equipment.repair();
        this.changeBatteryLevel(-15); // Decrease battery level by 15 units
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
        if (this.canHandleEmergency(incident.getEmergencyType()) && this.isAvailable()) {
            System.out.println("RepairRobot " + this.getId() + " is handling the emergency: " + incident.getEmergencyType());
            incident.setEmergencyStatus(EmergencyStatus.RESOLVED); // Mark the emergency as resolved
        } else {
            System.out.println("RepairRobot " + this.getId() + " cannot handle the emergency: " + incident.getEmergencyType());
        }

    }
}
