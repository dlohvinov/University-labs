package ua.lviv.iot;

import org.junit.jupiter.api.Test;
import ua.lviv.iot.lab3.EquipmentManager;
import ua.lviv.iot.lab3.EquipmentService;
import ua.lviv.iot.lab3.EquipmentWriter;
import ua.lviv.iot.lab3.model.DefenceEquipment;
import ua.lviv.iot.lab3.model.EngeneeringEquipment;
import ua.lviv.iot.lab3.model.Equipment;
import ua.lviv.iot.lab3.model.OffenceEquipment;
import ua.lviv.iot.lab3.model.enums.EquipmentType;
import ua.lviv.iot.lab3.model.enums.WorkType;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EquipmentManagerTest {

    private EquipmentManager equipmentManager = null;

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

    EquipmentWriter equipmentWriter = new EquipmentWriter();

    {
        equipmentManager = new EquipmentManager();
        equipmentManager.addEquipment(riffle);
        equipmentManager.addEquipment(machinegun);
        equipmentManager.addEquipment(pistol);
        equipmentManager.addEquipment(flakJacket);
        equipmentManager.addEquipment(shoesScouting);
        equipmentManager.addEquipment(shoesEngeneering);
        equipmentManager.addEquipment(binoculars);
        equipmentManager.addEquipment(shovel);
        equipmentManager.addEquipment(knife);

    }

    @Test
    public void testGetByWork() {
        assertNotNull(equipmentManager.getEquipmentList());
        List<Equipment> result = equipmentManager.getByWork(WorkType.FIRING);
        assertEquals(3, result.size());
        for (Equipment res : result) {
            assertEquals(WorkType.FIRING, res.getWorkType());
        }
    }

    @Test
    public void testSortByWeight() {
        List<Equipment> input = new LinkedList<>(List.of(riffle, machinegun, pistol, flakJacket, shoesScouting));
        equipmentManager.sortByWeight(input);
        assertEquals(5, input.size());
        assertEquals(0.5, input.get(0).getWeight());
        assertEquals(8, input.get(4).getWeight());
    }

    @Test
    public void writeToFile() {
        try {
            equipmentWriter.writeToFile(equipmentManager.getEquipmentList());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
