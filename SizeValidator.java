package task1.Service;

import task1.IValidator;
import task1.exception.ValidationException;

public class SizeValidator implements IValidator {

	@Override
	public String validate(String password) throws ValidationException {
		if(null != password && password.length() >= 8) {
			return "SUCCESS";
		}
		else {
			throw new ValidationException("Password size should be more than 8 characters");
		}
	}

}
