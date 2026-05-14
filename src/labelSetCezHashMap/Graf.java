package labelSetCezHashMap;

import java.util.ArrayList;
import java.util.HashMap;

public class Graf {
    private HashMap<Integer, ArrayList<Hrana>> susedia;
    private int pocetVrcholov;
    public Graf(int pocetVrcholov, ArrayList<Hrana> zoznamHran) {
        this.susedia = new HashMap<>();
        this.pocetVrcholov = pocetVrcholov;
        for (Hrana h : zoznamHran) {
            if (this.susedia.containsKey(h.getOdkial())) {
                this.susedia.get(h.getOdkial()).add(h);
            } else {
                this.susedia.put(h.getOdkial(), new ArrayList<>());
                this.susedia.get(h.getOdkial()).add(h);
            }
        }
    }

    public void najdiVzdialenost(int start, int ciel) {
        int[] vzdialenosti = new int[this.pocetVrcholov];
        int[] predchodcovia = new int[this.pocetVrcholov];
        boolean[] spracovane = new boolean[this.pocetVrcholov];

        for (int i = 0; i < this.pocetVrcholov; i++) {
            vzdialenosti[i] = Integer.MAX_VALUE;
            predchodcovia[i] = -1;
        }

        ArrayList<Integer> epsilon = new ArrayList<>();
        vzdialenosti[start] = 0;
        epsilon.add(start);

        while (!epsilon.isEmpty()) {
            int min = Integer.MAX_VALUE;
            int riadiaci = -1;

            for (int vrchol : epsilon) {
                if (vzdialenosti[vrchol] < min) {
                    min = vzdialenosti[vrchol];
                    riadiaci = vrchol;
                }
            }
            epsilon.remove(riadiaci);

            if (spracovane[riadiaci]) {
                continue;
            }
            spracovane[riadiaci] = true;

            if (riadiaci == ciel) {
                break;
            }
            //System.out.println("Riadiaci: " + riadiaci + " epsilon: " + epsilon.size());
            if (this.susedia.get(riadiaci) == null) {
                continue;
            }

            for (Hrana h : this.susedia.get(riadiaci)) {
                int cielHrany = h.getKam();
                int cenaHrany = h.getCena();

                if (vzdialenosti[cielHrany] > vzdialenosti[riadiaci] + cenaHrany) {
                    vzdialenosti[cielHrany] = vzdialenosti[riadiaci] + cenaHrany;
                    predchodcovia[cielHrany] = riadiaci;
                }

                if (!epsilon.contains(cielHrany)) {
                    epsilon.add(cielHrany);
                }
            }
        }
        ArrayList<Integer> cesta = new ArrayList<>();
        int aktualnyVrchol = ciel;
        while (aktualnyVrchol != -1) {
            cesta.add(aktualnyVrchol);
            aktualnyVrchol = predchodcovia[aktualnyVrchol];
        }

        for (int i = cesta.size() - 1; i >= 0 ; i--) {
            System.out.print(cesta.get(i));
            if (i != 0) {
                System.out.print("->");
            }
        }
        System.out.println();
        System.out.println("Najkratšia cesta do vrcholu: " + ciel + " je " + vzdialenosti[ciel]);
    }
}
