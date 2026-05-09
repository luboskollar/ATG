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
        ArrayList<Integer> p = new ArrayList<>();
        for (int i = 0; i < this.pocetVrcholov; i++) {
            if (this.ideg[i] == 0) {
                p.add(i);
            }
        }
        for (int i = 0; i < p.size(); i++) {
            for (Hrana hrana : this.zoznamHran) {
                if (p.get(i) == hrana.getOdkial()) {
                    this.ideg[hrana.getKam()]--;
                    if (this.ideg[hrana.getKam()] == 0) {
                        p.add(hrana.getKam());
                    }
                }
            }
        }
        return p;
    }

    public void vypocitaj() {
        ArrayList<Integer> p = this.monotonneOcislovanie();

        for (int i = 0; i < p.size(); i++) {
            int aktualny = p.get(i);
            for (Hrana hrana : this.zoznamHran) {
                if (hrana.getKam() == aktualny) {
                    int novyZ = this.zoznamVrcholov.get(hrana.getOdkial()).getZ() + this.zoznamVrcholov.get(hrana.getOdkial()).getTrvanie();
                    if (novyZ > this.zoznamVrcholov.get(aktualny).getZ()) {
                        this.zoznamVrcholov.get(aktualny).setZ(novyZ);
                    }
                }
            }
        }

        int posledny = p.get(p.size() - 1);
        int trvanieProjektu = this.zoznamVrcholov.get(posledny).getZ() + this.zoznamVrcholov.get(posledny).getTrvanie();

        for (int i = 0; i < p.size(); i++) {
            if (this.zoznamVrcholov.get(p.get(i)) == null) {
                continue;
            }
            this.zoznamVrcholov.get(p.get(i)).setK(trvanieProjektu);
        }

        for (int i = p.size() - 1; i >= 0; i--) {
            int aktualny = p.get(i);
            if (this.zoznamVrcholov.get(aktualny) == null) {
                continue;
            }
            for (Hrana hrana : this.zoznamHran) {
                if (hrana.getKam() == aktualny) {
                    int kandidat = this.zoznamVrcholov.get(aktualny).getK() - this.zoznamVrcholov.get(aktualny).getTrvanie();
                    if (this.zoznamVrcholov.get(hrana.getOdkial()).getK() > kandidat) {
                        this.zoznamVrcholov.get(hrana.getOdkial()).setK(kandidat);
                    }
                }
            }
        }

        System.out.println("\nv | z(v) | k(v) | r(v)");
        for (int i = 1; i < this.pocetVrcholov; i++) {
            Vrchol v = this.zoznamVrcholov.get(i);
            int rezerva = v.getK() - v.getZ() - v.getTrvanie();
            System.out.println(i + " | " + v.getZ() + " | " + v.getK() + " | " + rezerva);
        }
        System.out.println("Doba trvania projektu = " + trvanieProjektu);

        System.out.print("\nKritická cesta: ");
        for (int i = 0; i < p.size(); i++) {
            int vrchol = p.get(i);
            if (this.zoznamVrcholov.get(vrchol) == null) {
                continue;
            }
            Vrchol v = this.zoznamVrcholov.get(vrchol);
            int rezerva = v.getK() - v.getZ() - v.getTrvanie();
            if (rezerva == 0) {
                System.out.print(vrchol + " -> ");
            }
        }
        System.out.println();
    }
}