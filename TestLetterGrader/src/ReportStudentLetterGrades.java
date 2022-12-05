import java.io.PrintWriter;
import utility.IGrader;

/*
This class is associated with IGrader and Report, perusal of both will help in
understanding; especially in regards to the use of the super keyword for the
not-really implemented IGrader methods, at bottom...

Please note the use of the @Override annotation (almost overlooked this...),
and implementation of toString...

Candidate for the Data Structures and Algorithm Hall of Shame: the Bubble Sort...

Oh, yeah, about that: little design flaw here; see, encapsulation is grossly violated
here because the sort alters a data structure originating outside both the class and
inheritance chain...
*/

public class ReportStudentLetterGrades extends Report implements IGrader {
  private final int NUM_STUDENTS;
  private Score[]   classScores;

  private final String  INPUT_FILE;
  private final String OUTPUT_FILE;

  ReportStudentLetterGrades(final int   NUM_STUDENTS,
                            Score[]      classScores,
                            final String  INPUT_FILE,
                            final String OUTPUT_FILE) {

    this.NUM_STUDENTS = NUM_STUDENTS;
    this. classScores =  classScores;
    this.  INPUT_FILE =   INPUT_FILE;
    this. OUTPUT_FILE =  OUTPUT_FILE;

    preamble = "Letter grade for "       + NUM_STUDENTS          + FILL_CHAR +
               "students given in file " + INPUT_FILE            + SEPARATOR +
                                           LetterGrader.NEW_LINE +
                                           LetterGrader.NEW_LINE;
    sort();
  }

  @Override
  public String toString() {
    String studentLetterGradeReport = preamble;

    for (int student = 0; student < NUM_STUDENTS; student++) {
      Score studentScores = classScores[student];

      final String             NAME = studentScores.getName() + SEPARATOR + FILL_CHAR;
      final int    FIELD_WIDTH_NAME = 32                      + 1         + 1;

      studentLetterGradeReport += fillRight     (NAME, FIELD_WIDTH_NAME);
      studentLetterGradeReport += getLetterGrade(studentScores.getFinalScore());
      studentLetterGradeReport +=    LetterGrader.NEW_LINE;
    } 
    return studentLetterGradeReport;
  }

  protected void write() {
    try {
      PrintWriter printWriter = new PrintWriter(OUTPUT_FILE);
      printWriter.write(toString()); // could not replace 'toString()' with 'this'
      printWriter.close();
    }
    catch (final Exception e) {
      System.out.println  (e);
      System.exit(-1);
    }
    System.out.println(
      "Letter grade has been calculated for students"     + LetterGrader.NEW_LINE +
      "that are listed in the input file "  +  INPUT_FILE + LetterGrader.NEW_LINE +
      "and are written to the output file " + OUTPUT_FILE + LetterGrader.NEW_LINE
    );
  }

  private char getLetterGrade(final double FINAL_SCORE) {
    char                         letterGrade = '?';
         if (FINAL_SCORE >= 90) {letterGrade = 'A';}
    else if (FINAL_SCORE >= 80) {letterGrade = 'B';}
    else if (FINAL_SCORE >= 70) {letterGrade = 'C';}
    else if (FINAL_SCORE >= 60) {letterGrade = 'D';}
    else                        {letterGrade = 'F';}
    return                       letterGrade;
  }

  private void sort() { // Bubble Sort
    for (int x = NUM_STUDENTS; x > 0; x--) {
      for (int y = 0; y < x - 1; y++) {
        if    (classScores[y]  .getName().compareTo
              (classScores[y+1].getName())          > 0) {
          swap(y);
        }
      }
    }
  }

  private void swap(final int X) {
    Score tmp        = classScores[X];
    classScores[X]   = classScores[X+1];
    classScores[X+1] = tmp;
  }

  @Override
  public  String fill(final int AMOUNT) {
    return super.fill(          AMOUNT);
  }
  @Override
  public  String fillLeft(final String VALUE, final int FIELD_WIDTH) {
    return super.fillLeft(             VALUE,           FIELD_WIDTH);
  }
  @Override
  public  String fillRight(final String VALUE, final int FIELD_WIDTH) {
    return super.fillRight(             VALUE,           FIELD_WIDTH);
  }
}