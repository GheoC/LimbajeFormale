package homework02;

public class Rule
{
    private String input;
    private String result;

    public Rule() {
    }

    public Rule(String input, String result) {
        this.input = input;
        this.result = result;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return input + " -> " + result;

    }
}
