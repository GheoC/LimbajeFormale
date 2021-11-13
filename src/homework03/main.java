/**
 Automate finite deterministe
 Cerințe:
 Să se implementeze un AFD (automat finit determinist) astfel: se citesc din fișier elementele componente
 ale automatului Q, Σ, δ, q0, F.
 Se citește de la tastatură un cuvant și se verifică, dacă este acceptat de către automat.
 Barem
 1. Se cere crearea unei clase AFD (alta decât clasa principală). În funcția principală main se
 declară un obiect de tip AFD
 Membrii clasei vor fi: Stări, Sigma, Delta, StareInit, Finale
 Metodele clasei:
 (1) citire() - cititrea elementelor automatului
 (2) afisare() - afișarea formatată (frumoasă) a automatului
 (3) verificare(cuvant) - verifică dacă cuvântul dat ca parametru este acceptat de către
 automat și afișază: "acceptat" - dacă este cuvânt acceptat, "neacceptat" - dacă nu este acceptat ,
 "blocaj" - dacă automatul se blochează pe parcurs.
 Construcția corectă a clasei:- 2p
 2. Citirea din fișier a elementelor AFD-ului - 1p
 3. Afisarea formatată (frumoasă) a elementelor automatului - 1p
 4. Posibilitatea de a verifica mai multe cuvinte, fără a reporni algoritmul - 1p
 5. Functionalitatea completa a algoritmului - 4p
 **/

package homework03;

import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {

        /*
        Instructiuni de completare a fisierului sursa:
        1. pe primul rand se introduc caracterele alfabetului cu spatiu intre ele
        2. pe urmatoarele randuri se introduc starile automatului dupa urmatoarea sintaxa:
            - "stare,  stariViitoare[...], statusStareFinala=true/false"
            OBS:
            - stari viitoare trebuie sa fie tot atatea cate caractere tranzitionale sunt in alfabetul introdus
            - starea de inceput este intotdeauna q0.
            - pentru lipsa unei legaturi inre stari va rog sa treceti simbolul "-"

        Exista clasa de testare pentru datele din din fisierul "automat01.txt"

        */

        AutomatFinitDeterminist afd = new AutomatFinitDeterminist();
        afd.readData("automat01.txt");
        afd.displayData();
        AutomatService automatService = new AutomatService("q0", afd);

        Scanner input = new Scanner(System.in);
        String optiune;
        do {
            System.out.println("Please insert the word: ");
            String word = input.next();
            automatService.checkWord(word);

            System.out.println("\nDo you want to insert another text? ('y' to continue)");
            optiune = input.next();
        } while (optiune.equals("y"));


    }
}
