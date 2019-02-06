package ua.lviv.iot.lab3.model;

import ua.lviv.iot.lab3.model.enums.EquipmentType;
import ua.lviv.iot.lab3.model.enums.WorkType;

public class OffenceEquipment extends Equipment {
    private int range;
    private int ammo;

public OffenceEquipment(final EquipmentType equipmentType, final WorkType workType, final double weight,
                             final int range, final int ammo) {
         this.setEquipmentType(equipmentType);
         this.setWorkType(workType);
         this.setWeight(weight);
         this.range = range;
         this.ammo = ammo;
    }

@Override
public String toString() {
        return "OffenceEquipment{" + "weight=" + getWeight() + ", equipmentType=" + getEquipmentType() + ", workType="
                + getWorkType() + ", range=" + range + ", ammo=" + ammo + '}';
    }
}
