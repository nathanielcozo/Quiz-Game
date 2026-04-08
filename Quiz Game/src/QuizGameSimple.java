import java.util.Scanner;

public class QuizGameSimple {
    public static void main(String[] args){
        String[] questions = {"What does kVp control in an x-ray machine?",
                              "What is the only metal that is liquid at room temperature?",
                              "Who painted the Mona Lisa?",
                              "What is the largest ocean on Earth?",
                              "Which language has the longest alphabet in the world?"};

        String[][] choices = {{"1. New Zealand", "2. Australia", "3. Iceland", "4. Madagascar"},
                              {"1. Iron", "2. Copper", "3. Mercury", "4. Aluminum"},
                              {"1. Leonardo da Vinci", "2. Vincent van Gogh", "3. Michelangelo", "4. Claude Monet"},
                              {"1. Atlantic Ocean", "2. Indian Ocean", "3. Arctic Ocean", "4. Pacific Ocean"},
                              {"1. Russian", "2. Khmer", "3. English", "4. Mandarin"}};

        int[] answers = {2, 3, 1, 4, 2};
        int score = 0;
        int guess;

        Scanner scanner = new Scanner(System.in);

        System.out.println("------------");
        System.out.println("Trivia Quiz!");
        System.out.println("------------");

        for(int r = 0; r < choices.length; r++){
            System.out.println(questions[r]);
            for(int c = 0; c < choices[r].length; c++){
                System.out.println(choices[r][c]);
            }
            System.out.print("Enter answer: ");
            guess = scanner.nextInt();
            if(guess == answers[r]){
                System.out.println("Correct answer!");
                score++;
            }
            else{
                System.out.println("Wrong answer!");
            }
            System.out.println("------------");
        }

        System.out.printf("Score: %d/%d", score, questions.length);

        scanner.close();
    }
}
