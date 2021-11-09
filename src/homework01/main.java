/**
 * Tema 1:
 * Implementarea unui algoritm normal in sens Markov
 * Sa se implementeze un algoritm normal in sens Markov, astfel:
 * - Se citesc din fisier componentele (vocabularul si regulile de rescriere);
 * - Se citeste de la tastatura un cuvant, se deriveaza pe baza algoritmului (se
 * deriveaza pana se aplica o regula finala sau nu mai exista reguli aplicabile), se
 * afiseaza cuvantul obtinut, apoi utilizatorul este intrebat daca mai doreste testarea
 * unui cuvant si in caz afirmativ se reia procesul;
 * <p>
 * Instructiuni de utilizare:
 * 1. Variante predefinite: algoritm cmmdc si inmultimrea a doua numere
 * 2. Pentru varianta Custom se completeaza fisierul dictionar.txt cate un caracter cu spatiu intre ele si
 *    se completeaza fisierul rules.txt fisier, care contine reguli/productii, dupa urmatoarea sintaxa:
 * - "sirInitial"->"sirFinal";
 *   Cele doua fisiere sunt in prezent sunt completate cu un algoritm simplu de traducere a oricarui cuvânt
 *   din {a,b}* în aba
 *
 * Observatii:
 * - caracterul '~' reprezinta multimea vida
 * - caracterul '&' reprezinta marcajul pentru o poductie finala
 *
 *
 * Fisierele dictionaryMultiply2Numbers.txt si rulesMultiply2Numbers.txt sunt completate cu algoritmul de inmultire a
 * două numere. Ex: aa#aaa => aaaaaa
 * Fisierele dictionaryCMMDC.txt si rulesCMMDC.txt sunt completate cu algoritmul de obtinere a ccmdc
 * două numere. Ex: aaaaaa#aaaa => aa
 *
 * @Author Gheo Coanta
 **/
package homework01;

import java.io.IOException;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        Markov markov = new Markov();

        Scanner scanner = new Scanner(System.in);
        String option;
        do {
            System.out.println("What algoritm you want to load? ");
            System.out.println("1. Multiplying for 2 numbers (eq: aa#aaa)");
            System.out.println("2. cmmdc for 2 numbers (eq aaaaaa#aaaa)");
            System.out.println("3. Custom. In this case please provide the path for the dictionary and rules files. Also please check the instructions how to set them up.");
            boolean validOption = true;

            int choice = scanner.nextInt();
            switch (choice)
            {
                case 1: {
                    markov.inputDictionary("dictionaryMultiply2Numbers.txt");
                    markov.inputRules("rulesMultiply2Numbers.txt");
                    System.out.println("Algorithm for multiplying number is loaded. Here is the data: ");
                    markov.displayVocabulary();
                    markov.displayRules();
                    break;
                }
                case 2: {
                    markov.inputDictionary("dictionaryCMMDC.txt");
                    markov.inputRules("rulesCMMDC.txt");
                    System.out.println("Algorithm for CMMDC for 2 numbers is loaded. Here is the data: ");
                    markov.displayVocabulary();
                    markov.displayRules();
                    break;
                }
                case 3: {
                    System.out.println("\nPlease provide the path to the dictionary file");
                    String dictionaryPath = scanner.next();
                    markov.inputDictionary(dictionaryPath);
                    System.out.println("\nPlease provide the path to the rules file");
                    String rulesPath = scanner.next();
                    markov.inputRules(rulesPath);
                    System.out.println("Your custom algorithm is loaded. Here is the data: ");
                    markov.displayVocabulary();
                    markov.displayRules();
                    break;
                }
                default: {
                    System.out.println("Not a valid choice. Please choose again");
                    validOption = false;
                }
            }

            if (validOption) {
                System.out.print("Please provide the text to me Markov-ed: ");
                String text = scanner.next();
                markov.markovTransformation(text);
            }
            System.out.println("\nDo you want to insert another text? ('y' to continue)");
            option = scanner.next();
        }
        while (option.equals("y"));


    }
}
