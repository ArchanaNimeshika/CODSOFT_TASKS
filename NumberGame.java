import java.util.Random;
import java.util.Scanner;


// Task 01-----------------------------------

public class NumberGame {
    public static void main(String[] args) {

        System.out.println("-----* The number game* -----");
        System.out.println("-----* You have only 5 attempts! -----");

        int score = 0;

        for (int i = 1; i <= 5; i++) {

            Scanner sc = new Scanner(System.in);
            System.out.print("Enter any number between 1 - 5: ");
            int userNum = sc.nextInt();


            Random random = new Random();

            int num = random.nextInt(1, 6);

            if (num == userNum) {
                System.out.println("Yes!! Your guess is correct!");
                score = score + 10;
            } else if (num > userNum){
                System.out.println("Sorry! the number is too low! ");
            }
            else if (num < userNum){
                System.out.println("Sorry! the number is too high! ");
            }
            else{
                System.out.println("Your input is out of range!");
            }
        }

        System.out.println("------------Game Over!!!--------------");
        System.out.println("Your Score is: " + score );

    }

}
