# Makefile for building the Seductions of Scala Tutorial distribution

STAGE_DIR=seductions-of-scala-tutorial
ZIP=${STAGE_DIR}.zip

all: zip
	zip -r ${ZIP} ${STAGE_DIR}

zip: stage

stage: clean ${STAGE_DIR}
	cp README.* ScalaResources.* SeductionsOfScalaTutorial*.pdf ${STAGE_DIR}
	cp -r code-examples ${STAGE_DIR}/code-examples
	cp -r tutorial-exercises ${STAGE_DIR}/tutorial-exercises

# Actually, we will distribute the compiled class files:
# clean: code.clean stage.clean zip.clean
clean: stage.clean zip.clean

code.clean:
	cd tutorial-exercises/drawing-actors; sbt clean
	find code-examples tutorial-exercises -name '*.class' -exec rm {} \;

stage.clean:  
	rm -rf ${STAGE_DIR}

zip.clean:  
	rm -f ${ZIP}

${STAGE_DIR}:
	mkdir -p ${STAGE_DIR}
    
