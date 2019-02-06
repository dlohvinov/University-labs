package ua.lviv.iot.lab3;

import ua.lviv.iot.lab3.model.DefenceEquipment;
import ua.lviv.iot.lab3.model.EngeneeringEquipment;
import ua.lviv.iot.lab3.model.Equipment;
import ua.lviv.iot.lab3.model.OffenceEquipment;
import ua.lviv.iot.lab3.model.enums.EquipmentType;
import ua.lviv.iot.lab3.model.enums.WorkType;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class EquipmentManager {
    private LinkedList<Equipment> equipment = new LinkedList<>();
    private LinkedList<Equipment> sortedEquipment = new LinkedList<>();

public void setEquipment(final Equipment riffle, final Equipment machinegun, final Equipment pistol,
                    final Equipment flackJacket, final Equipment shoesScouting, final Equipment shoesEngeneering,
                    final Equipment binocular, final Equipment shovel, final Equipment knife) {
    equipment.add(riffle);
    equipment.add(machinegun);
    equipment.add(pistol);
    equipment.add(flackJacket);
    equipment.add(shoesScouting);
    equipment.add(shoesEngeneering);
    equipment.add(binocular);
    equipment.add(shovel);
    equipment.add(knife);
    }

    public LinkedList getByWork(final WorkType workType) {
        System.out.println("Get your set for " + workType + " task!");
        if (workType == WorkType.FIRING) {
            for (Equipment equipment : equipment) {
                if (equipment.getWorkType() == WorkType.FIRING) {
                    sortedEquipment.add(equipment);
                    //System.out.println("Get your " + equipment.equipmentType);
                }
            }
        }

        if (workType == WorkType.SCOUTING) {
            for (Equipment equipment : equipment) {
                if (equipment.getWorkType() == WorkType.SCOUTING) {
                    sortedEquipment.add(equipment);
                    //System.out.println("Get your " + equipment.equipmentType);
                }
            }
        }

        if (workType == WorkType.ENGENEERING) {
            for (Equipment equipment : equipment) {
                if (equipment.getWorkType() == WorkType.ENGENEERING) {
                    sortedEquipment.add(equipment);
                    //System.out.println("Get your " + equipment.equipmentType);
                }
            }
        }
        return sortedEquipment;
    }

    public void sortByWeight() {

        System.out.println("Equipment, sorted by weight: ");

        equipment.sort(new Comparator<Equipment>() {
            @Override
            public int compare(final Equipment equipment1, final Equipment equipment2) {
                return Double.compare(equipment1.getWeight(), equipment2.getWeight());
            }
        });

        for (Equipment equipment  : equipment) {
            System.out.println(equipment.getEquipmentType() + ", that weights: " + equipment.getWeight()
                    + "(in kgrams)");
        }
    }

    public void print(final LinkedList<Equipment> equipmentList) {
        for (Equipment equipment : equipmentList) {
            System.out.println(equipment);
        }
    }

    public LinkedList<Equipment> getEquipment() {
        return equipment;
    }

    public LinkedList<Equipment> getSortedEquipment() {
        return sortedEquipment;
    }

    public static void main(String[] args) {
        Equipment riffle = new OffenceEquipment(EquipmentType.RIFFLE, WorkType.SCOUTING, 7,
                10000, 10);
        Equipment machinegun = new OffenceEquipment(EquipmentType.MACHINEGUN, WorkType.FIRING, 4,
                1000, 30);
        Equipment pistol = new OffenceEquipment(EquipmentType.PISTOL, WorkType.FIRING, 0.5,
                500, 20);

        Equipment flakJacket = new DefenceEquipment(EquipmentType.FLAK_JACKET, WorkType.FIRING, 8,
                "Body", 48);
        Equipment shoesScouting = new DefenceEquipment(EquipmentType.SHOES, WorkType.SCOUTING, 1,
                "Foot", 43);
        Equipment shoesEngeneering = new DefenceEquipment(EquipmentType.SHOES, WorkType.ENGENEERING, 1.5,
                "Foot", 43);

        Equipment binoculars = new EngeneeringEquipment(EquipmentType.BINOCULARS, WorkType.SCOUTING, 0.2,
                "plastic", "green");
        Equipment shovel = new EngeneeringEquipment(EquipmentType.SHOVEL, WorkType.ENGENEERING, 0.2,
                "iron", "yellow");
        Equipment knife = new EngeneeringEquipment(EquipmentType.KNIFE, WorkType.ENGENEERING, 0.1,
                "steel", "grey");

        EquipmentManager equipmentManager = new EquipmentManager();
        equipmentManager.setEquipment(riffle, machinegun, pistol, flakJacket, shoesScouting, shoesEngeneering,
                binoculars, shovel, knife);

        String str;
        Scanner reader = new Scanner(System.in);
        do {
            System.out.println("1 -- print, 2 - scouting sort, 3 - firing sort, 3 - engeneering sort, "
                    +  "4 -- sort by weight, q - exit  ");

            str = reader.next();
            switch (str) {
                case "1": {
                    equipmentManager.print(equipmentManager.equipment);
                    break;
                }
                case "2": {
                    equipmentManager.getByWork(WorkType.SCOUTING);
                    equipmentManager.print(equipmentManager.sortedEquipment);
                    break;
                }
                case "3": {
                    equipmentManager.getByWork(WorkType.FIRING);
                    equipmentManager.print(equipmentManager.sortedEquipment);
                    break;
                }
                case "4": {
                    equipmentManager.getByWork(WorkType.ENGENEERING);
                    equipmentManager.print(equipmentManager.sortedEquipment);
                    break;
                }
                case "5": {
                    equipmentManager.sortByWeight();
                    break;
                }
                default: {
                    continue;
                }
            }
        } while (!str.equals("q"));
    }
}
