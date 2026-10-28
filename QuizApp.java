
import javax.swing.*;
import java.awt.*;

public class QuizApp extends JFrame {

    //Store Questions
    private String[] questions = {
            "Which language is used for Android development?",
            "Which keyword is used to inherit a class in Java?",
            "Which method is the entry point of a Java program?",
            "Which data type stores true or false?",
            "Which keyword creates an object in Java?"
    };

    //Store Answers
    private String[][] options = {
            {"Python", "Java", "HTML", "SQL"},
            {"implements", "inherits", "extends", "import"},
            {"start()", "run()", "main()", "execute()"},
            {"int", "String", "boolean", "char"},
            {"class", "new", "this", "void"}
    };

    //Store Correct answers
    private int[] correctAnswers = {1, 2, 2, 2, 1};

    //Store user's answers
    private String[] userAnswers = new String[questions.length];

    private int currentQuestion = 0;
    private int score = 0;
    private int timeLeft = 15;

    private JLabel questionLabel;
    private JLabel timerLabel;
    private JRadioButton[] radioButtons;
    private ButtonGroup buttonGroup;
    private JButton submitButton;
    private Timer timer;

    //QuizApp interface creation
    public QuizApp() {
        setTitle("Java Quiz Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        questionLabel = new JLabel("", SwingConstants.CENTER);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        timerLabel = new JLabel("Time: 15 seconds",
                SwingConstants.CENTER);

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(questionLabel);
        topPanel.add(timerLabel);
        add(topPanel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        radioButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();

        for (int i = 0; i < 4; i++) {
            radioButtons[i] = new JRadioButton();
            buttonGroup.add(radioButtons[i]);
            optionsPanel.add(radioButtons[i]);
        }

        add(optionsPanel, BorderLayout.CENTER);

        submitButton = new JButton("Submit Answer");
        add(submitButton, BorderLayout.SOUTH);

        submitButton.addActionListener(e -> submitAnswer());

        displayQuestion();
        setVisible(true);
    }

    //method to display question
    private void displayQuestion() {
        questionLabel.setText(
                "Question " + (currentQuestion + 1) + ": "
                        + questions[currentQuestion]
        );

        buttonGroup.clearSelection();

        for (int i = 0; i < 4; i++) {
            radioButtons[i].setText(options[currentQuestion][i]);
        }

        timeLeft = 15;
        timerLabel.setText("Time: " + timeLeft + " seconds");

        if (timer != null) {
            timer.stop();
        }

        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft + " seconds");

            if (timeLeft <= 0) {
                timer.stop();
                submitAnswer();
            }
        });

        timer.start();
    }

    //method for submitting answer
    private void submitAnswer() {
        if (timer != null) {
            timer.stop();
        }

        int selectedAnswer = -1;

        for (int i = 0; i < 4; i++) {
            if (radioButtons[i].isSelected()) {
                selectedAnswer = i;
                break;
            }
        }

        if (selectedAnswer != -1) {
            userAnswers[currentQuestion] =
                    options[currentQuestion][selectedAnswer];

            if (selectedAnswer == correctAnswers[currentQuestion]) {
                score++;
            }
        } else {
            userAnswers[currentQuestion] = "Not answered";
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {
            displayQuestion();
        } else {
            showResult();
        }
    }

    //method for display result
    private void showResult() {
        StringBuilder result = new StringBuilder();

        result.append("Your Score: ")
                .append(score)
                .append("/")
                .append(questions.length)
                .append("\n\n");

        for (int i = 0; i < questions.length; i++) {
            result.append("Q").append(i + 1).append(": ")
                    .append(questions[i]).append("\n");

            result.append("Your answer: ")
                    .append(userAnswers[i]).append("\n");

            result.append("Correct answer: ")
                    .append(options[i][correctAnswers[i]])
                    .append("\n\n");
        }

        JOptionPane.showMessageDialog(
                this,
                result.toString(),
                "Quiz Results",
                JOptionPane.INFORMATION_MESSAGE
        );

        System.exit(0);
    }

    //execute the quiz app
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp());
    }
}

