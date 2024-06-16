PRINT "This program demonstrates all features.";
FOR X = 1 TO 5
    PRINT X; X/2, X; X*X
NEXT
GOSUB Test

PRINT "hello"
INPUT "Test", H
PRINT 12 - 4 / 2
PRINT 100

Test:
PRINT "this is a subroutine"
RETURN