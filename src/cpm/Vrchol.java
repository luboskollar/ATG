package cpm;

public class Vrchol {
    private int id;
    private int p;
    private int z;
    private int k;

    public Vrchol(int id, int p) {
        this.id = id;
        this.p = p;
    }

    public int getId() {
        return this.id;
    }

    public int getP() {
        return this.p;
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