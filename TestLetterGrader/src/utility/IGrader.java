package utility;

/*
This interface is for String formatting methods used by both reports.

Well, almost - fillLeft is only used for one of the reports; however, it looked so
fetching alongside its pal, fillRight that it didn't seem right to have it left out...
Okay, okay, I confess: couldn't resist the word-play... Besides the logic of both are,
literally, bilaterally symmetric...

String formatting is such a general utility (yes, in package of same name...) that it
seems to be a textbook example of "functionality used across otherwise unrelated
classes"...

The way in which these methods are actually implemented is curious and there may very
well be a better way; however, admittedly, I have yet to do the research for this
better way. Instead, I have something that seems to work.

For more details on the peculiarities of implementation, please refer to: Report.java.

*/

public interface IGrader {
  String fill     (final int AMOUNT);
  String fillLeft (final String VALUE, final int FIELD_WIDTH);
  String fillRight(final String VALUE, final int FIELD_WIDTH);
}