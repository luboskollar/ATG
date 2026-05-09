package cpm;

public class Vrchol {
    private int id;
    private int trvanie;
    private int z;
    private int k;

    public Vrchol(int id, int trvanie) {
        this.id = id;
        this.trvanie = trvanie;
    }

    public int getId() {
        return this.id;
    }

    public int getTrvanie() {
        return this.trvanie;
    }

    public int getZ() {
        return this.z;
    }

    public int getK() {
        return this.k;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setK(int k) {
        this.k = k;
    }
}