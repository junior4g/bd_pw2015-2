package bancodados.cadastroaluno;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = "/bancodados/CadastroAluno")
public class CadastroAlunoControle extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String paramMatricula = req.getParameter("matricula");
		String matricula = paramMatricula == null ? "" : paramMatricula;

		String paramNome = req.getParameter("nome");
		String nome = paramNome == null ? "" : paramNome;

		String paramFone = req.getParameter("fone");
		String fone = paramFone == null ? "" : paramFone;

		String paramCpf = req.getParameter("cpf");
		String cpf = paramCpf == null ? "" : paramCpf;

		String paramEvento = req.getParameter("evento");
		String evento = paramEvento == null ? "" : paramEvento;

		Aluno aluno = new Aluno();
		aluno.setMatricula(matricula);
		aluno.setNome(nome);
		aluno.setFone(fone);
		aluno.setCpf(cpf);

		if (evento.equals("Incluir")) {
			if (!matricula.equals("")) {
				aluno.incluir();
			}
		} else if (evento.equals("Alterar")) {
			if (!matricula.equals("")) {
				aluno.alterar(matricula, nome, fone, cpf);
			}
		} else if (evento.equals("Excluir")) {
			if (!matricula.equals("")) {
				aluno.excluir(matricula);
			}
		}

		req.setAttribute("aluno", aluno); // Passando um objeto para o JSP.

		List<Aluno> alunos = Aluno.listar();
		req.setAttribute("alunos", alunos); // Passando uma coleção para o
											// JSP.

		// Chamar o JSP apenas para mostrar o resultado.
		req.getRequestDispatcher("CadastroAlunoView.jsp").forward(req, resp);
	}

}
