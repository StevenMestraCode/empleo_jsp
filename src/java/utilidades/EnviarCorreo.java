/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class EnviarCorreo {
    public static void enviar(String destinatario, String asunto, String cuerpo) throws Exception {
        String apiKey = System.getenv("RESEND_API");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new Exception("La variable RESEND_API no está configurada");
        }

        String json = String.format(
            "{\"from\":\"onboarding@resend.dev\",\"to\":\"%s\",\"subject\":\"%s\",\"text\":\"%s\"}",
            destinatario, asunto, cuerpo.replace("\n", "\\n")
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.resend.com/emails"))
            .header("Authorization", "Bearer " + apiKey)
            .header("Content-Type", "application/json")
            .POST(HttpRequest.BodyPublishers.ofString(json))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() != 200) {
            throw new Exception("Error al enviar correo: " + response.body());
        }
    }
}