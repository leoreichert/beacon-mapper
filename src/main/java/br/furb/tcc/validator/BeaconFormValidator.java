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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uid", "NotEmpty.beaconForm.uid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "urlid", "NotEmpty.beaconForm.urlid");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estado", "NotEmpty.beaconForm.estado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posicaoX", "NotEmpty.beaconForm.posicaoX");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posicaoY", "NotEmpty.beaconForm.posicaoY");

		Beacon beacon = (Beacon) target;
		
		if ((beacon.getEstado() == null) || (beacon.getEstado().trim().isEmpty()) || (beacon.getEstado().equalsIgnoreCase("none")) || (beacon.getEstado().equalsIgnoreCase("--- Select ---")))
			errors.rejectValue("estado", "NotEmpty.beaconForm.estado");
	}

}