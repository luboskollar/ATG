package labelSet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Hrana> zoznamHran = new ArrayList<>();
        int maxVrchol = 0;

        try {
            Scanner skener = new Scanner(new File("data/pr1.hrn"));

            while (skener.hasNextInt()) {
                int odkial = skener.nextInt();
                int kam = skener.nextInt();
                int cena = skener.nextInt();

                zoznamHran.add(new Hrana(odkial, kam, cena));
                if (odkial > maxVrchol) {
                    maxVrchol = odkial;
                }
                if (kam > maxVrchol) {
                    maxVrchol = kam;
                }
            }
            skener.close();
            System.out.println("Načítanie úspešné!");
            System.out.println("Počet hrán v pamäti: " + zoznamHran.size());
            System.out.println("Najvyššie ID vrcholu: " + maxVrchol);
        } catch (FileNotFoundException e) {
            System.out.println("Súbor sa nenašiel");
        }

        Graf graf = new Graf(maxVrchol + 1, zoznamHran);
        graf.najdiVzdialenost(1, 12);
    }
}