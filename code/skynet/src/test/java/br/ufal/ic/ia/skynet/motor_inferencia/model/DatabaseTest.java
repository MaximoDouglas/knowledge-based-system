package br.ufal.ic.ia.skynet.motor_inferencia.model;

import org.junit.jupiter.api.Test;

import br.ufal.ic.ia.skynet.motor_inferencia.dao.CelularDAO;
import br.ufal.ic.ia.skynet.motor_inferencia.dao.DAOFactory;

class DatabaseTest {

	@Test
	void test() {
		DAOFactory daoFactory = DAOFactory.getFactory();
		CelularDAO cellDAO = daoFactory.getCelularDAO();
		Celular cell = new Celular("k6");
		
		cellDAO.save(cell);
		
	}

}
