package ua.lviv.iot.lab3;

import ua.lviv.iot.lab3.model.Equipment;
import ua.lviv.iot.lab3.model.enums.WorkType;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class EquipmentManager {
    private List<Equipment> equipmentList = new LinkedList<>();

    public void addEquipment(final Equipment equipment) {
        this.equipmentList.add(equipment);
    }

    public List<Equipment> getByWork(final WorkType workType) {

        List<Equipment> result = new LinkedList<>();
        for (Equipment equipment : equipmentList) {
            if (equipment.getWorkType() == workType) {
                result.add(equipment);
            }
        }
        return result;
    }

    public void sortByWeight(final List<Equipment> equipmentList) {

        System.out.println("Equipment, sorted by weight: ");
        equipmentList.sort(Comparator.comparing(Equipment::getWeight));
    }

    public void print(final LinkedList<Equipment> equipmentList) {
        for (Equipment equipment : equipmentList) {
            System.out.println(equipment);
        }
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }
}
