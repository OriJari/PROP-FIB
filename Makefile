DESTI_CLASSES = ./EXE/
CODI_FONT = /subgrup-prop2-3/
JUNIT = src/dominio/controladores/junits
LIB = ./FONTS/src/libs/junit-4.12.jar:/FONTS/src/libs/hamcrest-core-1.3.jar
ORG = ./FONTS/src/



default: class run


$(DESTI_CLASSES):
	mkdir -p $(DESTI_CLASSES)



.PHONY: class
class:
	javac -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/algorithm/collaborativefiltering/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/algorithm/contentbasedflitering/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/algorithm/k_means/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/algorithm/slopeone/*.java	-d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/atribut/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/content/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/controladores/drivers/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/evaluation/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/item/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/preprocessat/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/tag/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/tipus/*.java -d ${DESTI_CLASSES} ./FONTS/src/dominio/clases/user/*.java -cp $(LIB) -d ${DESTI_CLASSES} ./FONTS/src/dominio/controladores/junits/K_MeansTest.java ./FONTS/src/dominio/clases/algorithm/k_means/*.java -cp $(LIB) -d ${DESTI_CLASSES} ./FONTS/src/dominio/controladores/junits/K_NNTest.java ./FONTS/src/dominio/clases/algorithm/contentbasedflitering/*.java ./FONTS/src/dominio/clases/content/*.java ./FONTS/src/dominio/clases/item/*.java ./FONTS/src/dominio/clases/tag/*.java

run: 
	java -cp ${DESTI_CLASSES} ./FONTS/src/testmain.java

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

