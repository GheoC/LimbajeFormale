package homework03;

import homework02.Rule;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AutomatFinitDeterminist {

    private List<Stare> stareList = new ArrayList<>();
    private List<Character> alfabet = new ArrayList<>();


    public AutomatFinitDeterminist() {
    }

    public List<Stare> getStareList() {
        return stareList;
    }

    public List<Character> getAlfabet() {
        return alfabet;
    }

    public void readData(String filePath) throws IOException {
        File myFile = new File("./src/homework03/" + filePath);
        FileReader fileReader = new FileReader(myFile);
        BufferedReader bfReader = new BufferedReader(fileReader);

        String firstLine = bfReader.readLine();
        for (int i = 0; i < firstLine.length(); i++) {
            if (firstLine.charAt(i) != ' ') {
                alfabet.add(firstLine.charAt(i));
            }
        }

        String currentLine;
        while ((currentLine = bfReader.readLine()) != null) {
            String[] parts = currentLine.split(" ", (alfabet.size()+2));
            Stare stare = new Stare();
            stare.setNameOfStare(parts[0]);
            for (int i = 0; i < alfabet.size(); i++)
            {
                stare.addLinks(alfabet.get(i),parts[i+1]);
            }
            stare.setStatusStare(Boolean.parseBoolean(parts[alfabet.size()+1]));

            stareList.add(stare);
        }
    }

    public void displayData()
    {
        System.out.print("Alfabetul este: ");
        System.out.println(alfabet);
        stareList.forEach(Stare::displayLinks);
    }
}
