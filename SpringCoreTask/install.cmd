set JAVA_HOME=D:\Programs\Java\jdk1.8.0_60
set MAVEN_HOME=D:\Programs\apache-maven-3.0.4
set PATH=%PATH%;%MAVEN_HOME%\bin

set PATH_TO_WORKSPACE=D:\eclipseLUNA\workspace2\SpringCoreTask

cd %PATH_TO_WORKSPACE%
REM call mvn clean install

call mvn exec:java -Dexec.mainClass=com.epam.sc.main.SpringCoreApp
REM -Dmaven.test.skip=true

cd %PATH_TO_WORKSPACE%\target\
call java -jar springCoreTask-1.0.jar

pause