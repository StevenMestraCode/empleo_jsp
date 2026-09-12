/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package utilidades;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
/**
 *
 * @author ASUS
 */

public class EnviarCorreo {

    public static void enviar(String destinatario, String asunto, String cuerpo) throws Exception {
        // Tu correo y contraseña de aplicación
        final String remitente = "stevenmestra3@gmail.com";
        final String clave = "sbzzspynwucffupt"; // contraseña de aplicación generada en Gmail

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