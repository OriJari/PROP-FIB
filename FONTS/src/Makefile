DESTI_CLASSES = ../../EXE/
CODI_FONT = /src/
JUNIT = src/dominio/controladores/junits
LIB = libs/junit-4.12.jar:libs/hamcrest-core-1.3.jar



default: class run


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
	javac -cp $(LIB) -d ${DESTI_CLASSES} ./dominio/controladores/junits/K_MeansTest.java ./dominio/clases/algorithm/k_means/*.java
	javac -cp $(LIB) -d ${DESTI_CLASSES} ./dominio/controladores/junits/K_NNTest.java ./dominio/clases/algorithm/contentbasedflitering/*.java ./dominio/clases/content/*.java ./dominio/clases/item/*.java ./dominio/clases/tag/*.java

run: 
	java -classpath ${DESTI_CLASSES} testmain.java

junits:
	java -cp $(LIB):${DESTI_CLASSES} org.junit.runner.JUnitCore dominio.controladores.junits.K_MeansTest
	java -cp $(LIB):${DESTI_CLASSES} org.junit.runner.JUnitCore dominio.controladores.junits.K_NNTest


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

driver_slopeone:
	java -classpath ${DESTI_CLASSES} dominio.controladores.drivers.Driverslopeone



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
	@echo "driver_slopeone : ejecuta el driver slopone "
	@echo "junits: Executa los test junits"
	@echo "run: Executa el código"

.PHONY: clean
clean:
	rm -rf $(DESTI_CLASSES) 

