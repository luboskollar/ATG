package labelSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Graf {
    private int pocetVrcholov;
    private ArrayList<Vrchol> zoznamVrcholov;

    private ArrayList<int[]>[] susedia;

    public Graf(int pocetVrcholov, ArrayList<Hrana> zoznamHran) {
        this.pocetVrcholov = pocetVrcholov;

        this.zoznamVrcholov = new ArrayList<>();
        for (int i = 0; i < this.pocetVrcholov; i++) {
            Vrchol vrchol = new Vrchol(i);
            this.zoznamVrcholov.add(vrchol);
        }

        this.susedia = new ArrayList[pocetVrcholov];
        for (int i = 0; i < pocetVrcholov; i++) {
            this.susedia[i] = new ArrayList<>();
        }

        for (Hrana h : zoznamHran) {
            int odkial = h.getOdkial();
            int kam = h.getKam();
            int cena = h.getCena();

            int[] sused = new int[2];
            sused[0] = kam;
            sused[1] = cena;

            this.susedia[odkial].add(sused);
        }
    }

    public void najdiVzdialenost(int start, int ciel) {
        int[] vzdialenosti = new int[this.pocetVrcholov];
        int[] predchodcovia = new int[this.pocetVrcholov];

        for (int i = 0; i < this.pocetVrcholov; i++) {
            vzdialenosti[i] = Integer.MAX_VALUE;
            predchodcovia[i] = -1;
        }

        PriorityQueue<int[]> epsilon = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[0] - b[0];
            }
        });

        vzdialenosti[start] = 0;

        int[] startovnyVrchol = new int[2];
        startovnyVrchol[0] = 0;
        startovnyVrchol[1] = start;
        epsilon.add(startovnyVrchol);

        while (!epsilon.isEmpty()) {

            int[] aktualny = epsilon.poll();
            int riadiaci = aktualny[1];

            for (int[] sused : this.susedia[riadiaci]) {
                int cielHrany = sused[0];
                int cenaHrany = sused[1];

                if (vzdialenosti[cielHrany] > vzdialenosti[riadiaci] + cenaHrany) {
                    vzdialenosti[cielHrany] = vzdialenosti[riadiaci] + cenaHrany;
                    predchodcovia[cielHrany] = riadiaci;

                    int[] novyVrchol = new int[2];
                    novyVrchol[0] = vzdialenosti[cielHrany];
                    novyVrchol[1] = cielHrany;
                    epsilon.add(novyVrchol);
                }
            }
        }

        ArrayList<Integer> cesta = new ArrayList<>();
        int aktualnyVrchol = ciel;
        while (aktualnyVrchol != -1) {
            cesta.add(aktualnyVrchol);
            aktualnyVrchol = predchodcovia[aktualnyVrchol];
        }

        for (int i = cesta.size() - 1; i >= 0; i--) {
            System.out.print(cesta.get(i));
            if (i != 0) {
                System.out.print("->");
            }
        }
        System.out.println();
        System.out.println("Najkratšia vzdialenosť do vrcholu " + ciel + " je: " + vzdialenosti[ciel]);
    }
}