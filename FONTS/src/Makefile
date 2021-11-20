DESTI_CLASSES = ../../EXE/
CODI_FONT = /src/


run:
	java -classpath ${DESTI_CLASSES} testmain.java


$(DESTI_CLASSES):
	mkdir -p $(DESTI_CLASSES)



.PHONY: class
class:
	javac -d ${DESTI_CLASSES} algorithm/*.java
	javac -d ${DESTI_CLASSES} atribut/*.java
	javac -d ${DESTI_CLASSES} content/*.java
	javac -d ${DESTI_CLASSES} drivers/*.java
	javac -d ${DESTI_CLASSES} evaluation/*.java
	javac -d ${DESTI_CLASSES} item/*.java
	javac -d ${DESTI_CLASSES} preprocessat/*.java
	javac -d ${DESTI_CLASSES} tag/*.java
	javac -d ${DESTI_CLASSES} tipus/*.java
	javac -d ${DESTI_CLASSES} user/*.java


driver_CollaborativeFiltering:
	java -classpath ${DESTI_CLASSES} drivers.DriverCollaborativeFiltering

driver_CSVparserItem:
	java -classpath ${DESTI_CLASSES} drivers.DriverCSVparserItem

driver_CSVparserRate:
	java -classpath ${DESTI_CLASSES} drivers.DriverCSVparserRate

driver_Evaluation:
	java -classpath ${DESTI_CLASSES} drivers.DriverEvaluation

driver_K_Means:
	java -classpath ${DESTI_CLASSES} drivers.DriverK_Means

driver_K_NN:
	java -classpath ${DESTI_CLASSES} drivers.DriverK_NN

driver_slopone:
	java -classpath ${DESTI_CLASSES} drivers.Driverslopeone


default: class run



.PHONY: help
help:
	@echo "Opcions:"
	@echo "class: Compila el codigo a /EXE"
	@echo "driver_CollaborativeFiltering : ejecuta el driver CollaborativeFiltering"
	@echo "driver_CSVparserItem : ejecuta el driver CSVparserItem "
	@echo "driver_CSVparserRate : ejecuta el driver CSVparserRate"
	@echo "driver_Evaluation : ejecuta el driver Evaluation "
	@echo "driver_K_Means : ejecuta el driver K_Means "
	@echo "driver_K_NN : ejecuta el driver K_NN "
	@echo "driver_slopone : ejecuta el driver slopone "
	@echo "run: Executa el código"

.PHONY: clean
clean:
	rm -rf EXE *.class