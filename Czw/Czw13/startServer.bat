@echo off

set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_102
set DERBY_HOME=C:\Program Files\Java\jdk1.8.0_102\db
set DERBY_JARS=%DERBY_HOME%\lib\derby.jar;%DERBY_HOME%\lib\derbytools.jar;%DERBY_HOME%\lib\derbynet.jar;%DERBY_HOME%\lib\derbyclient.jar

"%JAVA_HOME%\bin\java" -Dderby.system.home=Z:\DerbyDbs -Dderby.storage.useDefaultFilePermissions=true -cp "%DERBY_JARS%" org.apache.derby.drda.NetworkServerControl start 

