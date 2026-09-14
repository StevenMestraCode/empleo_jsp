# Usar la imagen oficial de Tomcat
FROM tomcat:9.0

# Copiar tu WAR generado por NetBeans al directorio de despliegue de Tomcat
COPY dist/empleo_jsp.war /usr/local/tomcat/webapps/empleo_jsp.war

# --- CONFIGURACIÓN PARA RENDER ---

# 1. Modificar server.xml para que Tomcat escuche en el puerto que Render le asigne ($PORT)
RUN sed -i 's/port="8080"/port="${PORT}"/g' /usr/local/tomcat/conf/server.xml

# 2. Crear la ruta de Health Check para que Render sepa que la app está viva
# Esto crea un archivo en http://tu-app.onrender.com/healthz
RUN mkdir -p /usr/local/tomcat/webapps/ROOT/healthz
RUN echo "OK" > /usr/local/tomcat/webapps/ROOT/healthz/index.html

# Exponer el puerto (informativo, Render usará el 10000 por defecto)
EXPOSE 10000

# Comando por defecto: Tomcat ya arranca automáticamente
