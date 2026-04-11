package kruskal;

import kruskal.Graf;
import kruskal.Hrana;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Hrana> hrany = new ArrayList<>();
        int maxVrchol = 0;

        try {
            File subor = new File("dataKruskal/pr3.hrn");
            Scanner skenuj = new Scanner(subor);

            while (skenuj.hasNextInt()) {
                int odkial = skenuj.nextInt();
                int kam = skenuj.nextInt();
                int cena = skenuj.nextInt();

                hrany.add(new Hrana(odkial, kam, cena));
                if (maxVrchol < odkial) {
                    maxVrchol = odkial;
                }
                if (maxVrchol < kam) {
                    maxVrchol = kam;
                }
            }
        } catch (Exception e) {
            System.out.println("Zlý súbor");
        }

        Graf graf = new Graf(hrany, maxVrchol + 1);
        graf.najdiKostru();
    }
}
