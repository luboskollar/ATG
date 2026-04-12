package kruskal;

import java.util.ArrayList;
import java.util.Collections;

public class Graf {
    private int[] k;
    private ArrayList<Hrana> zoznamHran;
    private int pocetVrcholov;

    public Graf(ArrayList<Hrana> zoznamHran, int pocetVrcholov) {
        this.zoznamHran = zoznamHran;
        this.pocetVrcholov = pocetVrcholov;
        this.k = new int[this.pocetVrcholov];

        for (int i = 0; i < pocetVrcholov; i++) {
            this.k[i] = i;
        }
    }

    public void najdiKostru() {
        Collections.sort(this.zoznamHran, new HranaComparator());
        ArrayList<Hrana> kostra = new ArrayList<>();

        for (Hrana h : this.zoznamHran) {
            if (this.k[h.getOdkial()] != this.k[h.getKam()]) {
                kostra.add(h);
                int stareK = this.k[h.getKam()];
                int noveK = this.k[h.getOdkial()];

                for (int i = 0; i < this.pocetVrcholov; i++) {
                    if (this.k[i] == stareK) {
                        this.k[i] = noveK;
                    }
                }

                if (kostra.size() == this.pocetVrcholov - 1) {
                    break;
                }
            }
        }

        int finalCena = 0;
        for (Hrana h : kostra) {
            System.out.println(h.getOdkial() + "->" + h.getKam() + " cena: " + h.getCena());
            finalCena += h.getCena();
        }

        System.out.println("Celková cena: " + finalCena);
    }
}
