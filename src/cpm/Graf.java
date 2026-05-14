package cpm;

import java.util.ArrayList;

public class Graf {
    private ArrayList<Hrana> zoznamHran;
    private ArrayList<Vrchol> zoznamVrcholov;
    private int pocetVrcholov;
    private int[] ideg;

    public Graf(ArrayList<Hrana> zoznamHran, ArrayList<Vrchol> zoznamVrcholov) {
        this.zoznamHran = zoznamHran;
        this.zoznamVrcholov = zoznamVrcholov;
        this.pocetVrcholov = zoznamVrcholov.size();

        this.ideg = new int[this.pocetVrcholov];
        for (Hrana hrana : this.zoznamHran) {
            this.ideg[hrana.getKam()]++;
        }
    }

    private ArrayList<Integer> monotonneOcislovanie() {
        ArrayList<Integer> poradie = new ArrayList<>();
        for (int i = 0; i < this.pocetVrcholov; i++) {
            if (this.ideg[i] == 0) {
                poradie.add(i);
            }
        }
        for (int i = 0; i < poradie.size(); i++) {
            for (Hrana hrana : this.zoznamHran) {
                if (poradie.get(i) == hrana.getOdkial()) {
                    this.ideg[hrana.getKam()]--;
                    if (this.ideg[hrana.getKam()] == 0) {
                        poradie.add(hrana.getKam());
                    }
                }
            }
        }
        return poradie;
    }

    public void vypocitaj() {
        ArrayList<Integer> poradie = this.monotonneOcislovanie();

        for (int i = 1; i < poradie.size(); i++) {
            int aktualny = poradie.get(i);
            for (Hrana hrana : this.zoznamHran) {
                if (hrana.getKam() == aktualny) {
                    int novyZ = this.zoznamVrcholov.get(hrana.getOdkial()).getZ() + this.zoznamVrcholov.get(hrana.getOdkial()).getP();
                    if (novyZ > this.zoznamVrcholov.get(aktualny).getZ()) {
                        this.zoznamVrcholov.get(aktualny).setZ(novyZ);
                    }
                }
            }
        }

        int posledny = poradie.get(poradie.size() - 1);
        int trvanieProjektu = this.zoznamVrcholov.get(posledny).getZ() + this.zoznamVrcholov.get(posledny).getP();

        for (int i = 1; i < poradie.size(); i++) {
            this.zoznamVrcholov.get(poradie.get(i)).setK(trvanieProjektu);
        }

        for (int i = poradie.size() - 1; i >= 1; i--) {
            int aktualny = poradie.get(i);
            for (Hrana hrana : this.zoznamHran) {
                if (hrana.getKam() == aktualny) {
                    int novyKoniec = this.zoznamVrcholov.get(aktualny).getK() - this.zoznamVrcholov.get(aktualny).getP();
                    if (this.zoznamVrcholov.get(hrana.getOdkial()).getK() > novyKoniec) {
                        this.zoznamVrcholov.get(hrana.getOdkial()).setK(novyKoniec);
                    }
                }
            }
        }

        System.out.println("\nv | z(v) | k(v) | r(v)");
        for (int i = 1; i < this.pocetVrcholov; i++) {
            Vrchol v = this.zoznamVrcholov.get(i);
            int rezerva = v.getK() - v.getZ() - v.getP();
            System.out.println(i + " | " + v.getZ() + " | " + v.getK() + " | " + rezerva);
        }
        System.out.println("Doba trvania projektu = " + trvanieProjektu);

        System.out.print("\nKritická cesta: ");
        for (int i = 1; i < poradie.size(); i++) {
            int vrchol = poradie.get(i);
            Vrchol v = this.zoznamVrcholov.get(vrchol);
            int rezerva = v.getK() - v.getZ() - v.getP();
            if (rezerva == 0) {
                System.out.print(vrchol + " -> ");
            }
        }
        System.out.println();
    }
}