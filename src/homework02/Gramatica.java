package homework02;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Gramatica {
    private List<Character> vocabularNeterminale = new ArrayList<>();
    private List<Character> vocabularTerminale = new ArrayList<>();
    private String start;
    private List<Rule> rules = new ArrayList<>();

    public Gramatica() {
    }

    public void inputData(String filePath) throws IOException {
        vocabularNeterminale.clear();
        vocabularTerminale.clear();
        File myFile = new File("./src/homework02/" + filePath);
        FileReader fileReader = new FileReader(myFile);
        BufferedReader bfReader = new BufferedReader(fileReader);

        String firstLine = bfReader.readLine();
        for (int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) != ' ') {
                vocabularNeterminale.add(firstLine.charAt(i));
            }
        }

        String secondLine = bfReader.readLine();
        for (int i = 0; i < secondLine.length(); i++) {
            if (secondLine.charAt(i) != ' ') {
                vocabularTerminale.add(secondLine.charAt(i));
            }
        }

        start = bfReader.readLine();

        String currentLine;
        while ((currentLine = bfReader.readLine()) != null) {
            String[] parts = currentLine.split("->", 2);
            Rule rule = new Rule(parts[0], parts[1]);
            rules.add(rule);
        }
    }

    public void displayData() {
        System.out.print("Vocabularul Neterminalelor: ");
        System.out.println(vocabularNeterminale);
        System.out.print("Vocaularul Terminalelor: ");
        System.out.println(vocabularTerminale);
        System.out.print("Start: ");
        System.out.println(start);

        for (int i = 0; i < rules.size(); i++) {
            System.out.println("Regula numarul " + (i + 1) + " este: " + rules.get(i));
        }
    }

    public List<Character> getVocabularNeterminale() {
        return vocabularNeterminale;
    }

    public void setVocabularNeterminale(List<Character> vocabularNeterminale) {
        this.vocabularNeterminale = vocabularNeterminale;
    }

    public List<Character> getVocabularTerminale() {
        return vocabularTerminale;
    }

    public void setVocabularTerminale(List<Character> vocabularTerminale) {
        this.vocabularTerminale = vocabularTerminale;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public boolean checkData() {
        boolean validation = true;

        //test case 1
        if (checkIntersection()) {
            System.out.println("Test case (1): VN intersectat cu VT = multimea vida. TEST PASSED");
        } else {
            validation = false;
            System.out.println("Test case (1): VN intersectat cu VT = multimea vida. TEST FAILED");
        }

        //test case 2
        if (checkStartApartineNeterminale()) {
            System.out.println("Test case (2): S apartine Neterminalelor. TEST PASSED");
        } else {
            validation = false;
            System.out.println("Test case (2): S apartine Neterminalelor. TEST FAILED");
        }

        //test case 3
        if (checkMembruStangContineNeterminal()) {
            System.out.println("Test case (3): Pentru fiecare regulă membrul stâng conține cel puțin un neterminal. TEST PASSED");
        } else {
            validation = false;
            System.out.println("Test case (3): Pentru fiecare regulă membrul stâng conține cel puțin un neterminal. TEST FAILED");
        }

        //test case 4
        if (checkForSingleStartInNeterminale()) {
            System.out.println("Test case (4): Există cel puțin o producție care are în stânga doar S. TEST PASSED");
        } else {
            System.out.println("Test case (4): Există cel puțin o producție care are în stânga doar S. TEST FAILED");
            validation = false;
        }

        //test case 5
        if (checkElementsOfRules()) {
            System.out.println("Test case (5): Fiecare producție conține doar elemente din VN și VT. TEST PASSED");
        } else {
            System.out.println("Test case (5): Fiecare producție conține doar elemente din VN și VT. TEST FAILED");
            validation = false;
        }
        return validation;
    }

    //returns true daca intersectia VN cu VT este vida
    private boolean checkIntersection() {
        return vocabularNeterminale.stream()
                .filter(vocabularTerminale::contains)
                .collect(Collectors.toList()).isEmpty();
    }

    //return true daca S apartine VN
    private boolean checkStartApartineNeterminale() {

        return vocabularNeterminale.contains(start.charAt(0));
    }

    //returns true daca pentru fiecare regula, membrul stâng conține cel puțin un neterminal
    private boolean checkMembruStangContineNeterminal() {
        for (int i = 0; i < rules.size(); i++) {

            //se creaza o lista cu caracterele regulii
            List<Character> caractereRegula = new ArrayList<>();
            for (int j = 0; j < rules.get(i).getInput().length(); j++) {
                caractereRegula.add(rules.get(i).getInput().charAt(j));
            }
            //se intersecteaza cu multimea Neterminalelor
            List<Character> intersectieRegulaCuNeterminale = caractereRegula.stream()
                    .filter(vocabularNeterminale::contains)
                    .collect(Collectors.toList());

            //daca intersectia de mai sus este vida, atunci s-a gasit o regula ce nu contine nici o Neterminala
            // no bueno
            if (intersectieRegulaCuNeterminale.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    //returns true daca există cel puțin o producție care are în stânga doar S (start)
    private boolean checkForSingleStartInNeterminale() {
        for (int i = 0; i < rules.size(); i++) {
            if (rules.get(i).getInput().equals(start)) {
                return true;
            }
        }
        return false;
    }

    //returs false daca exista cel putin un caracter in productii care nu apartine vocabularului total
    private boolean checkElementsOfRules() {
        List<Character> vocabularTotal = new ArrayList<>();
        vocabularTotal.addAll(vocabularNeterminale);
        vocabularTotal.addAll(vocabularTerminale);

        for (int i = 0; i < rules.size(); i++) {
            for (int j = 0; j < rules.get(i).getInput().length(); j++) {
                if (!vocabularTotal.contains(rules.get(i).getInput().charAt(j))) {
                    return false;
                }
            }
            for (int k = 0; k < rules.get(i).getResult().length(); k++) {
                if (!vocabularTotal.contains(rules.get(i).getResult().charAt(k))) {
                    return false;
                }
            }
        }
        return true;
    }


    public String generateWord() {

        List<Rule> identicalInputRules = getIdenticalInputRules(start);



        Collections.shuffle(identicalInputRules);
        String text = identicalInputRules.get(0).getResult();
        System.out.print("Prima regula gasita in text este: ("+start+"->"+text+")");
        System.out.println(" => textul intermediar: "+ text);
        boolean noRulesFoundInText = false;
        int stepNumber = 1;
        while (noRulesFoundInText != true) {
            noRulesFoundInText = true;
            for (int i = 0; i < rules.size(); i++) {
                if (text.contains(rules.get(i).getInput())) {
                    identicalInputRules.clear();
                    identicalInputRules = getIdenticalInputRules(rules.get(i).getInput());
                    Collections.shuffle(identicalInputRules);
                    text = text.replaceFirst(identicalInputRules.get(0).getInput(), identicalInputRules.get(0).getResult());
                    System.out.print("Prima regula gasita in text este: ("+identicalInputRules.get(0).getInput()+"->"+identicalInputRules.get(0).getResult()+")");
                    System.out.println(" => textul intermediar: "+ text);

                    noRulesFoundInText = false;
                    break;
                }
            }

        }
        return text;
    }

    public boolean checkWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!vocabularTerminale.contains(word.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public List<Rule> getIdenticalInputRules(String rule) {
        List<Rule> identicalInputRules = new ArrayList<>();
        for (int i = 0; i < rules.size(); i++) {
            if (rules.get(i).getInput().equals(rule)) {
                identicalInputRules.add(rules.get(i));
            }
        }
        return identicalInputRules;
    }

}
