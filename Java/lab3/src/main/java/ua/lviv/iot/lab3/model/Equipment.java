package ua.lviv.iot.lab3.model;

import ua.lviv.iot.lab3.model.enums.EquipmentType;
import ua.lviv.iot.lab3.model.enums.WorkType;

import javax.persistence.*;

@MappedSuperclass
public abstract class Equipment {

    @Column(name = "equipment_weight")
    private double weight;

    @Column(name = "equipment_type")
    private EquipmentType equipmentType;

    @Column(name = "work_type")
    private WorkType workType;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "equipment_id")
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeaders(){
        return "weight" + ", " + "equipmentType" + ", " + "workType";
    }

    public String toCSV(){
        return String.valueOf(getWeight()) + ", " + String.valueOf(getEquipmentType())
                + ", " + String.valueOf(getWorkType() + ", " + String.valueOf(getId()));
    }

}



