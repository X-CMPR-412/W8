import java.util.Scanner;

/*
Name:        t. crook
Class:       CMPR.X412.(21) Java Programming for Beginners
Section:     21
Date:        November 22, 2019
Due:         November 23, 2019
Version:     6
Description: Final Project - Determine Letter Grades
*/

//
//   C   O   N   T   E   N   T   S
//

/*
- O V E R V I E W
- N O T E S
- C O D E
- T E S T
  . REPORT
    o Description
    o Issues
  . LOG
- A S S I G N M E N T  (program specification)
- R E A D M E
*/

//
//   O   V   E   R   V   I   E   W
//

/*
If you haven't already, please consider reading the README.txt file.

Don't have or can't find the README.txt file?

Don't worry! A near-copy of same may be found near the bottom of this file!

The CONTENTS, above, lists the major sections of this file; the ASSIGNMENT section,
right above README, at bottom, is a recreation of the final project specification! It
explains in admirable detail what this final project is, why it exists, and what it's
supposed to do! It also contains some design and implementation details.

The CODE is the actual main for this program (also known as "the final project"); it
immediately follows the next section, NOTES.

The TEST section has information on what was, and was not, tested, what was found, what
was found and fixed, or not...

Finally, the NOTES, which immediately follows, has information about the program,
including design rationale.
*/

//
//   N   O   T   E   S
//

/*
Every .java file in the final project: 1) has one and only one class; 2) is documented.

Comments within the classes themselves are sparse - the odd or interesting aspect...

The class documentation provides information on design choices and confessions;
too many nagging feelings about how things should, or could, be better...

Major themes:

- Was gonna do input data file validation - then read the assignment, again...
- Bubble sort used...
- Dynamic array used to store scores
  . Horribly inefficient - just copies over to another array twice as large...
  - Binary Search Tree woulda been better:
    o Uses only the space needed.
    o Eliminates the sort.
- Interface has three formatting methods used by both reports.
- Report inheritance chain is interesting:
  - Parent abstract class implements the three interface methods without the keyword...
  - Child classes use the implements keyword but use super to access parent's methods.
  - There's gotta be a better way - and am looking forward to learning more about that.
*/

//
//   C   O   D   E
//

/*
The following class exists solely as a container for the main which does some
argument testing, mainly, the number of, which must be two. Assuming that passes,
the input file is read, followed by the reporting, writing results to both the
console and output file.

Console and file I/O open and close were done as close together as practically possible
in order to avoid conflicts resulting in run-time errors.

Run-time errors that do manage to be detected or caught result in program termination.
*/

public class TestLetterGrader {
  public static void main(String[] args) {
    final byte         NUMBER_COMMAND_LINE_ARGUMENTS = 2;
    if (args.length != NUMBER_COMMAND_LINE_ARGUMENTS) {
      System.out.println("Usage: java TestLetterGrader InputFile OutputFile");
      return;
    }
    LetterGrader letterGrader = new LetterGrader(args[0], args[1]);
    letterGrader.read  ();
    letterGrader.report();

    System.out.print("Press ENTER to continue... ");
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();
    scanner.close   ();
  }
}

//
//   T   E   S   T
//

