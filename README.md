<div align="center">
    <br>
    <h1>💾Algoritmická teória grafov</h1>
    <strong>Vypracovanie zadaní pre predmet ATG</strong>
</div>
<br>
Tento repozitár obsahuje implementácie algoritmov vypracované počas semestra pre predmet ATG. 
Konkrétne ide o algoritmy LabelSet, Kruskal, CPM a toky v sieťach.

Súčasťou repozitára sú testovacie dáta vo formátoch `.hrn` a `.tim`

### .hrn
Každý riadok reprezentuje jednu hranu grafu.

| Zdroj | Cieľ | cena | 
|-------|------|------| 
| 1     | 2    | 10   |

### .tim
Každý riadok zodpovedá jednému vrcholu. Číslo riadku = index vrcholu, hodnota = dĺžka trvania aktivity.

---
## LabelSet - Najkratšia cesta

Hľadanie najkratšej cesty v ohodnotenom grafe.
- [`Main.java`](src/labelSetCezHashMap/Main.java)
- [`Graf.java`](src/labelSetCezHashMap/Graf.java)
- [`Hrana.java`](src/labelSetCezHashMap/Hrana.java)
- [`Vrchol.java`](src/labelSetCezHashMap/Vrchol.java)
---
## Kruskal - Maximálna kostra

Hľadanie maximálnej kostry grafu.
- [`Main.java`](src/kruskal/Main.java)
- [`Graf.java`](src/kruskal/Graf.java)
- [`Hrana.java`](src/kruskal/Hrana.java)
- [`HranaComparator.java`](src/kruskal/HranaComparator.java)

---
## CPM - Kritická cesta

Výpočet kritickej cesty v projekte.
- [`Main.java`](src/cpm/Main.java)
- [`Graf.java`](src/cpm/Graf.java)
- [`Hrana.java`](src/cpm/Hrana.java)
- [`Vrchol.java`](src/cpm/Vrchol.java)
---
## Toky v sieťach

Hľadanie maximálneho toku v sieti od zdroja po ústie.
- [`Main.java`](src/toky/Main.java)
- [`Graf.java`](src/toky/Graf.java)
- [`Hrana.java`](src/toky/Hrana.java)