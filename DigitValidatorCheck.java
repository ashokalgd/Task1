package task1.Service;

import task1.IValidator;
import task1.exception.ValidationException;

public class DigitValidatorCheck implements IValidator {

	@Override
	public String validate(String password) throws ValidationException {
		boolean exists = false;
		if(null != password)
		for(int i = 0; i < password.length();i++) {
			if(!Character.isDigit(password.charAt(i))) {
				exists = false;
			}else {
				exists = true;
				break;
			}
		}
		if(exists)
			return "SUCCESS";
		else
			throw new ValidationException("Password should have one number atleast");
	}

}
