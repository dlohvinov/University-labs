package ua.lviv.iot;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import ua.lviv.iot.lab3.EquipmentManager;
import ua.lviv.iot.lab3.model.DefenceEquipment;
import ua.lviv.iot.lab3.model.EngeneeringEquipment;
import ua.lviv.iot.lab3.model.Equipment;
import ua.lviv.iot.lab3.model.OffenceEquipment;
import ua.lviv.iot.lab3.model.enums.EquipmentType;
import ua.lviv.iot.lab3.model.enums.WorkType;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//TESTS THROUGH MAIN METHOD
public class MyTest {
//
//
//    @Test
//    public void anotherTest( ) {
//        throw new RuntimeException();
//    }
//
//    @Test
//    public  void test(){ Equipment riffle = new OffenceEquipment(EquipmentType.RIFFLE, WorkType.SCOUTING, 7,
//            10000, 10);
//        Equipment machinegun = new OffenceEquipment(EquipmentType.MACHINEGUN, WorkType.FIRING, 4,
//                1000, 30);
//        Equipment pistol = new OffenceEquipment(EquipmentType.PISTOL, WorkType.FIRING, 0.5,
//                500, 20);
//
//        Equipment flakJacket = new DefenceEquipment(EquipmentType.FLAK_JACKET, WorkType.FIRING, 8,
//                "Body", 48);
//        Equipment shoesScouting = new DefenceEquipment(EquipmentType.SHOES, WorkType.SCOUTING, 1,
//                "Foot", 43);
//        Equipment shoesEngeneering = new DefenceEquipment(EquipmentType.SHOES, WorkType.ENGENEERING, 1.5,
//                "Foot", 43);
//
//        Equipment binoculars = new EngeneeringEquipment(EquipmentType.BINOCULARS, WorkType.SCOUTING, 0.2,
//                "plastic", "green");
//        Equipment shovel = new EngeneeringEquipment(EquipmentType.SHOVEL, WorkType.ENGENEERING, 0.2,
//                "iron", "yellow");
//        Equipment knife = new EngeneeringEquipment(EquipmentType.KNIFE, WorkType.ENGENEERING, 0.1,
//                "steel", "grey");
//
//        EquipmentManager equipmentManager = new EquipmentManager();
//
//            assert (riffle.getEquipmentType() == EquipmentType.RIFFLE);
//            assertFalse (pistol.getWeight() == 10);
//
//
//            equipmentManager.setEquipment(riffle, machinegun, pistol, flakJacket, shoesScouting, shoesEngeneering,
//                    binoculars, shovel, knife);
//
//            assertNotNull(equipmentManager.getEquipment());
//            assertSame(equipmentManager.getByWork(WorkType.FIRING), equipmentManager.getByWork(WorkType.SCOUTING));
//            LinkedList <Equipment> beforeSort = equipmentManager.getEquipment();
//            equipmentManager.sortByWeight();
//            assertSame(equipmentManager.getEquipment(), beforeSort);
//        }
//
//
//






























// ORIGINAL TESTS

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

    @Test
    public void testClass(){
        assert (riffle.getEquipmentType() == EquipmentType.RIFFLE);
        assertFalse (pistol.getWeight() == 10);
    }


    @Test
    public void testManager(){

        equipmentManager.setEquipment(riffle, machinegun, pistol, flakJacket, shoesScouting, shoesEngeneering,
                binoculars, shovel, knife);

        assertNotNull(equipmentManager.getEquipment());
        assertSame(equipmentManager.getByWork(WorkType.FIRING), equipmentManager.getByWork(WorkType.SCOUTING));
        LinkedList <Equipment> beforeSort = equipmentManager.getEquipment();
        equipmentManager.sortByWeight();
        assertSame(equipmentManager.getEquipment(), beforeSort);
    }
}
