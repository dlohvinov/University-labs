from flask import jsonify, request
from model.enum.EquipmentType import EquipmentType
from model.enum.WorkType import WorkType
from model.DefenceEquipment import DefenceEquipment
from lab_app import app

defence_equipment_dict = dict()

@app.route("/equipment", methods=["POST"])
def add_equipment():
    request_getter = request.get_json()
    equipment_type = request_getter['equipment_type']
    work_type = request_getter['work_type']
    weight = request_getter['weight']
    body_part = request_getter['body_part']
    size = request_getter['size']
    id = request_getter['id']
    defence_equipment = DefenceEquipment(equipment_type, work_type, weight, body_part, size)
    defence_equipment_dict[id] = defence_equipment
    return 'done'

@app.route("/equipment/<id>", methods=["GET"])
def get_equipment(id):
    if id in defence_equipment_dict:
        return defence_equipment_dict[id].__dict__.__str__()
    else:
        return "nope"

@app.route("/equipment/<id>", methods=["PUT"])
def update_equipment(id):
    if id in defence_equipment_dict:
        request_getter = request.get_json()
        equipment_type = request_getter['equipment_type']
        work_type = request_getter['work_type']
        weight = request_getter['weight']
        body_part = request_getter['body_part']
        size = request_getter['size']
        update_equipment = defence_equipment_dict[id]
        update_equipment.equipment_type = equipment_type
        update_equipment.work_type = work_type
        update_equipment.weight = weight
        update_equipment.body_part = body_part
        update_equipment.size = size
        defence_equipment_dict[id] = update_equipment
        return "done"
    else:
        return "none"

@app.route("/equipment/<id>", methods=["DELETE"])
def delete_equipment(id):
    if id in defence_equipment_dict:
        del defence_equipment_dict[id]
        return "done"
    else:
        return "none"

