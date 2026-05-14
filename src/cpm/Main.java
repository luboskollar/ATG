package cpm;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Hrana> hrany = new ArrayList<>();
        ArrayList<Vrchol> vrcholy = new ArrayList<>();
        int maxVrchol = 0;

        try {
            File suborHrn = new File("dataCPM/CPM_stred.hrn");
            File suborTim = new File("dataCPM/CPM_stred.tim");
            Scanner skenujHrn = new Scanner(suborHrn);
            Scanner skenujTim = new Scanner(suborTim);

            while (skenujHrn.hasNextInt()) {
                int odkial = skenujHrn.nextInt();
                int kam = skenujHrn.nextInt();
                int cena = skenujHrn.nextInt();

                hrany.add(new Hrana(odkial, kam, cena));
                if (maxVrchol < odkial) {
                    maxVrchol = odkial;
                }
                if (maxVrchol < kam) {
                    maxVrchol = kam;
                }

            }
            skenujHrn.close();

            vrcholy.add(null);
            int i = 1;
            while (skenujTim.hasNextInt()) {
                vrcholy.add(new Vrchol(i, skenujTim.nextInt()));
                i++;
            }
            skenujTim.close();
        } catch (Exception e) {
            System.out.println("Zlý súbor");
        }
        Graf graf = new Graf(hrany, vrcholy);
        graf.vypocitaj();
    }
}