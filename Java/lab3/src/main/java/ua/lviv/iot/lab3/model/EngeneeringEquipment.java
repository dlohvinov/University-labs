package ua.lviv.iot.lab3.model;

import ua.lviv.iot.lab3.model.enums.EquipmentType;
import ua.lviv.iot.lab3.model.enums.WorkType;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class EngeneeringEquipment extends Equipment {

    @Column(name = "material")
    private String material;

    @Column(name = "color")
    private String color;

    public EngeneeringEquipment() {

    }

    public EngeneeringEquipment(final EquipmentType equipmentType, final WorkType workType, final double weight,
                                final String material, final String color) {
        this.setEquipmentType(equipmentType);
        this.setWorkType(workType);
        this.setWeight(weight);
        this.material = material;
        this.color = color;
    }

    @Override
    public String toString() {
        return "EngeneeringEquipment{" + "weight=" + getWeight() + ", equipmentType=" + getEquipmentType()
                + ", workType=" + getWorkType() + ", material='" + material + '\'' + ", color='" + color + '\'' + '}';
    }

    @Override
    public String getHeaders() {
        return super.getHeaders() + ", " + "material" + ", " + "color\n";
    }

    @Override
    public String toCSV() {
        return super.toCSV() + ", " + material + ", " + color + "\n";
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
