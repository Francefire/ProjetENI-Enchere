package fr.eni.projetencheres.bll;

public class Utils {
	public static void verifyStringField(String fieldName, String fieldString, int min, int max)
			throws BusinessException {
		if (fieldString == null || fieldString.isEmpty()) {
			throw new BusinessException(String.format(BusinessException.BLL_FIELD_EMPTY_ERROR, fieldName));
		}

		if (fieldString.length() < min || fieldString.length() > max+1) {
			throw new BusinessException(String.format(BusinessException.BLL_FIELD_BOUNDS_ERROR, fieldName, min, max));
		}
	}
	
	public static void verifyMoneyField(String fieldName, double fieldPrice, int min) throws BusinessException {
		if (fieldPrice < min) {
			throw new BusinessException(String.format(BusinessException.BLL_MONEY_NOT_ENOUGH_ERROR, fieldName, min));
		}
	}
}
