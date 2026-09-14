# Usar una versión estable de Java (17) con Tomcat 9
FROM tomcat:9.0-jdk17-temurin

# Eliminar la app por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar tu WAR generado por NetBeans como ROOT.war (para que cargue en la raíz /)
COPY dist/empleo_jsp.war /usr/local/tomcat/webapps/ROOT.war

# --- CONFIGURACIÓN PARA RENDER ---

# 1. Crear la ruta de Health Check
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/healthz
RUN echo "OK" > /usr/local/tomcat/webapps/ROOT/healthz/index.html

# 2. LA CLAVE: Forzar a Tomcat a usar el puerto 10000 mediante propiedades del sistema
ENV CATALINA_OPTS="-Dport=10000 -Djava.security.egd=file:/dev/./urandom"

# Exponer el puerto 10000
EXPOSE 10000
