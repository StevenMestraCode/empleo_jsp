# Usar la imagen oficial de Tomcat
FROM tomcat:9.0

# Eliminar la app por defecto de Tomcat (opcional pero recomendado)
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar tu WAR. Lo renombramos a ROOT.war para que cargue en la raíz (/)
COPY dist/empleo_jsp.war /usr/local/tomcat/webapps/ROOT.war

# --- CONFIGURACIÓN PARA RENDER ---

# 1. Modificar server.xml para que Tomcat escuche en el puerto 10000 directamente
#    Render usa el puerto 10000 por defecto para sus web services.
RUN sed -i 's/port="8080"/port="10000"/g' /usr/local/tomcat/conf/server.xml

# 2. Crear la ruta de Health Check
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/healthz
RUN echo "OK" > /usr/local/tomcat/webapps/ROOT/healthz/index.html

# Exponer el puerto 10000
EXPOSE 10000

# Comando por defecto: Tomcat ya arranca automáticamente
