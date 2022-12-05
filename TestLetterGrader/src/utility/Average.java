/*
This is actually a very modest "statistics package" masquerading as a very simple class
that in reality is really quite clever; however, "statistics package" is a bit too
immodest and is quite long to type out (unlike this commentary...).

Here's the thing: the code is very general! Sort of like the Math package... As such,
it definitely belongs to the utility package!

What does it do?

1. Input:  consists of an array of indeterminate length of type int, containing scores
           for a particular student.

2. Output: three arrays, one double, two int, for the average, minimum & maximum numbers
           or scores, for all of the students passed in.
*/

package utility;

public class Average {
  private double[] avg;
  private int   [] min;
  private int   [] max;

  public Average(final int MEASURES) {
    avg = new double      [MEASURES];
    min = new int         [MEASURES];
    max = new int         [MEASURES];

    for (int measure = 0; measure < MEASURES; measure++) {
      min[measure] = Integer.MAX_VALUE;
    }
  }

  public double[] getAvg() {return avg;}
  public int   [] getMin() {return min;}
  public int   [] getMax() {return max;}

  public void update(int[] measures) {
    for (int measure = 0; measure < measures.length; measure++) {
      int measurement = measures[measure];
                                       avg[measure] += measurement;
      if (min[measure] > measurement) {min[measure]  = measurement;}
      if (max[measure] < measurement) {max[measure]  = measurement;}
    }
  }

  public void complete(final int POPULATION_SIZE) {
    for (int measure = 0; measure < avg.length; measure++) {
      avg[measure] /= POPULATION_SIZE;
    }
  }
}