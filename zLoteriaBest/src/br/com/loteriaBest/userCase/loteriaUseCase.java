package br.com.loteriaBest.userCase;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.com.loteriaBest.entities.loteriaEntity;
import br.com.loteriaBest.entities.userEntity;
import br.com.loteriaBest.repository.loteriaRepository;
import br.com.loteriaBest.repository.userRepository;

public class loteriaUseCase {
	public loteriaEntity buscaLoteria(int pEmpresa, userEntity pUsuarioAutenticacao, loteriaEntity pLoteria) {

		loteriaRepository loteriaRepository = new loteriaRepository();
		loteriaEntity loteriaBusca = new loteriaEntity();

		pLoteria.setAtivo(0); // para consulta vai zero, pois tente que quer consultar todas idependente se ta
								// ativo ou não
		loteriaBusca = loteriaRepository.consultaLoteria(pEmpresa, pUsuarioAutenticacao, pLoteria);

		return loteriaBusca;
	}

	private boolean verificaPermissao(int pIndicador, int pEmpresa, userEntity pUsuarioAutenticado,
			loteriaEntity pLoteria) {

		loteriaRepository loteriaRepository = new loteriaRepository();
		loteriaEntity loteriaBusca = new loteriaEntity();

		loteriaBusca = loteriaRepository.consultaLoteria(pEmpresa, pUsuarioAutenticado, pLoteria);
		if (pIndicador == 0) { // regras para cadastro
			if (pLoteria.getCodigo() != 0) {
				JOptionPane.showMessageDialog(null, "Para cadastrar não insira o codigo da loteria!");
				return false;
			}
			if (pLoteria.getAtivo() == 0) {
				JOptionPane.showMessageDialog(null, "Para cadastrar selecione se será estará ativa ou inativa!");
				return false;
			}
		} else { // regras para alteração
			if (!loteriaBusca.getMsg_erro().trim().equals("")) {
				JOptionPane.showMessageDialog(null, "Você não pode alterar uma loteria que ainda não existe!");
				return false;
			}

		}

		if (pUsuarioAutenticado.getNivel() > 2) {
			JOptionPane.showMessageDialog(null, "Apenas diretor/gerente pode incluir/alterar loteria!");
			return false;
		}

		if ((pLoteria.getPorcentagem_acrescimo() + pLoteria.getPorcentagem_comissao()
				+ pLoteria.getPorcentagem_empresa()) == 100) {
			JOptionPane.showMessageDialog(null, "A soma das % tem que ser 100%!");
			return false;
		}

		if (!pLoteria.getNome().trim().equals("")) {
			JOptionPane.showMessageDialog(null, "O nome tem que ser preenchido!");
			return false;
		}

		if (pLoteria.getNumero_maximo() == 0 || pLoteria.getNumero_minimo() == 0) {
			JOptionPane.showMessageDialog(null, "O primeiro e último numero devem ser informados!");
			return false;
		}

		if (pLoteria.getPremio_inicial() == 0) {
			JOptionPane.showMessageDialog(null, "Premio inicial deve ser informado!");
			return false;
		}

		if (pLoteria.getPremio_final() == 0) {
			JOptionPane.showMessageDialog(null, "Premio final não deve ser informado!");
			return false;
		}

		if (pLoteria.getValor_bilhete() == 0) {
			JOptionPane.showMessageDialog(null, "Valor do bilhete deve ser informado!");
			return false;
		}

		return true;
	}
	
	public loteriaEntity criarLoteria(int pEmpresa, userEntity pUsuarioAutenticado, loteriaEntity pLoteria)
			throws SQLException {

		loteriaEntity loteriaInclusao = new loteriaEntity();

		if (verificaPermissao(0, pEmpresa, pUsuarioAutenticado, pLoteria)) {
			loteriaRepository loteriaRepository = new loteriaRepository();
			loteriaRepository.cadastroLoteriaNova(pEmpresa, pLoteria);

			loteriaInclusao = loteriaRepository.consultaLoteria(pEmpresa, pUsuarioAutenticado, pLoteria);

			JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!!");
		} else {
			loteriaInclusao = pLoteria;
			loteriaInclusao.setMsg_erro("");
		}

		return loteriaInclusao;
	}

	public loteriaEntity alteraLoteria(int pEmpresa, userEntity pUsuarioAutenticado, loteriaEntity pLoteria)
			throws SQLException {

		loteriaEntity loteriaAlteracao = new loteriaEntity();

		if (verificaPermissao(1, pEmpresa, pUsuarioAutenticado, pLoteria)) {
			loteriaRepository loteriaRepository = new loteriaRepository();
			loteriaRepository.alteraLoteria(pEmpresa, pLoteria);

			loteriaAlteracao = loteriaRepository.consultaLoteria(pEmpresa, pUsuarioAutenticado, pLoteria);

			JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso!!");
		} else {
			loteriaAlteracao = pLoteria;
			loteriaAlteracao.setMsg_erro("");
		}

		return loteriaAlteracao;
	}
}
