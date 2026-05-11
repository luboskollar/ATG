package toky;

import java.util.ArrayList;

public class Graf {
    private ArrayList<Hrana> zoznamHran;
    private int pocetVrcholov;

    public Graf(ArrayList<Hrana> zoznamHran, int pocetVrcholov) {
        this.zoznamHran = zoznamHran;
        this.pocetVrcholov = pocetVrcholov;
    }

    public void najdiMaxTok(int zdroj, int ustie) {
        while (true) {
            int[] x = new int[this.pocetVrcholov];
            for (int i = 0; i < this.pocetVrcholov; i++) {
                x[i] = Integer.MAX_VALUE;
            }
            x[zdroj] = 0;
            ArrayList<Integer> epsilon = new ArrayList<>();
            epsilon.add(zdroj);

            while (!epsilon.isEmpty()) {
                int aktualny = epsilon.get(0);
                epsilon.remove(0);

                for (Hrana hrana : this.zoznamHran) {
                    if (aktualny == hrana.getOdkial() && x[hrana.getKam()] == Integer.MAX_VALUE) {
                        if (hrana.getKapacita() - hrana.getTok() > 0) {
                            x[hrana.getKam()] = aktualny;
                            epsilon.add(hrana.getKam());
                        }
                    }
                    if (aktualny == hrana.getKam() && x[hrana.getOdkial()] == Integer.MAX_VALUE) {
                        if (hrana.getTok() > 0) {
                            x[hrana.getOdkial()] = -aktualny;
                            epsilon.add(hrana.getOdkial());
                        }
                    }
                }
            }
            if (x[ustie] == Integer.MAX_VALUE) {
                break;
            }

            int rezerva = Integer.MAX_VALUE;
            int aktualny = ustie;
            while (aktualny != zdroj) {
                int predchodca = Math.abs(x[aktualny]);
                for (Hrana hrana : this.zoznamHran) {
                    if (x[aktualny] > 0 && hrana.getOdkial() == predchodca && hrana.getKam() == aktualny) {
                        rezerva = Math.min(rezerva, hrana.getKapacita() - hrana.getTok());
                    }
                    if (x[aktualny] < 0 && hrana.getKam() == predchodca && hrana.getOdkial() == aktualny) {
                        rezerva = Math.min(rezerva, hrana.getTok());
                    }
                }
                aktualny = predchodca;
            }

            aktualny = ustie;
            while (aktualny != zdroj) {
                int predchodca = Math.abs(x[aktualny]);
                for (Hrana hrana : this.zoznamHran) {
                    if (x[aktualny] > 0 && hrana.getOdkial() == predchodca && hrana.getKam() == aktualny) {
                        hrana.setTok(hrana.getTok() + rezerva);
                    }
                    if (x[aktualny] < 0 && hrana.getKam() == predchodca && hrana.getOdkial() == aktualny) {
                        hrana.setTok(hrana.getTok() - rezerva);
                    }
                }
                aktualny = predchodca;
            }
        }
        int maxTok = 0;
        for (Hrana hrana : this.zoznamHran) {
            if (hrana.getOdkial() == zdroj) {
                maxTok += hrana.getTok();
            }
        }
        System.out.println("Maximálny tok: " + maxTok);

        for (Hrana hrana : this.zoznamHran) {
            System.out.println(hrana.getOdkial() + " -> " + hrana.getKam() + " tok: " + hrana.getTok() + "/" + hrana.getKapacita());
        }
    }
}