/*

===========
R E P O R T
===========

-----------
Description
-----------

This is the closest this final project comes to formal testing.

The CLI was used and the LOG, below, is a copy of the user interaction.

While Eclipse was used for almost all development; the CLI provided an easy and
succinct approach.

What wasn't tested: bad input files; although a major deficiency if this was in
production, the ASSIGNMENT, below, seemed to be specific about this not being a
specification...

Tests:

1. zero        arguments
2. one         argument
3. nonexistent file
4. empty       file
5. valid       file

------
Issues
------

- The labels "Minimum" and "Maximum" were reversed - fixed.
- An empty input file produces garbage          (NOT fixed):
  . Console displays initial values of instance variables - lotta zeroes...
  . Output file has message about containing letter grades for zero students...

A fix for this would be additional logic to test for an empty file.

Which leads to more questions, for example: what about a file with a single line?

=====
L O G
=====

Users-MacBook-Pro:src user$ date
Thu Nov 21 16:55:20 PST 2019
Users-MacBook-Pro:src user$ javac TestLetterGrader.java
Users-MacBook-Pro:src user$ java  TestLetterGrader
Usage: java TestLetterGrader InputFile OutputFile
Users-MacBook-Pro:src user$ java  TestLetterGrader THIS_FILE_DOES_NOT_EXIST
Usage: java TestLetterGrader InputFile OutputFile
Users-MacBook-Pro:src user$ java  TestLetterGrader THIS_FILE_DOES_NOT_EXIST neitherDoesThisOne
Input File "THIS_FILE_DOES_NOT_EXIST" not found

Users-MacBook-Pro:src user$ touch EMPTY_FILE
Users-MacBook-Pro:src user$ java  TestLetterGrader EMPTY_FILE EMPTY_FILE
Letter grade has been calculated for students
that are listed in the input file EMPTY_FILE
and are written to the output file EMPTY_FILE

Here are the class averages:

             Q1        Q2        Q3        Q4      MidI     MidII     Final
Average:    0.0       0.0       0.0       0.0       0.0       0.0       0.0
Minimum: 2147483647    2147483647    2147483647    2147483647    2147483647    2147483647    2147483647
Maximum:      0         0         0         0         0         0         0

Press ENTER to continue... 
Users-MacBook-Pro:src user$ cat EMPTY_FILE
Letter grade for 0 students given in file EMPTY_FILE:

Users-MacBook-Pro:src user$ cat ../input_final.txt
Thui Bhu, 100, 90, 80, 100, 89, 99, 88
Ariana B. Smith, 90, 90, 100, 100, 99, 100, 95
Emily Gonzales, 100, 90, 100, 70, 78, 78, 80
Jennifer L, 80, 90, 90, 100, 89, 99, 85
Maria Jones, 65, 72, 77, 68, 62, 70, 65
Bill Gates, 60, 54, 38, 62, 65, 60, 50
Escobar Morris, 83, 77, 88, 76, 79, 72, 76
Anne Latner, 80, 80, 85, 95, 90, 95, 90
Users-MacBook-Pro:src user$ java  TestLetterGrader ../input_final.txt ../output_final.txt
Letter grade has been calculated for students
that are listed in the input file ../input_final.txt
and are written to the output file ../output_final.txt

Here are the class averages:

             Q1        Q2        Q3        Q4      MidI     MidII     Final
Average:  82.25     80.38     82.25     83.88     81.38     84.13     78.63
Minimum:     60        54        38        62        62        60        50
Maximum:    100        90       100       100        99       100        95

Press ENTER to continue... 
Users-MacBook-Pro:src user$ cat ../output_final.txt
Letter grade for 8 students given in file ../input_final.txt:

Anne Latner:                      B
Ariana B. Smith:                  A
Bill Gates:                       F
Emily Gonzales:                   B
Escobar Morris:                   C
Jennifer L:                       B
Maria Jones:                      D
Thui Bhu:                         A
Users-MacBook-Pro:src user$ date
Thu Nov 21 16:59:14 PST 2019
Users-MacBook-Pro:src user$ 

*/

//
//   A   S   S   I   G   N   M   E   N   T      (program specification)
//

