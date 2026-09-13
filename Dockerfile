# Usar la imagen oficial de Tomcat
FROM tomcat:9.0

# Copiar tu WAR generado por NetBeans al directorio de despliegue de Tomcat
COPY dist/empleo_jsp.war /usr/local/tomcat/webapps/empleo_jsp.war

# Exponer el puerto HTTP
EXPOSE 8080

# Comando por defecto: Tomcat ya arranca automáticamente
