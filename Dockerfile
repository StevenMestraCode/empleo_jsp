# Imagen base ligera de Payara Micro
FROM payara/micro:latest

# Copiar el WAR generado por NetBeans
COPY dist/empleo_jsp.war /opt/payara/deployments/

# Exponer el puerto HTTP
EXPOSE 8080

# Comando de arranque: Payara Micro ya sabe desplegar lo que haya en /deployments
ENTRYPOINT ["java", "-jar", "/opt/payara/payara-micro.jar"]
CMD ["--deploy", "/opt/payara/deployments/empleo_jsp.war"]