/*
Programming Assignment: Final Project
Name:                   Determine Letter Grades
Class:                  Java Programming for Beginners
Instructor:             Bineet Sharma

S u m m a r y

Write a program that will determine the letter grade of students of a class
(university class, not Java class) using your understanding of class (Java class),
objects, exception handling, and collections in Java.

D e s c r i p t i o n

Your program will need to accept two command line arguments. The first argument will
be the name of a disk file that contains the names of students, and their test scores,
separated by commas followed by one or more spaces. Each line in the file will contain
scores for one student. The second argument will be the name of an output disk file.
Your program will create a new output disk file using that name. The output file will
have grade information of all students whose information was read from the input file,
in sorted order. You will be writing one line in the output file for one student. You
will write the name of one student and the letter grade he/she got in the class in each
line (you should format the texts in the output file). The format of the data in the
output file is fixed; however, the number of students in the input file is unknown
during compile time. The name of input and output files could be anything and only
known during run time. Besides writing to the output file, you will also need to
display the averages of the scores along with the minimum and maximum scores for each
test in the screen/console.

===========
Calculation
===========

The test scores are weighted. There are four quizzes, 40% total, midterm I is 20%,
midterm II is 15% and the final is 25%. All scores in the input file are recorded out
of 100. You need to apply the weight for each score in the program to calculate the
final score. The final score is tabulated as follows:

Final Score = quiz1 * .10 + quiz2 * .10 + quiz3 * .10 + quiz4 * .10 +
               midi * .20 + midii * .15 + final * .25

Determination of the letter grade:

Final Score >= 90%       then the letter grade is an A,
               80% - 89% then the letter grade is a  B,
               70% - 79% then the letter grade is a  C,
               60% - 69% then the letter grade is a  D,
            <= 59%       then the letter grade is an F
            
S a m p l e   i n p u t   d a t a   f i l e :   i n p u t _ d a t a . t x t

The input data format is:

name, quiz1, quiz2, quiz3, quiz4, midi, midii, final

you may assume the data will be correctly formatted as described; for example:

Thui Bhu, 100, 90, 80, 100, 89, 99, 88
Ariana B. Smith, 90, 90, 100, 100, 99, 100, 95
Emily Gonzales, 100, 90, 100, 70, 78, 78, 80
Jennifer L, 80, 90, 90, 100, 89, 99, 85
Maria Jones, 65, 72, 77, 68, 62, 70, 65
Bill Gates, 60, 54, 38, 62, 65, 60, 50
Escobar Morris, 83, 77, 88, 76, 79, 72, 76
Anne Latner, 80, 80, 85, 95, 90, 95, 90

<<< more if there are more students >>>

S a m p l e   o u t p u t   d a t a   f i l e :   o u t p u t _ d a t a . t x t

The output format is:

Name: letter grade

The output should be sorted by name; for example:

Letter grade for 8 students given in input_final.txt file is:

Anne Latner:                B
Ariana B. Smith:            A
Bill Gates:                 F
Emily Gonzales:             B
Escobar Morris:             C
Jennifer L:                 B
Maria Jones:                D
Thui Bhu:                   A

<<< more if there are more students >>>

S a m p l e   R u n   o f   t h e   p r o g r a m

The name of the application is TestLetterGrader.

Remember that there are two sets of outputs. Letter grade is written in the output
disk file (which is not shown on the screen), and score averages are displayed on the
console (and are not written in the disk file). Here is an example of a run using the
command line (you could also run this from your IDE (eclipse); you just need to provide
the arguments in the class properties):

C:>java TestLetterGrader input_data.txt output_data.txt

Letter grade has been calculated for students listed in input file input_data.txt
and written to output file output_data.txt

Here are the class averages:

             Q1        Q2        Q3        Q4      MidI     MidII     Final
Average:  82.25     80.38     82.25     83.88     81.38     84.13     78.63
Minimum:     60        54        38        62        62        60        50
Maximum:    100        90       100       100        99       100        95

Press ENTER to continue... 
C:>

S c o r e

The maximum score you can receive for this final project is 100 and it is divided into
the following categories:

    1) Program compiles, runs, and provides the correct answers in the format as shown
       (70%).

    2) The application design is modular and has good choices of classes and methods
       with proper data encapsulation
       (20%).

    3) Proper error checking, exception handling and descriptive comments are used
       (10%).

D e s i g n   S u g g e s t i o n

You need to model this problem into a software application - TestLetterGrader
                                                             ----------------
(driver class). Your final program should be implemented as Object Oriented,
instantiated using new. The driver class, TestLetterGrader, uses your main class,
                                          ----------------
LetterGrader, which really determines the grade - meaning LetterGrader has all the code
------------                                              ------------
to determine the grade, TestLetterGrader simply uses the LetterGrader.
                         ----------------                 ------------

You need to identify classes, their hierarchies (inheritance and/or interface) to be
used. THe driver class will simply parse the command line arguments and use the main
class to read the student's scores, do the calculation, output the result, and close
the files. Only TestLetterGrader and LetterGrader class are needed to be public
                ----------------     ------------
classes. You will need only one main method in the application and that should be in
the TestLetterGrader class only. You may have a main in LetterGrader for testing
    ----------------                                    ------------
purposes; however, it will not be used when your application, the driver class,
TestLetterGrader is run.
----------------

A simplified pseudo-code for the driver class (TestLetterGrader.java), your
                                               ----------------
application, may look like this:

public class TestLetterGrader {
  public static void main(String args[]) {
    // test if there are two valid arguments, then, create the object
    // if not, give the right message and exit

    LetterGrader letterGrader = new LetterGrader(args[0], args[1]);

    // LetterGrader is your main class,
    // args[0] has the input  file name, and
    // args[1] has the output file name

    letterGrader.readScore();       // reads score, storing member data in variables
    letterGrader.calcLetterGrade(); // determines letter grade and stores information
    letterGrader.printGrade();      // writes the grade in output file
    letterGrader.displayAverage();  // displays the averages in the console
    letterGrader.doCleanup();       // use it to close files and other resources

    // remember you need to take care of any errors or exceptions in any of these
    // activities
  }
}

S u b m i s s i o n   R e q u i r e m e n t s

Submit your source code, all java files (*.java), through the UCSC web-portal
(as attachments) before the deadline. You DO NOT need to submit anything else.

N a m i n g   R e q u i r e m e n t s   -   ...especially for public items:
                                                              ------

===============
Important Class
===============

The public class which does all the work, reading input data, calculating the grade
averages, and printing data in disk file and console - LetterGrader.java.
                                                       ------------

=========
Interface - is optional: IGrader.java
=========                -------

=======
Package   - is optional: utility
=======                  -------

=============
Private Items
=============

You can choose any names you want for private classes which does not affect file names.
You can add all private classes in your main class, LetterGrader.java (yes you can have
                                                    ------------
multiple classes in one .java file as long as there is only one public class in that
file). If you are planning to inherit from a class of your own to your public class, in
that case, leave the access modifier of the parent class to nothing (that makes it a
package access as you can't inherit from a private class).

============
Driver Class (your application)
============

The public class which acts like a driver program is really the application name -
TestLetterGrader.java.
----------------
*/

//
//   R   E   A   D   M   E
//

/*
Name:        t. crook
Class:       CMPR.X412.(21) Java Programming for Beginners
Section:     21
Date:        November 22, 2019
Due:         November 23, 2019
Version:     6
Description: Final Project - Determine Letter Grades
*/

/*
This final project exists as an Eclipse project named TestLetterGrader:

TestLetterGrader
  bin
  src
    (default package)
      TestLetterGrader.java          // main and most documentation
      LetterGrader.java
      Report.java
      ReportClassAverages.java
      ReportStudentLetterGrades.java
      Score.java
    utility
      Average.java
      IGrader.java
  input_final.txt
  output_final.txt
  README.txt                         // YOU WOULD BE HERE IF THIS WAS ACTUALLY
                                     // THE ACTUAL README.txt FILE!
                                     // Reminder: it isn't, this is
                                     //           TestLetter.Grader.java

All source files, except for those in the utility package, reside in the
"default package" (NOT visible from Finder or the CLI...).

TestLetterGrader.java has the main and most documentation; 
you may want to consider checking that out next...

(actually, you're already there! suggestion: start at the top of this file).
*/