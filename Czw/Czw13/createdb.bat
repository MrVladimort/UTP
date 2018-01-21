@echo off
set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_102
set DERBY_HOME=C:\Program Files\Java\jdk1.8.0_102\db
set DERBY_JARS=%DERBY_HOME%/lib/derby.jar;%DERBY_HOME%/lib/derbynet.jar;%DERBY_HOME%/lib/derbyclient.jar;%DERBY_HOME%/lib/derbytools.jar
set DERBY_SYSTEM_HOME=Z:\DerbyDbs

"%JAVA_HOME%\bin\java" -Dderby.system.home=%DERBY_SYSTEM_HOME% -cp "%DERBY_JARS%;%CLASSPATH%" -Dij.protocol=jdbc:derby: org.apache.derby.tools.ij createdb.sql