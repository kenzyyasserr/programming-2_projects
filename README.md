# programming-2_projects

## 1. System Description and Main Components
The system is designed to coordinate personnel, robotic units, facilities, resources, missions, environmental conditions and emergencies where:
- Human Personnel: Includes Scientists, Engineers and Medical Officers each one has their ID, name, health status, location, skills, fatigue level, and availability
- Robotic Units: Includes Exploration Rovers, Repair Robots, and Drones tracking battery levels, damage status, location and availability
- Colony Facilities: Includes Habitats, Laboratories, Storage Facilities, Power Stations, and Communication Stations, tracking capacity, operational status, resources and equipment
- Resources: Tracks colony-wide quantities of oxygen, water, food, fuel, battery power, medical supplies and spare parts
- Missions: Supports Exploration, Repair, Research and Rescue missions
## 2. Project Directory Structure
```
lab5
├── Agents/
│   ├── Agent.java
│   ├── Drone.java
│   ├── Engineer.java
│   ├── ExplorationRover.java
│   ├── FireFighter.java
│   ├── HumanPersonnel.java
│   ├── MedicalOfficer.java
│   ├── RepairAbility.java
│   ├── RepairRobot.java
│   ├── RoboticUnits.java
│   └── Scientist.java
├── Equipments/
│   ├── CanBeFixed.java
│   └── Equipment.java
├── Locations/
│   ├── ColonyFacility.java
│   ├── CommunicationStation.java
│   ├── Habitat.java
│   ├── Laboratory.java
│   ├── Location.java
│   ├── MarsSurface.java
│   ├── MedicalBay.java
│   ├── PowerStation.java
│   └── StorageFacility.java
├── Main/
│   ├── Main.java
│   └── ScenarioExecuter.java
├── MarsColonies/
│   └── MarsColony.java
├── Missions/
│   ├── ExplorationMission.java
│   ├── Mission.java
│   ├── RepairMission.java
│   ├── RescueMission.java
│   └── ResearchMission.java
├── Resources/
│   ├── BatteryPower.java
│   ├── Food.java
│   ├── Fuel.java
│   ├── LabSupplies.java
│   ├── MedicalSupplies.java
│   ├── Oxygen.java
│   ├── Resource.java
│   ├── ResourceUsage.java
│   ├── SpareParts.java
│   └── Water.java
├── Simulation/
│   └── Simulation.java
├── TimeConcept/
│   └── TimeOperation.java
└── enums/
    ├── AgentType.java
    ├── EnvironmentCondition.java
    ├── EquipmentType.java
    ├── HealthStatus.java
    ├── LocationType.java
    ├── MissionState.java
    ├── MissionType.java
    ├── ResponderType.java
    ├── ResourceType.java
    └── Skill.java
```
## 3. Design Decisions and Object Relationships
The implementation uses the 4 fundamental UML relationships:

- Association: Dynamic interactions without lifecycle dependency (Engineer working on a Mission)
- Aggregation: Whole-part relationships where parts exist independently of the whole (MarsColony containing Agent objects)
- Composition: Strong ownership where the part's lifecycle is tied to the whole (Habitat owning its internal Facility equipment)
- Inheritance: "Is-a" relationships enabling polymorphism (engineer and Scientist extending HumanPersonnel + Agent)
## 4. Resource Allocation Rules
Resources are stored in individual facilities but managed and evaluated at the colony level:

- Colony-Wide Totals: Total available resource quantity is the sum across all currently operational facilities. Destroyed or unavailable facilities are excluded
- Multiple Sources: Operations may draw required resources from more than one facility simultaneously
- All-or-Nothing Consumption: If the full required quantity cannot be supplied, no resources are consumed and the operation fails with a clear reason
- Allocation Policy: Resources are allocated sequentially across available facilities starting from Storage Facilities followed by Habitats and operational stations until requirements are met
## 5. Mission States and Lifecycle Rules
The colony supports exactly one active mission at a time. Missions progress through specific states: ASSIGNED, IN_PROGRESS, COMPLETED, and FAILED.

If another mission is active or capabilities/resources are insufficient, a new submission immediately becomes FAILED with a clear reason.
Assigned agents become available again when the mission ends (completes or fails). Failed missions are never retried automatically.
## 6. Environmental Effects and Emergency Handling
Environmental conditions directly affect operations: dust storms prevent surface exploration and cause rover missions to fail; communication disruptions block remote drones and communication-dependent operations. Emergencies operate independently of the one-active-mission limit, requiring available responders evaluated via capability checks rather than concrete types.

Oxygen Leak: Deducts oxygen from the affected habitat; repaired leaks cost the initial loss, while unresolved leaks cost twice the initial deduction.
Fire: Disables the laboratory, damages equipment, increases oxygen consumption, and requires firefighter and medical response.
Rover Breakdown: Marks rover as damaged, halts surface exploration, and creates a repair operation requirement.
Medical Emergency: Requires medical officers and supplies; successful treatment increases human health status.
## 7. Instructions for Compiling, Running, and Testing
run the following commands
```
javac -d bin $(find . -name "*.java")
java -cp bin Main.Main
```