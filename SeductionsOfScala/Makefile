# Makefile for building the Seductions of Scala Tutorial distribution

STAGE_DIR=seductions-of-scala-tutorial

all: zip
	zip -r ${STAGE_DIR}.zip ${STAGE_DIR}

zip: stage

stage: clean ${STAGE_DIR}
	cp README.* ScalaResources.markdown SeductionsOfScalaTutorial*.pdf ${STAGE_DIR}
	cp -r code-examples ${STAGE_DIR}/code-examples
	cp -r tutorial-exercises ${STAGE_DIR}/tutorial-exercises

# Actually, we will distribute the pre-compiled code:
# clean: code.clean stage.clean
clean: stage.clean

code.clean:
	cd tutorial-exercises/drawing-actors; sbt clean
	find code-examples tutorial-exercises -name '*.class' -exec rm {} \;

stage.clean:  
	rm -rf ${STAGE_DIR}

${STAGE_DIR}:
	mkdir -p ${STAGE_DIR}
    