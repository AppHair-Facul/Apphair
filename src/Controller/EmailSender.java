/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {

    private static final String EMAIL = "b79700086@gmail.com";
    private static final String SENHA = "barbeiro1234@";

    public static void enviarEmail(String destinatario, String assunto, String corpo) {
        Properties propriedades = new Properties();

        // Configurações para o Gmail, ajuste conforme o servidor de e-mail que você está usando
        propriedades.put("mail.smtp.host", "smtp.gmail.com");
        propriedades.put("mail.smtp.socketFactory.port", "465");
        propriedades.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        propriedades.put("mail.smtp.auth", "true");
        propriedades.put("mail.smtp.port", "465");

        // Adicione estas linhas para forçar o uso de TLS v1.2
        propriedades.put("mail.smtp.ssl.protocols", "TLSv1.2");
        propriedades.put("mail.smtp.ssl.ciphersuites", "TLS_AES_128_GCM_SHA256");

        // Configuração do autenticador
        Session sessao = Session.getInstance(propriedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, SENHA);
            }
        });

        // Criação da mensagem
        MimeMessage mensagem = new MimeMessage(sessao);
        try {
            mensagem.setFrom(new InternetAddress(EMAIL));
            mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensagem.setSubject(assunto);
            mensagem.setText(corpo);

            // Envio da mensagem
            Transport.send(mensagem);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
