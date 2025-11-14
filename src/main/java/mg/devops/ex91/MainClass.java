package mg.devops.ex91;

public class MainClass {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Main \"your text here\"");
            return;
        }
        String text = args[0];
        int vowelCount = VowelCounter.countVowels(text);
        System.out.println("Input: " + text);
        System.out.println("Number of vowels: " + vowelCount);
    }
}