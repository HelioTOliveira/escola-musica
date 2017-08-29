package escola.musica.servico;

import java.util.List;

import escola.musica.modelo.Aluno;
import escola.musica.modelo.Matricula;
import escola.musica.modelo.MatriculaVO;
import escola.musica.modelo.ParametrosBuscaMatriculas;

public interface MatriculaServico {

	public List<MatriculaVO> listarTodas();
	public void salvar(Matricula matricula);
	public List<Matricula> listarTodasAtivas();
	public Matricula obterPorId(Integer id);
	public List<Matricula> listarPorCurso(Integer id);
	public void pesquisar(ParametrosBuscaMatriculas parametros);
}
