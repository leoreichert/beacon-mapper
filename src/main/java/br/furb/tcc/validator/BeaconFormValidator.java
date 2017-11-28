package br.furb.tcc.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.furb.tcc.model.Beacon;

@Component
public class BeaconFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Beacon.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		Beacon beacon = (Beacon) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uid", "NotEmpty.beaconForm.uid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "urlid", "NotEmpty.beaconForm.urlid");

		if(beacon.getUrlid().equalsIgnoreCase("none")){
			errors.rejectValue("uid", "NotEmpty.beaconForm.uid");
		}
		
		if(beacon.getUrlid().equalsIgnoreCase("none")){
			errors.rejectValue("urlid", "NotEmpty.beaconForm.urlid");
		}
	}

}