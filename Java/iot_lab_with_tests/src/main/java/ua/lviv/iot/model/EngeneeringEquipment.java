package ua.lviv.iot.lab3.model;

import ua.lviv.iot.lab3.model.enums.EquipmentType;
import ua.lviv.iot.lab3.model.enums.WorkType;

public class EngeneeringEquipment extends Equipment {
    private String material;
    private String color;

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
        return "EngeneeringEquipment{" + "weight=" + getWeight() +  ", equipmentType=" + getEquipmentType()
                + ", workType=" + getWorkType() + ", material='" + material + '\'' + ", color='" + color + '\'' + '}';
    }
}
