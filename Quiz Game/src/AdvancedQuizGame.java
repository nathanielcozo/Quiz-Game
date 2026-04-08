import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdvancedQuizGame {

    private static ArrayList<String> quizChoices = new ArrayList<>();
    private static File projectDir = new File(".");
    private static File[] allFiles = projectDir.listFiles();

    private static ArrayList<String> questions = new ArrayList<>();
    private static ArrayList<ArrayList<String>> choices = new ArrayList<>();
    private static ArrayList<Character> answers = new ArrayList<>();
    static int count = 0;

    private static int quiz;
    private static String chosenQuiz;

    private static int score = 0;
    private static char guess;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner write = new Scanner(System.in);

        getQuizzes();
        if(quizChoices.isEmpty()){
            System.out.println("No quizzes available!");
            return;
        }

        System.out.println("------------------------------------------");
        System.out.println("Which quiz would you like to take?");
        for(int i = 0; i < quizChoices.size(); i++){
            String text = quizChoices.get(i);
            System.out.println((i + 1) + ". " + text.substring(0, text.indexOf(".")));
        }
        System.out.print("Enter choice: ");

        try{
            quiz = write.nextInt();
            if(quiz > quizChoices.size() || quiz <= 0){
                System.out.println("Invalid option.");
                return;
            }
            chosenQuiz = quizChoices.get(quiz - 1);
        }catch(InputMismatchException e){
            System.out.println("Invalid option.");
            write.nextLine();
            return;
        }

        Scanner scanner = new Scanner(new File(chosenQuiz));
        while(scanner.hasNextLine()){
            String[] qLine = scanner.nextLine().split("\\|");
            questions.add(qLine[0]);
            choices.add(new ArrayList<String>());
            choices.get(count).add(qLine[1]);
            choices.get(count).add(qLine[2]);
            choices.get(count).add(qLine[3]);
            choices.get(count).add(qLine[4]);
            answers.add(qLine[5].charAt(0));
            count++;
        }
        scanner.close();

        System.out.println("------------------------------------------");
        System.out.println("Welcome to the " + chosenQuiz.substring(0, chosenQuiz.indexOf(".")) + " quiz!");
        System.out.println("This quiz has a total of " + questions.size() + " items.");
        System.out.println("------------------------------------------");

        for(int r = 0; r < questions.size(); r++){
            System.out.print(r + 1 + ".) ");
            System.out.println(questions.get(r));
            for(int c = 0; c < choices.get(r).size(); c++){
                System.out.println(choices.get(r).get(c));
            }
            System.out.print("Enter your answer: ");
            guess = write.next().toUpperCase().charAt(0);
            if(guess == answers.get(r)){
                System.out.println("Correct answer! The answer is " + answers.get(r));
                score++;
            }
            else{
                System.out.println("Wrong answer! The correct answer is " + answers.get(r));
            }
            System.out.println("------------------------------------------");
        }

        System.out.printf("Score: %d/%d%n%n", score, questions.size());
        System.out.println("Would you like to view the answer key? (Y/N)");
        char yn = write.next().toUpperCase().charAt(0);
        if(yn == 'Y'){
            viewAnswerKey();
        }
        else{
            return;
        }

        write.close();
    }

    public static void getQuizzes() {
        if(allFiles != null){
            for(File file : allFiles){
                if(file.isFile() && file.getName().endsWith(".txt")){
                    quizChoices.add(file.getName());
                }
            }
        }
    }

    public static void viewAnswerKey(){
        for(int r = 0; r < questions.size(); r++){
            String c = "";
            switch(answers.get(r)){
                case 'A' -> c = choices.get(r).get(0);
                case 'B' -> c = choices.get(r).get(1);
                case 'C' -> c = choices.get(r).get(2);
                case 'D' -> c = choices.get(r).get(3);
            }
            System.out.println("Item " + (r + 1) + ".");
            System.out.println("Q: " + questions.get(r));
            System.out.println("A: " + c);
            System.out.println("------------------------------------------");
        }
    }
}
