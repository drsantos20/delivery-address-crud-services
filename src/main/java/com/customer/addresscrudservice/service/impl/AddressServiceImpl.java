package com.customer.addresscrudservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.addresscrudservice.exception.GenericAPIException;
import com.customer.addresscrudservice.model.Address;
import com.customer.addresscrudservice.repository.AddressRepository;
import com.customer.addresscrudservice.service.AddressService;


/**
 * Endereco service layer impl
 * 
 * @author andre.s.mafra
 * 
 */
@Service
public class AddressServiceImpl implements AddressService {

	/**
	 * Persistence API
	 */
	@Autowired
	private AddressRepository addressRepository;

	/**
	 * @see EnderecoService
	 */
	public String insertAddress(Address endereco) {
		return addressRepository.insert(endereco);
	}

	/**
	 * @throws GenericAPIException
	 * @see EnderecoService
	 */
	public String updateAddress(Address endereco) throws GenericAPIException {
		return addressRepository.update(endereco);
	}

	/**
	 * @see EnderecoService
	 */
	public Address getAddress(String id) {
		return addressRepository.get(id);
	}

	/**
	 * @see EnderecoService
	 */
	public boolean deleteAddress(String id) {
		return addressRepository.delete(id);
	}

	/**
	 * @see EnderecoService
	 */
	public List<Address> getAll() {
		return addressRepository.listAll();
	}

}
