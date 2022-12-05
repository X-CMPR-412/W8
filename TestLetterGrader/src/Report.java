/*
Not sure things were done properly here.

This abstract class factors out methods and instance variables & constants common to
both reports, of which, there are only a few...

Would prefer to have the constants in the IGrader.java interface as they are more
closely associated with formatting than reporting; however, attempts to do so failed
and time marches on...

Report does something else that may be either terribly awful and/or inspired:
it implements the formatting methods in IGrader.java; however, it does not use the
keyword implements or otherwise refer to IGrader! Instead, the classes for each of the
two reports use the keyword implements for IGrader and then "implements" them by using
the super keyword to refer to their actual implementations in this class!

Why?

Well, a separate formatting class could be used - at the expense of encapsulation.
Or, both report classes could have duplicated the code - which seemed awful; however,
the code blocks in both classes referring to these methods are almost as awful...

Looking forward to, someday, learning if there's a better way....

Point of confusion: found that the abstract keywords, below, are needed; was under the
impression they should not be... Perhaps there's a fundamental misunderstanding?
*/

abstract class Report {
  String preamble;

  final char SEPARATOR = ':';
  final char FILL_CHAR = ' ';

  abstract void write();

  String fillLeft(final String VALUE, final int FIELD_WIDTH) {
    return fill(FIELD_WIDTH - VALUE.length()) + VALUE;
  }

  String fillRight(final String VALUE, final int FIELD_WIDTH) {
    return VALUE + fill(FIELD_WIDTH - VALUE.length());
  }

  String fill(final int AMOUNT) {
    String s = "";
    for (int x = 0; x < AMOUNT; x++) {
      s += FILL_CHAR;
    }
    return s;
  }
}