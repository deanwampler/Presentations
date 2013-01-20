# Compiles the scala files that aren't scripts.
# Runs the scripts

SCALA_OPTS = -classpath ${PWD}
SCRIPTS    = string-interpolation implicits futures values \
			 macros type-dynamic not-implemented reflection try

all: clean compile scripts

clean:
	rm -rf examples

compile:
	scalac Units.scala macros-Print-sip16.scala macros-ReflectiveClosure-sip16.scala

scripts: ${SCRIPTS}

string-interpolation:
	scala ${SCALA_OPTS} string-interpolation-sip11.scala

implicits:
	scala ${SCALA_OPTS} implicit-classes-sip13.scala
	scala ${SCALA_OPTS} implicit-conversions-sip13.scala

futures:
	scala ${SCALA_OPTS} futures-promises-sip14.scala
	scala ${SCALA_OPTS} futures-as-combinators-sip14.scala

values:
	scala ${SCALA_OPTS} value-classes-sip15.scala

macros:
	scala ${SCALA_OPTS} macros-sip16.scala

type-dynamic:
	scala ${SCALA_OPTS} type-dynamic-sip17.scala

not-implemented:
	scala ${SCALA_OPTS} not-implemented.scala

reflection:
	scala ${SCALA_OPTS} reflection-api.scala

try:
	scala ${SCALA_OPTS} try-example.scala