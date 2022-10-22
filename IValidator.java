package task1;

import task1.exception.ValidationException;

public interface IValidator {
 String validate(String password) throws ValidationException;
}
