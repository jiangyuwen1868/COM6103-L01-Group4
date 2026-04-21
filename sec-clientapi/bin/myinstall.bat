@echo off
echo.
echo [��Ϣ] ��װ�ⲿjar����maven���ؿ⡣
echo.

%~d0
cd %~dp0

cd ../lib
call mvn install:install-file -Dfile=gmssl_provider.jar -DgroupId=cn.gmssl -DartifactId=gmssl_provider -Dversion=1.0.0 -Dpackaging=jar
call mvn install:install-file -Dfile=cspGatewayAPI_1.0.0.jar -DgroupId=com.anydef.csp -DartifactId=cspGatewayAPI -Dversion=1.0.0 -Dpackaging=jar
call mvn install:install-file -Dfile=anykit-1.0.0.jar -DgroupId=com.anydef.anykit -DartifactId=anykit -Dversion=1.0.0 -Dpackaging=jar

pause