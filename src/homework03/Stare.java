package homework03;

import java.util.HashMap;
import java.util.Map;

public class Stare {
    private String nameOfStare;
    private Map<Character, String> links = new HashMap<Character, String>();
    private boolean statusStare;

    public String getNameOfStare() {
        return nameOfStare;
    }

    public void setNameOfStare(String nameOfStare) {
        this.nameOfStare = nameOfStare;
    }

    public Map<Character, String> getLinks() {
        return links;
    }

    public void addLinks(Character simbolIntrare, String nextStare) {
        links.put(simbolIntrare, nextStare);
    }

    public void displayLinks()
    {
        System.out.print("pentru starea " + nameOfStare+" avem urmatoarele tranzitii: ");
        System.out.print(links);
        System.out.println(" Stare finala? " + statusStare);
    }

    public boolean isStatusStare() {
        return statusStare;
    }

    public void setStatusStare(boolean statusStare) {
        this.statusStare = statusStare;
    }


}
