import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {

        System.out.println("------------Student Grade Calculator--------------");
        System.out.println("--------------------------------------------------");

        //Input no.of subjects
        Scanner sc = new Scanner(System.in);
        System.out.print("Input No. of Subjects: ");

        int noofSub = sc.nextInt();
        int sum = 0;
        char grade;

        // input marks for each subject
        for (int i = 1; i <= noofSub; i++){
            Scanner sub = new Scanner(System.in);
            System.out.print("Input Subject " + i + " marks: ");
            int marks = sub.nextInt();
            sum = marks + sum;
        }

        //Calculating average
        double average = sum / noofSub;


        //Calculating Grade
        if (average >= 75){
            grade = 'A';
        }
        else if (average >= 65){
            grade = 'B';
        }
        else if (average >= 50){
            grade = 'C';
        }
        else{
            grade = 'F';
        }

        //Display info
        System.out.println("----------Your Status:----------");
        System.out.println("Your Total marks: " + sum);
        System.out.println("Your Average Percentage: " + average +"%");
        System.out.println("Your Grade: " + grade);


    }

}
