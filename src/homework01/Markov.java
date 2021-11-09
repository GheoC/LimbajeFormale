package homework01;

import java.io.*;
import java.util.*;

public class Markov {
    private Set<Character> dictionary = new HashSet<>();
    private List<Rule> rules = new ArrayList<>();
    private String text;
    private final String finalRule = "&";

    public Markov() {
    }

    public Markov(Set<Character> dictionary, List<Rule> rules) {
        this.dictionary = dictionary;
        this.rules = rules;
    }

    public void inputDictionary(String path) throws IOException
    {
       dictionary.clear();
        File myFile = new File("./src/homework01/"+path);
        FileReader fileReader = new FileReader(myFile);
        BufferedReader bfReader = new BufferedReader(fileReader);

        int i;
        while ((i = bfReader.read()) != -1) {
            if ((char) i != ' ') {
                dictionary.add((char) i);
            }
        }
    }

    public Set<Character> getDictionary() {
        return this.dictionary;
    }

    public void displayVocabulary()
    {
        System.out.print("\nthe Vocabulary is: {");
        Iterator<Character> characterIterator = dictionary.iterator();
        while (characterIterator.hasNext())
        {
            System.out.print(characterIterator.next()+" ");
        }
        System.out.print("}\n");
    }


    public void inputRules(String path) throws IOException {

        rules.clear();
        File myRulesFile = new File("./src/homework01/"+path);
        FileReader fileReader = new FileReader(myRulesFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String currentLine;

        while ((currentLine = bufferedReader.readLine()) != null) {
            String[] parts = currentLine.split("->", 2);
            Rule rule = new Rule(parts[0], parts[1]);
            rules.add(rule);
        }
    }

    public List<Rule> getRules() {
        return rules;
    }

    public void displayRules() {
        for (int i = 0; i < rules.size(); i++)
        {
            System.out.print("Rule number " + (i + 1) + ": '" + rules.get(i).getInput() + "'");
            System.out.println(" -> '" + rules.get(i).getResult() + "'");
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean checkTextInDictionary() {
        for (int i = 0; i < text.length(); i++) {
            if (!dictionary.contains(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String runRulesOnText() {
        boolean noRulesFoundInText = false;
        boolean finalRuleEncountered = false;
        int stepNumber = 1;
        while (noRulesFoundInText != true && finalRuleEncountered != true) {
            noRulesFoundInText = true;
            for (int i = 0; i < rules.size(); i++) {
                if (text.contains(rules.get(i).getInput())) {
                    System.out.println("\nStep: " + stepNumber);
                    System.out.print("Applying rule number " + (i + 1) + " (" + rules.get(i).toString() + ") to: ");
                    System.out.print(text.replaceAll("~|&", ""));
                    String newText = text.replaceFirst(rules.get(i).getInput(), rules.get(i).getResult());
                    setText(newText);
                    stepNumber++;
                    System.out.println("   ===>   " + text.replaceAll("~|&", ""));
                    if (newText.contains(this.finalRule)) {
                        finalRuleEncountered = true;
                    }
                    noRulesFoundInText = false;
                    break;
                }
            }

        }
        setText(text.replaceAll("&", ""));
        setText(text.replaceAll("~", ""));
        return text;
    }

    public void markovTransformation(String text) throws IOException
    {
        setText(text);
        if (!checkTextInDictionary())
        {
            System.out.println("\nText contains different characters than the dictionary");
            return;
        }
        runRulesOnText();
    }
}
