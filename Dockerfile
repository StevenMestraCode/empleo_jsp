# Usar la imagen oficial de Tomcat (con Java 17 para estabilidad)
FROM tomcat:9.0-jdk17-temurin

# Eliminar la app por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar tu WAR como ROOT.war para que cargue en la raíz (/)
COPY dist/empleo_jsp.war /usr/local/tomcat/webapps/ROOT.war

# Exponer el puerto 8080 (el que Tomcat usa por defecto)
EXPOSE 8080
