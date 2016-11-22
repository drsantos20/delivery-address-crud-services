package com.customer.addresscrudservice.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.customer.addresscrudservice.exception.GenericAPIException;
import com.customer.addresscrudservice.exception.InvalidAddressInputException;
import com.customer.addresscrudservice.exception.ResourceNotFoundException;
import com.customer.addresscrudservice.exception.ZipCodeException;
import com.customer.addresscrudservice.model.Address;
import com.customer.addresscrudservice.model.BadStatus;
import com.customer.addresscrudservice.model.ZipCode;
import com.customer.addresscrudservice.service.AddressService;
import com.customer.addresscrudservice.service.ZipCodeService;
import com.customer.addresscrudservice.util.ZipCodeValidation;


@Controller
@RequestMapping("/")
public class CrudController {

	/**
	 * AddressService service
	 */
	@Autowired
	private AddressService addressService;

	/**
	 * Cep service
	 */
	@Autowired
	private ZipCodeService zipCodeService;

	/**
	 * Create address endpoint API
	 * 
	 * @param address
	 * @throws InvalidAddressInputException
	 * @throws ZipCodeException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	Address createAddress(@RequestBody Address address) throws InvalidAddressInputException, ZipCodeException {

		ZipCodeValidation.validateInput(address);
		this.validateCepAPI(address);

		final String id = addressService.insertAddress(address);
		address.setId(id);

		return address;
	}

	/**
	 * @param address
	 * @throws ZipCodeException
	 */
	private void validateCepAPI(Address address) throws ZipCodeException {

	   ZipCode zipCode = zipCodeService.validateZipCode(address.getZipCodeNumber());

		if (zipCode == null) {
			throw new ZipCodeException();
		}

		if (StringUtils.isNotBlank(zipCode.getStatusMessage())) {
			throw new ZipCodeException(zipCode.getStatusMessage());
		}
	}

	/**
	 * Update address endpoint API
	 * 
	 * @param address
	 * @throws InvalidAddressInputException
	 * @throws GenericAPIException
	 * @throws ZipCodeException
	 */
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void updateAddress(@RequestBody Address address) throws InvalidAddressInputException, GenericAPIException,
			ZipCodeException {

		ZipCodeValidation.validateInput(address);
		this.validateCepAPI(address);
		addressService.updateAddress(address);
	}

	/**
	 * Get address endpoint API
	 * 
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	Address getAddress(@PathVariable String id) throws ResourceNotFoundException {

		Address address = addressService.getAddress(id);

		if (address == null) {
			throw new ResourceNotFoundException();
		}

		return address;
	}

	/**
	 * List all address for testing purposes
	 * 
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Address> getAll() throws ResourceNotFoundException {
		return addressService.getAll();
	}

	/**
	 * Delete address endpoint API
	 * 
	 * @param id
	 * @throws ResourceNotFoundException
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteAddress(@PathVariable String id) throws ResourceNotFoundException {

		if (!addressService.deleteAddress(id)) {
			throw new ResourceNotFoundException();
		}
	}

	/**
	 * Exception handler for Invalid input data
	 * 
	 * @return
	 */
	@ExceptionHandler(InvalidAddressInputException.class)
	public @ResponseBody
	BadStatus exceptionHandler() {
		return new BadStatus("Erro ao incluir/alterar o AddressService");
	}

	/**
	 * Exception handler for generic error
	 * 
	 * @return
	 */
	@ExceptionHandler(GenericAPIException.class)
	public @ResponseBody
	BadStatus exceptionGenericHandler() {
		return new BadStatus("Erro generico na API");
	}

	/**
	 * Exception handler for cep Validation
	 * 
	 * @return
	 */
	@ExceptionHandler(ZipCodeException.class)
	public @ResponseBody
	BadStatus exceptionCepValidationHandler(ZipCodeException ex) {
		return new BadStatus(ex.getMessage() != null ? ex.getMessage() : "Erro na validacao do cep");
	}

}
