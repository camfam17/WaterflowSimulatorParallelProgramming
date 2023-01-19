JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
		ForkJoinTest.java \
		SerialTest.java \
        Main.java \
        

default: classes

classes:$(CLASSES:.java=.class)

clean:
	$(RM) *.class
	
run:
	java Main
	
runt:
	java Main < Data/trivialbasin_in.txt
	
runs:
	java Main < Data/small_in.txt
	
runm:
	java Main < Data/med_in.txt
	
runl:
	java Main < Data/large_in.txt
