FROM payara/micro:latest

# Copiar el WAR
COPY dist/empleo_jsp.war /opt/payara/deployments/

# Exponer el puerto
EXPOSE 8080

# Arranque con propiedad para habilitar REST API de Hazelcast
CMD ["java", "-Dhz.network.rest-api.enabled=true", "-jar", "/opt/payara/payara-micro.jar", "--deploy", "/opt/payara/deployments/empleo_jsp.war"]

