/*
An instantiation of class Score holds the information from one input file line.

Pains were made to, somewhat, generalize the logic in case slight modifications, such
as addition or subtraction of graded scores, are made in future.
*/

class Score {
  private final static String DELIMITER = ",";

  private final static int NUM_QUIZZES  = 4;
  private final static int NUM_MIDTERMS = 2;

  private final static int NAME_INDEX          =                  0;
  private final static int QUIZ_INDEX          =     NAME_INDEX + 1;
  private final static int MID_TERM_INDEX      =     QUIZ_INDEX + NUM_QUIZZES;
  private final static int FINAL_PROJECT_INDEX = MID_TERM_INDEX + NUM_MIDTERMS;

  private final static int NUM_STUDENT_SCORES  = FINAL_PROJECT_INDEX;

  private int[] studentScores = new int[NUM_STUDENT_SCORES];

  private String name;
  private int[]  quiz    = new int[NUM_QUIZZES];
  private int[]  midTerm = new int[NUM_MIDTERMS];
  private int    finalProject;
  private double finalScore;

  Score(final String LINE) {
    String[] lineSplit = LINE.split(DELIMITER);
    name =   lineSplit[0].trim();

    int score = 0;
    int x     = 0;
    for (int source  = QUIZ_INDEX, target = 0;
             source <= quiz.length; 
             source++,             target++) {

      score = Integer.parseInt(lineSplit[source].trim());
      quiz  [target] = score;
      studentScores[x++] = score;
    }
    for (int source = MID_TERM_INDEX,  target = 0;
             source < MID_TERM_INDEX + midTerm.length;
             source++,                 target++) {

      score = Integer.parseInt(lineSplit[source].trim());
      midTerm[target] = score;
      studentScores [x++] = score;
    }
    score = Integer.parseInt(lineSplit[lineSplit.length - 1].trim());
    finalProject = score;
    studentScores[x] = score;
  }

  String     getName            () {return name;}
  int[]      getStudentScores   () {return studentScores;}
  static int getNumStudentScores() {return NUM_STUDENT_SCORES;}

  double getFinalScore() {
    finalScore = 0.0;
    for (int x = 0; x < quiz.length; x++) {
      finalScore += quiz[x] * getFactorQuiz();
    }
    for (int x = 0; x < midTerm.length; x++) {
      finalScore += midTerm[x] * getFactorMidTerm(x);
    }
    return finalScore + finalProject * getFactorFinalProject();
  }

  private double getFactorQuiz        ()            {return                 0.10;}
  private double getFactorMidTerm     (final int X) {return X == 0 ? 0.20 : 0.15;}
  private double getFactorFinalProject()            {return                 0.25;}
}