package fr.eni.projetencheres.bll;

import java.time.LocalDate;
import java.util.List;

import fr.eni.projetencheres.bo.Takeout;
import fr.eni.projetencheres.dal.DAOFactory;
import fr.eni.projetencheres.dal.DataException;
import fr.eni.projetencheres.dal.TakeoutDAO;

public class TakeoutManager {
	private static TakeoutDAO takeoutDAO;

	public static TakeoutDAO getIntance() {
		if (takeoutDAO == null) {
			takeoutDAO = DAOFactory.getTakeoutDAO();
		}

		return takeoutDAO;
	}
	
	public static void addTakeout(Takeout t) throws DataException, BusinessException {
		Utils.verifyStringField("rue", t.getStreet(), 1, 128);
		Utils.verifyStringField("code postale",t.getZipCode(), 0, 6);
		Utils.verifyStringField("ville", t.getCity(), 1, 128);
		
		t.setDate(LocalDate.now().plusDays(2));
		
		TakeoutManager.getIntance().insertTakeout(t);
	}
	
	public static List<Takeout> getAllTakeouts() throws DataException {
		return TakeoutManager.getIntance().selectAllTakeouts();
	}
}
