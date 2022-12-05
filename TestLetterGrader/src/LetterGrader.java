import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
The file and console I/O was simplified as much as possible in order to better focus on
what seemed to be higher priorities. Yes, buffered I/O might be faster.

Similarly, the input was read into an array of size 2, yes, 2 - to ensure that the
abysmally inefficient logic that increases class size can safely accommodate even the
most abusive institutions of higher learning...

A better idea would be to abandon the array idea, static or dynamic, and use a
Binary Search Tree...

Logic updating the class averages report is invoked from within the loop that reads the
input file. Seemed like a good idea; otherwise, more passes over the input are needed.

The student letter grades report has an awkward dependency: the actual number of
students must be set before that report is written...
*/

class LetterGrader {
  private Score[] classScores;
  private ReportClassAverages reportClassAverages;

          final static String NEW_LINE = System.getProperty("line.separator");
  private final        String INPUT_FILE;
  private final        String OUTPUT_FILE;

  private int numStudents;              // Current number of students enrolled.
  private int numStudentsEstimated = 2; // Initial estimated class size capacity;
                                        // deliberately set to absurdly low number to
                                        // ensure usage of the abysmally inefficient
                                        // logic that increases class size...
  LetterGrader(final String  INPUT_FILE,
               final String OUTPUT_FILE) {
    this. INPUT_FILE =       INPUT_FILE;
    this.OUTPUT_FILE =      OUTPUT_FILE;

    classScores = new Score[numStudentsEstimated];
  }

  void read() {
    try {
      Scanner     scanner = new Scanner            (new File(INPUT_FILE));
      reportClassAverages = new ReportClassAverages();

      while  (scanner.hasNextLine()) {
        Score studentScores = new
        Score(scanner. nextLine());
        if (numStudents == numStudentsEstimated) { // don't crash!
          increaseClassSize();                     // ...just build another classroom!
        }
        classScores[numStudents++] = studentScores;
        reportClassAverages.update  (studentScores);
      }
      scanner.close();
    }
    // start with the most specific exception - ending with the most general
    catch (final FileNotFoundException e) {
      System.out.println("Input File \"" + INPUT_FILE + "\" not found" + NEW_LINE);
      System.exit(-1);
    }
    catch (final Exception e) {
      System.out.println(e);
      System.exit(-1);
    }
  }

  void report() {
    ReportStudentLetterGrades
    reportStudentLetterGrades = new
    ReportStudentLetterGrades      (numStudents, classScores, INPUT_FILE, OUTPUT_FILE);
    reportStudentLetterGrades.write();

    reportClassAverages.setNumStudents(numStudents);
    reportClassAverages.write         ();
  }

  /*
  Logic to be invoked when the class size is exceeded due to too many students.

  What to do? Well
  - Build another classroom, twice the size of the old one.
  - Move the currently enrolled students into the new building.

  New students to be enrolled will be sent to the new building.

  Problem is, what happens to the old building?

  Does the Java GC reference counting logic guarantee that it will eventually be
  recycled? Or, do we have proliferating old classrooms blighting the landscape?
  Am not completely sure! How to find out? More research: reading, experimenting,
  looking at the Java interpreter...
  */
  private void increaseClassSize() {
    int newNumStudentsEstimated = numStudentsEstimated * 2; // double possible class
    /*
    // test code

    System.out.println("   numStudentsEstimated: " +    numStudentsEstimated);
    System.out.println("newNumStudentsEstimated: " + newNumStudentsEstimated);
    */
    Score[] newClassScores = new Score[newNumStudentsEstimated];
    for (int x = 0; x < numStudentsEstimated; x++) {
      newClassScores[x] = classScores[x];
    }
    classScores = newClassScores;
    numStudentsEstimated = newNumStudentsEstimated;
  }
}