package task1.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import task1.IValidator;
import task1.exception.ValidationException;

public class PasswordValidationCheck {

	public List<String> isValidPasswordCase1(String password){
		List<String> messages = new ArrayList<>();
		if(null == password || password.isEmpty()) {
			messages.add("Password should not be null");
		}else {
			messages.add("SUCCESS");
		}
		List<IValidator> validators = getVaidators();
		
		for(IValidator validator : validators) {
			try {
				messages.add(validator.validate(password));
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				messages.add(e.getMessage());
			}
		}
		return messages;
	}
	private List<String> isValidPasswordCase2(String password) {
		List<String> messages = new ArrayList<>();
		if(null == password || password.isEmpty()) {
			messages.add("Password should not be null");
		}else {
			messages.add("SUCCESS");
		}
		List<IValidator> validators = getVaidators();
		
		for(IValidator validator : validators) {
			try {
				messages.add(validator.validate(password));
			} catch (ValidationException e) {
				// TODO Auto-generated catch block
				messages.add(e.getMessage());
			}
			if(3 == messages.stream().filter(m->m.equals("SUCCESS")).count()) {
				messages = new ArrayList<>();
				break;
			}
		}
		return messages;
	}
	private String isValidPasswordCase3(String password, IValidator iValidator) {
		try {
			return iValidator.validate(password);
		} catch (ValidationException e) {
			return e.getMessage();
		}
		
	}
	public static void main(String[] args) {
		PasswordValidationCheck passwordValidationCheck = new PasswordValidationCheck();
		//Case1
		List<String> validations = passwordValidationCheck.isValidPasswordCase1("Password@1");
		if(validations.stream().filter(p->!p.equals("SUCCESS")).collect(Collectors.toList()).isEmpty()) {
			System.out.println("Case 1 : Valid Password");		
		}
		else {
			System.out.println(validations.stream().filter(p->!p.equals("SUCCESS")).collect(Collectors.toList()));
		}
		//case 2
		List<String> validationsCase2 = passwordValidationCheck.isValidPasswordCase2("Pass");
		List<String> case2 = validationsCase2.stream().filter(msg -> !msg.equals("SUCCESS")).collect(Collectors.toList());
		if(0 != case2.size()) {
			System.out.println(case2);
		}
		else {
			System.out.println("Case 2 : Valid Password");
		}
		//case 3
		LowerCaseValidatorCheck lowerCaseValidatorCheck = new  LowerCaseValidatorCheck();
		String case3Message = passwordValidationCheck.isValidPasswordCase3("dadad", lowerCaseValidatorCheck);
		if("SUCCESS".equals(case3Message)) {
			System.out.println("Case 3 : Valid Password");
		}
		else {
			System.out.println(case3Message);
		}
		//case 4
		//4.	Assume Each verification takes 1 second to complete. How would you solve items 2 and 3 so tests can run faster?
		//Ans : Will check message on each iteration if 3 success message iteration done, will break loop so 2nd case will be faster
		//as we separate validator we can prioritize this validation and proceed further if it success, else will fail in this step and wont proceed further validations
	}
	
	
	private List<IValidator> getVaidators() {
		List<IValidator> validators = new ArrayList<>();
		validators.add(new DigitValidatorCheck());
		validators.add(new LowerCaseValidatorCheck());
		validators.add(new UpperCaseValidatorCheck());
		validators.add(new SizeValidator());
		return validators;
	}

}
