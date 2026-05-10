package toky;

public class Hrana {
    private int odkial;
    private int kam;
    private int kapacita;
    private int tok;

    public Hrana(int odkial, int kam, int kapacita) {
        this.odkial = odkial;
        this.kam = kam;
        this.kapacita = kapacita;
        this.tok = 0;
    }

    public int getOdkial() {
        return this.odkial;
    }

    public int getKam() {
        return this.kam;
    }

    public int getKapacita() {
        return this.kapacita;
    }

    public int getTok() {
        return this.tok;
    }

    public void setTok(int tok) {
        this.tok = tok;
    }
}
