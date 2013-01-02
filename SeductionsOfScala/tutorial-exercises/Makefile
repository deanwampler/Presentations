all: exercises avionics
	scala cheap-tests.scala

clean: exercises-clean avionics-clean

exercises:
	scala -cp . cheap-tests.scala

avionics: avionics-clean
	cd avionics-actors; scalac -cp .. *.scala

exercises-clean:
	rm -f *.class

avionics-clean:
	rm -rf avionics-actors/learn

run-avionics:
	cd avionics-actors; scala -cp ..:. learn.akka.avionics.Avionics