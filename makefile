SRC_DIR = src
OUT_DIR = classes
PORT_NO=300
HOSTNAME=localhost
COOKIE_FILE_PATH=/Users/chloe/VTTP/sdf/sdf_04w/cookie_file.txt
WORKSHOP_JAR = fortunecookie.jar

SOURCES = $(shell find $(SRC_DIR) -name '*.java')
JAVAC = javac
JFLAGS = -d $(OUT_DIR) -cp $(OUT_DIR)
JAVA = java

CLIENT_APP = fc.Client
SERVER_APP = fc.Server

all: $(OUT_DIR) compile

$(OUT_DIR):
	mkdir -p ${OUT_DIR}

compile: $(SOURCES)
	$(JAVAC) ${} ${JFLAGS} ${SOURCES}

run-client: compile
	${JAVA} -cp ${OUT_DIR} ${CLIENT_APP} ${HOSTNAME}:${PORT_NO}

run-server: compile
	${JAVA} -cp ${OUT_DIR} ${SERVER_APP} ${PORT_NO} ${COOKIE_FILE_PATH}

jar: compile
	jar cvf ${WORKSHOP_JAR} -C ${OUT_DIR} .

run-client-jar: jar
	${JAVA} -cp ${WORKSHOP_JAR} ${CLIENT_APP} ${HOSTNAME}:${PORT_NO}

run-server-jar: jar
	${JAVA} -cp ${WORKSHOP_JAR} ${SERVER_APP} ${PORT_NO} ${COOKIE_FILE_PATH}

clean:
	@rm -rf ${OUT_DIR}
	@rm -rf ${WORKSHOP_JAR}