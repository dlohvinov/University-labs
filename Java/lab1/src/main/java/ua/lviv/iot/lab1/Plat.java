/*
Lohvinov Daniil
*
*/

package main.java.ua.lviv.iot.lab1;

public class Plat {

    public static final int NUMBER_OF_SOLDIERS = 31;

    private String commander = "Bohdan Bohdanov";
    private String deputyCommander = "Volodymyr Volodymerenko";
    private String medic = "Ivan Ivanchuk";
    private String sniper = "Vasyl";
    private int amountOfOperations = 10;

    public Plat() {

    }

    public Plat(String commander, String deputyCommander, String medic, String sniper) {
        this.commander = commander;
        this.deputyCommander = deputyCommander;
        this.medic = medic;
        this.sniper = sniper;
    }

    public Plat(String commander, String deputyCommander, String medic, String sniper, int amountOfOperations) {
        this.commander = commander;
        this.deputyCommander = deputyCommander;
        this.medic = medic;
        this.sniper = sniper;
        this.amountOfOperations = amountOfOperations;
    }

    //function that isnt clashes(toString name already exists)
    public void to_String() {
        System.out.println("Commander: " + commander);
        System.out.println("Deputy Commander: " + deputyCommander);
        System.out.println("Medic: " + medic);
        System.out.println("Sniper: " + sniper);
        System.out.println("Amount of Operations: " + amountOfOperations);
    }

    public void printStaticSum() {
        System.out.println("Number of Soldiers: " + NUMBER_OF_SOLDIERS);
    }

    public static void printSum() {
        System.out.println("Number of Soldiers: " + NUMBER_OF_SOLDIERS);
    }

    public void resetValues() {
        this.commander = "Bohdan Bohdanov";
        this.deputyCommander = "Volodymyr Volodymerenko";
        this.medic = "Ivan Ivanchuk";
        this.sniper = "Vasyl";
        this.amountOfOperations = 10;
    }

    public static void main(String[] args) {
        Plat firstPlat = new Plat();
        firstPlat.to_String();

        Plat secPlat = new Plat("Jonh Johnov", "Steve Stevenko", "Jane Janenko", "Lance");
        secPlat.to_String();

        Plat thirdPlat = new Plat("Hance Hancov", "Henrih Henrihenko", "Joseph Josephchuk", "Vance", 13);
        thirdPlat.to_String();

        thirdPlat.resetValues();
        thirdPlat.to_String();

        firstPlat.printStaticSum();
        Plat.printSum();

    }
}
