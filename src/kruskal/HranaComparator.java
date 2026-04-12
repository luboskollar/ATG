package kruskal;

import java.util.Comparator;

public class HranaComparator implements Comparator<Hrana> {
    @Override
    public int compare(Hrana a, Hrana b) {
        return b.getCena() - a.getCena();
    }
}
