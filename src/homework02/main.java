/**
 Să se implementeze o gramatică generativă astfel: se citesc din fișier elementele componente
 VN, VT, simbolul de start și producțiile. Se citește de la tastatură un număr n, iar apoi se
 generează n cuvinte în gramatică. Generarea are loc în modul următor: se pornește de fiecare
 dată de la simbolul de start. Cât timp există producții aplicabile se aplică random una dintre
 producțiile aplicabile.

 1. Se cere creearea unei clase Gramatica. În funcția principală main se declară un obiect de tip Gramatica.
 Membrii clasei vor fi: VN, VT, S, producțiile.
 Metodele clasei:
    (1) citire() - citirea elementelor gramaticii
    (2) verificare() - verificările descrise la punctul 3
    (3) afisare( ) - afișarea frumoasă a elementelor gramaticii
    (4) generare() - generarea de cuvinte pornind de la simbolul de start cu afisarea fiecarui pas.
    Punctaj construcția clasei - 1p

 2. Se cere citire din fișier a elementelor gramaticii: VN, VT, S, P - 1p

 3. Se cere după citire verificarea corectitudinii gramaticii, conform definiției unei gramatici generative, adică
    (1) VN intersectat cu VT = multimea vida
    (2) S apartine VN
    (3) pentru fiecare regulă membrul stâng conține cel puțin un neterminal
    (4) există cel puțin o producție care are în stânga doar S
    (5) fiecare producție conține doar elemente din VN și VT
    Punctaj - 2p.

 4. Generarea unui cuvânt în gramatică folosind o productie aleasă random dintre producțiile aplicabile la momentul
    curent - 2p

 5. Atunci, când nu mai sunt aplicabile producții, se verifică faptul că avem un cuvânt format doar din terminale - 1p

 6. Se cere afisarea formatata (frumoasa) a elementelor gramaticii si a fiecarui pas – 2p

**/



package homework02;

import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {

        Gramatica gramatica = new Gramatica();

        //se poate alege urmatoarele 3 fisiere: data.txt, data2.txt si data3.txt
        gramatica.inputData("data.txt");
        gramatica.displayData();
        gramatica.checkData();

        System.out.println("\nPlease insert how many word would you like to be created: ");
        Scanner input = new Scanner(System.in);
        int numberOfWords = input.nextInt();
        System.out.println();
        for (int i = 0; i < numberOfWords; i++) {
            String word = gramatica.generateWord();
            String status = gramatica.checkWord(word) ? "valid word" : "invalid word";
            System.out.println("Word number " + (i + 1) + " (" + status + "):   " + word);


        }

    }
}
