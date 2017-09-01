package escola.musica.servico.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import escola.musica.modelo.Avaliacao;
import escola.musica.modelo.Matricula;
import escola.musica.modelo.UsuarioProfessor;
import escola.musica.servico.EnvioEmailServico;
import escola.musica.servico.MatriculaServico;

@Service
public class EnvioEmailServicoImpl implements EnvioEmailServico{

	@Autowired
	private MatriculaServico matriculaServico;
	
	@Override
	//@Scheduled(fixedDelay = 10000000) //rodar em tempo em tempo
	//@Scheduled(cron ="30 42 1 * * *") // rodar as 30 segundos 42 minutos 1 hora * qualquer dia * qualquer mes * qualquer ano
	public void enviarEmail(String assunto, String texto, List<File> anexos, String... destinatarios) {
		
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
			MimeMessageHelper helper = new MimeMessageHelper(msg, true);
			helper.setFrom("????????????");
			helper.setSubject(assunto);
			helper.setText(texto, true);// 'true' para informa que o texto e html 
			//Enviar para 
			//helper.addTo("?????????????");
			for(String destinatario : destinatarios){
				helper.addTo(destinatario);
			}
			
			if(anexos != null && !anexos.isEmpty()){
				for(File anexo : anexos){
					FileSystemResource resource = new FileSystemResource(anexo);
					helper.addAttachment(anexo.getName(), resource);
				}
			}
			
			//Envio do email
			mailSenderImpl.send(msg);
			
			System.out.println("Enviando email...");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	@Async // para executar em segundo plano
	public void enviarEmailCadastroUsuarioProfessor(UsuarioProfessor usuarioProfessor, String senha) {
		
		String assunto ="Cadastro de Usuário";
		String texto = pegarHtmlEmail("resources/email_cadastro_usuario_professor.html");
		texto = texto.replace("{professor.nome}", usuarioProfessor.getNome());
		texto = texto.replace("{professor.login}", usuarioProfessor.getLogin());
		texto = texto.replace("{professor.senha}", senha);
		
		enviarEmail(assunto, texto, null, usuarioProfessor.getEmail());
	}

	@Override
	@Async
	public void enviarEmailCorrecaoAvaliacao(Avaliacao avaliacao){
		Matricula matriculaBanco = matriculaServico.obterPorId(avaliacao.getMatricula().getId());
		String assunto = "Aviso de correção da avaliação do" + avaliacao.getBimestre().getLabel()
				+ " de " +avaliacao.getAno();
		String texto = pegarHtmlEmail("resources/email_correcao_avaliacao");
		texto = texto.replace("{aluno}", matriculaBanco.getAluno().getNome());
		texto = texto.replace("{bimestre}", avaliacao.getBimestre().getLabel());
		texto = texto.replace("{ano}", avaliacao.getAno().toString());
		
		File anexo = avaliacao.getArquivo().getFile();
		
		List<File> anexos = new ArrayList<File>();
		anexos.add(anexo);
		
		enviarEmail(assunto, texto, anexos, matriculaBanco.getAluno().getEmail());
	}
	
	private String pegarHtmlEmail(String url) {
		
		InputStream is = getClass().getResourceAsStream(url);
		BufferedInputStream bis = new BufferedInputStream(is);
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		int result;
		
		try {
			result = bis.read();
			while(result != -1){
				byte b = (byte) result;
				buf.write(b);
				result = bis.read();
			}
			return buf.toString("UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
