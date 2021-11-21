DESTI_CLASSES = ../../EXE/
CODI_FONT = /src/





$(DESTI_CLASSES):
	mkdir -p $(DESTI_CLASSES)



.PHONY: class
class:
	javac -d ${DESTI_CLASSES} ./dominio/clases/algorithm/collaborativefiltering/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/algorithm/contentbasedflitering/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/algorithm/k_means/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/algorithm/slopeone/*.java	
	javac -d ${DESTI_CLASSES} ./dominio/clases/atribut/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/content/*.java
	javac -d ${DESTI_CLASSES} ./dominio/controladores/drivers/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/evaluation/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/item/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/preprocessat/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/tag/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/tipus/*.java
	javac -d ${DESTI_CLASSES} ./dominio/clases/user/*.java

run:
	java -classpath ${DESTI_CLASSES} testmain.javama


driver_CollaborativeFiltering:
	java -classpath ${DESTI_CLASSES} dominio.controladores.drivers.DriverCollaborativeFiltering

driver_CSVparserItem:
	java -classpath ${DESTI_CLASSES} dominio.controladores.drivers.DriverCSVparserItem

driver_CSVparserRate:
	java -classpath ${DESTI_CLASSES} dominio.controladores.drivers.DriverCSVparserRate

driver_Evaluation:
	java -classpath ${DESTI_CLASSES} dominio.controladores.drivers.DriverEvaluation

driver_K_Means:
	java -classpath ${DESTI_CLASSES} dominio.controladores.drivers.DriverK_Means

driver_K_NN:
	java -classpath ${DESTI_CLASSES} dominio.controladores.drivers.DriverK_NN

driver_slopone:
	java -classpath ${DESTI_CLASSES} dominio.controladores.drivers.Driverslopeone


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

