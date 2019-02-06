from model.Equipment import Equipment


class OffenceEquipment(Equipment):
    def __init__(self, equipment_type, work_type, weight, range, ammo):
        self.equipment_type = equipment_type
        self.work_type = work_type
        self.weight = weight
        self.range = range
        self.ammo = ammo
