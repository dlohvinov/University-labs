package ua.lviv.iot.lab3.model;

import ua.lviv.iot.lab3.model.enums.EquipmentType;
import ua.lviv.iot.lab3.model.enums.WorkType;

public class DefenceEquipment extends Equipment {
    private String bodyPart;
    private int size;

public DefenceEquipment(final EquipmentType equipmentType, final WorkType workType, final double weight,
                        final String bodyPart, final int size) {
     this.setEquipmentType(equipmentType);
     this.setWorkType(workType);
     this.setWeight(weight);
     this.bodyPart = bodyPart;
     this.size = size;
    }

    @Override
public String toString() {
        return "DefenceEquipment{" + "weight=" + getWeight() + ", equipmentType=" + getEquipmentType() + ", workType="
                + getWorkType() + ", bodyPart='" + bodyPart  + '\'' + ", size=" + size + '}';
    }
}
