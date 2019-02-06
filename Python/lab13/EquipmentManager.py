# import sys
# sys.path.insert(0, 'model')
# sys.path.insert(1, 'model/enum')

from model.EngeneeringEquipment import EngeneeringEquipment
from model.DefenceEquipment import DefenceEquipment
from model.OffenceEquipment import OffenceEquipment
from model.enum.WorkType import WorkType
from model.enum.EquipmentType import EquipmentType

class EquipmentManager:
    equipment_list = list()
    result_list = list()

    def add_equipment(self, equipment):
        self.equipment_list.append(equipment)

    def get_by_work(self, work_type):
        for equipment in self.equipment_list:
            if(equipment.work_type == work_type):
                self.result_list.append(equipment)

        for equipment in self.result_list:
            print(equipment.equipment_type)

    def sort_by_weight(self):
        print("Equipment, sorted by weight: ")
        self.equipment_list.sort(key=lambda equip: equip.weight, reverse = False)


    def print_list(self):
        for equipment in self.equipment_list:
            print(equipment.equipment_type)

if __name__ == '__main__':
    eqiupment1 = DefenceEquipment(EquipmentType.SHOES, WorkType.SCOUTING, 1.0, "foot", "44")
    eqiupment2 = OffenceEquipment(EquipmentType.MACHINEGUN, WorkType.FIRING, 7.0, 2500, 30)
    eqiupment3 = EngeneeringEquipment(EquipmentType.SHOVEL, WorkType.ENGENEERING, 0.1, "steel", "white")
    equipment_manager = EquipmentManager()

    equipment_manager.add_equipment(eqiupment1)
    equipment_manager.add_equipment(eqiupment2)
    equipment_manager.add_equipment(eqiupment3)

    equipment_manager.get_by_work(WorkType.FIRING)
    equipment_manager.sort_by_weight()
    equipment_manager.print_list()