package ua.lviv.iot.lab3.model;

import ua.lviv.iot.lab3.model.enums.EquipmentType;
import ua.lviv.iot.lab3.model.enums.WorkType;

public abstract class Equipment {
    private double weight;
    private EquipmentType equipmentType;
    private WorkType workType;

    public double getWeight() {
        return weight;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public WorkType getWorkType() {
        return workType;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public void setWorkType(WorkType workType) {
        this.workType = workType;
    }
}



