from model.Equipment import Equipment

class EngeneeringEquipment(Equipment):
    def __init__(self, equipment_type, work_type, weight, material, color):
        self.equipment_type = equipment_type
        self.work_type = work_type
        self.weight = weight
        self.material = material
        self.color = color