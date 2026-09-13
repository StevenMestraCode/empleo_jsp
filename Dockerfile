# Imagen base ligera de Payara Micro
FROM payara/micro:latest

# Copiar el WAR generado por NetBeans
COPY dist/empleo_jsp.war /opt/payara/deployments/

# Exponer el puerto HTTP
EXPOSE 8080

# Comando de arranque (Payara Micro despliega automáticamente lo que haya en /deployments)
CMD ["java", "-jar", "/opt/payara/payara-micro.jar", "--deploy", "/opt/payara/deployments/empleo_jsp.war"]
