package ua.lviv.iot.lab3;

import ua.lviv.iot.lab3.model.Equipment;

import java.io.*;
import java.util.List;

public class EquipmentWriter {
    private File file = new File("data.txt");

    public void writeToFile(final List<Equipment> equipmentList) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file)) {
            for (Equipment equipment : equipmentList) {
                fileWriter.write(equipment.getHeaders());
                fileWriter.write(equipment.toCSV());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
