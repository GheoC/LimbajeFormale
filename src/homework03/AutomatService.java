package homework03;

public class AutomatService {
    private String start;
    private AutomatFinitDeterminist afd;
    private Stare currentState;


    public AutomatService(String start, AutomatFinitDeterminist afd) {
        this.start = start;
        this.afd = afd;

        currentState = findState(start);
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public AutomatFinitDeterminist getAfd() {
        return afd;
    }

    public void setAfd(AutomatFinitDeterminist afd) {
        this.afd = afd;
    }

    public Stare getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Stare currentState) {
        this.currentState = currentState;
    }

    private Stare findState(String stringState)
    {
        for (int i = 0; i < afd.getStareList().size(); i++) {
            if (afd.getStareList().get(i).getNameOfStare().equals(stringState)) {
                return afd.getStareList().get(i);
            }
        }
        return null;
    }

    public boolean checkWord(String word) {
        currentState = findState(start);
        Character currentTransition;
        String tranzitie;
        System.out.println("\nVerificam cuvantul: " + word);
        for (int i = 0; i < word.length(); i++) {
            currentTransition = word.charAt(i);

            if (!afd.getAlfabet().contains(currentTransition)) {
                System.out.println("Blocaj! Litera " + currentTransition + " nu apartine alfabetului AFD-ului");
                return false;
            }
            System.out.print(currentState.getNameOfStare() + " pe tranzatia " + currentTransition + " -> ");

            tranzitie = currentState.getLinks().get(currentTransition);
            setCurrentState(findState(tranzitie));

            if (currentState == null) {
                System.out.println("Blocaj! Lispa tranzitie");
                return false;
            }

            System.out.println(currentState.getNameOfStare());

        }

        if (currentState.isStatusStare()) {
            System.out.println("Cuvantul " + word + " este acceptat de automat");
            return true;
        } else {
            System.out.println("Cuvantul " + word + " nu este acceptat de automat;");
            return false;
        }
    }
}
