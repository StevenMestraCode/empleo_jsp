# Imagen base de Payara (servidor completo)
FROM payara/server-full:5.2022

# Copiar el WAR generado por NetBeans al directorio de despliegue automático
COPY dist/empleo_jsp.war $PAYARA_PATH/glassfish/domains/domain1/autodeploy/empleo_jsp.war

# Exponer el puerto de Payara
EXPOSE 8080

# Comando para arrancar el dominio por defecto
CMD ["asadmin", "start-domain", "-v", "domain1"]
