package br.com.mike_lanches.api.core.mail.services;

// Imports
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.thymeleaf.context.Context;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;


    @Value("${spring.mail.username}")
    private String username;

    public String sendContactAdminEmail(String nameContact, String emailContact, String messageContact) {
        try {

            // Criando o contexto para Thymeleaf e inserindo variáveis
            Context context = new Context();
            context.setVariable("name_contact", nameContact);
            context.setVariable("email_contact", emailContact);
            context.setVariable("message_contact", messageContact);

            String htmlContent = templateEngine.process("contact.html", context);

            // Criando e enviando email
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
            messageHelper.setFrom(username);
            messageHelper.setTo("vidalcaio125@gmail.com");
            messageHelper.setSubject("Contato do usuário " + nameContact);
            messageHelper.setText(htmlContent, true); // O segundo parâmetro `true` indica que é HTML

             // Adicionando a imagem embutida no e-mail
            // Certifique-se de que o caminho da imagem esteja correto
            FileSystemResource logo = new FileSystemResource("src/main/resources/images/logo.jpeg");  // Caminho para a imagem
            messageHelper.addInline("logo", logo);

            javaMailSender.send(mimeMessage);
            return "Email enviado com sucesso!";

        } catch (Exception e) {
            System.out.println(e);
            return "Erro ao enviar email!";
        }
    }

}