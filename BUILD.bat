@echo off
rem gradlew_ForPeopleWithAFuckedJavaEnv build
gradlew build

cd "build\libs"
del *-dev.jar
"..\..\advzip.exe" -z -3 *.jar
rem