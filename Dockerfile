# Imagen base de GlassFish
FROM glassfish/server:5.0

# Copiar el WAR generado por NetBeans al dominio por defecto
COPY dist/empleo_jsp.war /glassfish5/glassfish/domains/domain1/autodeploy/empleo_jsp.war

# Exponer el puerto de GlassFish
EXPOSE 8080

# Comando para arrancar GlassFish
CMD ["asadmin", "start-domain", "-v"]
