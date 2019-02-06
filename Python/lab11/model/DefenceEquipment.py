from model.Equipment import Equipment


class DefenceEquipment(Equipment):
    def __init__(self, equipment_type, work_type, weight, body_part, size, id):
        self.equipment_type = equipment_type
        self.work_type = work_type
        self.weight = weight
        self.body_part = body_part
        self.size = size
        self.id = id

    def __init__(self, equipment_type, work_type, weight, body_part, size):
        self.equipment_type = equipment_type
        self.work_type = work_type
        self.weight = weight
        self.body_part = body_part
        self.size = size
        self.id = id