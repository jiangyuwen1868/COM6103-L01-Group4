@echo off
echo.
echo [信息] 打包Web工程，生成war/jar包文件。
echo.

%~d0
cd %~dp0

cd ..
call mvn -f pom-use-shaded.xml clean package -Dmaven.test.skip=true

pause