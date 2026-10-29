# programming-2_projects
```
Agent (abstract)                            isUsable(), canPerform(Mission)

 ├── HumanPersonnel (abstract)              skills, health, fatigueLevel, currentLocation, id, onMission, changeHealth, canPerformMission, healthAnalysis
 │     ├── Scientist                        canPerform(), canHandleEmergency(), research()
 │     ├── Engineer                         canPerform(), canHandleEmergency(), repairEquipment(Equipment equipment), repairFacility(ColonyFacility facility), implements EmergencyResponder
 │     └── MedicalOfficer                   canPerform(), canHandleEmergency(), implements EmergencyResponder
 |     |___ FireFighter                     canPerform(), canHandleEmergency(), implements EmergencyResponder
 └── RoboticUnit (abstract)                 battery, damage, location
       ├── ExplorationRover                 canPerform(), canOperateIn()
       ├── RepairRobot                      canPerform(), canHandleEmegency(), canOperateIn(), implements EmergencyResponder
       └── Drone                            canPerform(), canOperateIn()

Facility (abstract)                         location, capacity, status, resources, implements location 
 ├── Habitat
 ├── Laboratory
 ├── StorageFacility
 ├── PowerStation
 └── CommunicationStation
 |___ MedicalBay

Mission (abstract)                          status, duration, elapsed, tick(), fail(reason)
 ├── ExplorationMission
 ├── RepairMission
 ├── ResearchMission
 └── RescueMission

Emergency (abstract)                        type, status, affected location
 ├── OxygenLeak
 ├── Fire
 ├── RoverBreakdown
 └── MedicalEmergency

MarsColony                                  the coordinator
 ├── has many Agents (aggregation)          addHumanPersonnel(), addRoboticUnit()
 ├── has many Facilities (aggregation)      addFacility()
 ├── has 0..1 active Mission                submitMission(), advanceTime()
 ├── has an Environment condition           setEnvironment()
 └── has an Emergency log                   triggerEmergency(), findEmergencyResponders()
```
Facility

 └── owns its parts (composition)           e.g. LifeSupportSystem inside Habitat
 
 └── holds Resources                        resource quantities live here
