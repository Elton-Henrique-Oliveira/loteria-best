package br.com.loteriaBest.userCase;

import br.com.loteriaBest.entities.companyEntity;
import br.com.loteriaBest.repository.companyRepository;

public class companyUseCase {
	public companyEntity verificaNomeEmpresa(int pEmpresa) {

		companyEntity companyEntity = new companyEntity();
		companyRepository companyRepository = new companyRepository();

		companyEntity = companyRepository.buscaEmpresa(pEmpresa);

		return companyEntity;

	}
}
