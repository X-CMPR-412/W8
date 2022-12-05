import utility.Average;
import utility.IGrader;

/*
This class is associated with IGrader and Report, perusal of both will help in
understanding; especially in regards to the use of the super keyword for the
not-really implemented IGrader methods, at bottom...

Please note the use of the @Override annotation (almost overlooked this...),
and implementation of toString...
*/

class ReportClassAverages extends Report implements IGrader {

  private int numStudents; // class size
  private Average average;

  ReportClassAverages() {
    preamble = "Here are the class averages:" + LetterGrader.NEW_LINE
                                              + LetterGrader.NEW_LINE;
    average = new Average(Score.getNumStudentScores());
  }

  void update(Score studentScores) {
    average.update (studentScores.getStudentScores());
  }

  void setNumStudents(final int NUM_STUDENTS) {
    numStudents =               NUM_STUDENTS;
  }

  protected void write() {
    System.out.println(this);
  }

  @Override
  public String toString() {
    average.complete(numStudents);

    String[] headers = {           "Q1", "Q2", "Q3", "Q4", "MidI", "MidII", "Final"};
    String[] labels  = {"Average" + SEPARATOR,
                        "Minimum" + SEPARATOR,
                        "Maximum" + SEPARATOR};

    // prophylactic - in case a label changes
    final int FIELD_WIDTH_LABEL = Math.max(labels[0].length(),
                                  Math.max(labels[1].length(),
                                           labels[2].length())) + 1;
    return
      preamble                                +
      fill                (FIELD_WIDTH_LABEL) + formatln         (headers)           +
      fillRight(labels[0], FIELD_WIDTH_LABEL) + formatln(toString(average.getAvg())) +
      fillRight(labels[1], FIELD_WIDTH_LABEL) + formatln(toString(average.getMin())) +
      fillRight(labels[2], FIELD_WIDTH_LABEL) + formatln(toString(average.getMax()));
  }

  private String[] toString(double[] numbers) {
	String[] s = new String[numbers.length];
	for (int x = 0; x < numbers.length; x++) {
	  s[x] = Double.toString(getTwoDecimalPlaces(numbers[x]));
	}
	return s;
  }

  private String[] toString(int[] numbers) {
    String[] s = new String[numbers.length];
    for (int x = 0; x < numbers.length; x++) {
      s[x] = Integer.toString(numbers[x]);
    }
    return s;
  }

  private String formatln(String[] values) {
    final int FIELD_WIDTH_NUM  = "100.00".length();
    final int FIELD_WIDTH_FILL = 4;

    String s = fillLeft(values[0], FIELD_WIDTH_NUM);
    for (int x = 1; x < values.length; x++) {
      s += fill               (FIELD_WIDTH_FILL);
      s += fillLeft(values[x], FIELD_WIDTH_NUM);
    }
    return s + LetterGrader.NEW_LINE;
  }

  private double getTwoDecimalPlaces(final double NUMBER) {
    return Math.round(NUMBER * 100) / 100.0;
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