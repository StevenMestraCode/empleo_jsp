# Usar la imagen oficial de Tomcat
FROM tomcat:9.0

# Eliminar la app por defecto de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar tu WAR. Lo renombramos a ROOT.war para que cargue en la raíz (/)
COPY dist/empleo_jsp.war /usr/local/tomcat/webapps/ROOT.war

# --- CONFIGURACIÓN PARA RENDER ---

# 1. Crear la ruta de Health Check (para que Render sepa que estamos vivos)
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/healthz
RUN echo "OK" > /usr/local/tomcat/webapps/ROOT/healthz/index.html

# 2. LA MAGIA: La imagen oficial de Tomcat 9 lee estas variables de entorno
#    y reconfigura su server.xml automáticamente al arrancar.
ENV PORT=10000
ENV CATALINA_OPTS="-Djava.security.egd=file:/dev/./urandom"

# Exponer el puerto 10000
EXPOSE 10000
