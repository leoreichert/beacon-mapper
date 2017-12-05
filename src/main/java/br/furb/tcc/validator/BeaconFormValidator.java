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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estado", "NotEmpty.beaconForm.estado");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posicaoX", "NotEmpty.beaconForm.posicaoX");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "posicaoY", "NotEmpty.beaconForm.posicaoY");

		if ((beacon.getUid() == null) || (beacon.getUid().trim().isEmpty()) || (beacon.getUid().equalsIgnoreCase("none")))
			errors.rejectValue("uid", "NotEmpty.beaconForm.uid");
		
		if ((beacon.getUrlid() == null) || (beacon.getUrlid().trim().isEmpty()) || (beacon.getUrlid().equalsIgnoreCase("none")))
			errors.rejectValue("urlid", "NotEmpty.beaconForm.urlid");
		
		if ((beacon.getEstado() == null) || (beacon.getEstado().trim().isEmpty()) || (beacon.getEstado().equalsIgnoreCase("none")) || (beacon.getEstado().equalsIgnoreCase("--- Select ---")))
			errors.rejectValue("urlid", "NotEmpty.beaconForm.estado");
		
		if ((beacon.getPosicaoX() == null) || (beacon.getPosicaoX() == 0L))
			errors.rejectValue("urlid", "NotEmpty.beaconForm.posixaoX");
		
		if ((beacon.getPosicaoY() == null) || (beacon.getPosicaoY() == 0L))
			errors.rejectValue("urlid", "NotEmpty.beaconForm.posixaoY");
	}

}