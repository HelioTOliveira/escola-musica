package escola.musica.servico.impl;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import escola.musica.servico.EnvioEmailServico;

@Service
public class EnvioEmailServicoImpl implements EnvioEmailServico{

	@Override
//	@Scheduled(fixedDelay = 5000) rodar em tempo em tempo
	//@Scheduled(cron ="30 42 1 * * *") // rodar as 30 segundos 42 minutos 1 hora * qualquer dia * qualquer mes * qualquer ano
	public void enviarEmail() {
		
		try {
			JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
			mailSenderImpl.setHost("smtp.gmail.com");
			mailSenderImpl.setPort(587);
			mailSenderImpl.setProtocol("smtp");
			mailSenderImpl.setUsername("??????????");
			mailSenderImpl.setPassword("********");
			mailSenderImpl.setDefaultEncoding("utf-8");
			
			Properties properties = new Properties();
			properties.setProperty("username", "???????????");
			properties.setProperty("password", "***********");
			properties.setProperty("mail.smtp.auth", "true");
			properties.setProperty("mail.smtp.starttls.enable", "true");
			properties.setProperty("mail.transport.protocol", "smtp");
			mailSenderImpl.setJavaMailProperties(properties);
			
			MimeMessage msg = mailSenderImpl.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(msg, false);
			helper.setFrom("????????????");
			helper.setSubject("Teste de envio de email");
			helper.setText("Conteúdo do email...", false);
			//Enviar para 
			helper.addTo("?????????????");
			//Envio do email
			mailSenderImpl.send(msg);
			
			System.out.println("Enviando email...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

}
