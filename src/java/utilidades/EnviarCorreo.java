/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ASUS
 */

public class EnviarCorreo {

    public static void enviar(String destinatario, String asunto, String cuerpo) throws Exception {
        // Leer credenciales desde variables de entorno
        final String remitente = System.getenv("DB_EMAIL");
        final String clave = System.getenv("DB_CLAVE");

        // Validar que las variables estén configuradas
        if (remitente == null || remitente.isEmpty()) {
            throw new Exception("La variable de entorno DB_EMAIL no está configurada");
        }
        if (clave == null || clave.isEmpty()) {
            throw new Exception("La variable de entorno DB_CLAVE no está configurada");
        }

        // Configuración de propiedades SMTP para Gmail
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Sesión autenticada
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        // Crear el mensaje
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remitente));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        message.setSubject(asunto);
        message.setText(cuerpo);

        // Enviar
        Transport.send(message);
    }
}
