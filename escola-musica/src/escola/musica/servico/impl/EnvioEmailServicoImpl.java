package escola.musica.servico.impl;

import org.springframework.scheduling.annotation.Scheduled;

import org.springframework.stereotype.Service;
import escola.musica.servico.EnvioEmailServico;

@Service
public class EnvioEmailServicoImpl implements EnvioEmailServico{

	@Override
//	@Scheduled(fixedDelay = 5000) rodar em tempo em tempo
	@Scheduled(cron ="30 42 1 * * *") // rodar as 30 segundos 42 minutos 1 hora * qualquer dia * qualquer mes * qualquer ano
	public void enviarEmail() {
		// TODO Auto-generated method stub
		System.out.println("Enviando email...");
	}

}
