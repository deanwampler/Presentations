all: exercises
	scala cheap-tests.scala

clean: exercises.clean 

exercises:
	scala -cp . cheap-tests.scala

exercises.clean:
	rm -f *.class
