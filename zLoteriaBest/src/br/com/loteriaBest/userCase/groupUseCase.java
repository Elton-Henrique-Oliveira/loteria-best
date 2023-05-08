package br.com.loteriaBest.userCase;

import java.util.ArrayList;

import br.com.loteriaBest.entities.groupEntity;
import br.com.loteriaBest.repository.groupRepository;

public class groupUseCase {
	public ArrayList<groupEntity> listaGrupos(int pEmpresa) {

		ArrayList<groupEntity> groupEntity = new ArrayList<>();
		groupRepository groupRepository = new groupRepository();

		groupEntity = groupRepository.buscaGrupos(pEmpresa);

		return groupEntity;
	}

}
