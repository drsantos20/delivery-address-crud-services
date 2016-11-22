package com.customer.addresscrudservice.service;

import java.util.List;

import com.customer.addresscrudservice.exception.GenericAPIException;
import com.customer.addresscrudservice.model.Address;


/**
 * Address service layer
 * 
 * @author andre.s.mafra
 * 
 */
public interface AddressService {

	/**
	 * Insert a Address model into db
	 * 
	 * @param address
	 * @return
	 */
	String insertAddress(Address address);

	/**
	 * Update a Address model into db
	 * 
	 * @param address
	 * @throws GenericAPIException
	 */
	String updateAddress(Address address) throws GenericAPIException;

	/**
	 * Get a Address model from db
	 * 
	 * @param id
	 * @return
	 */
	Address getAddress(String id);

	/**
	 * Delete a Address model from db
	 * 
	 * @param id
	 */
	boolean deleteAddress(String id);

	/**
	 * Get all address for testing purposes only
	 * 
	 * @return
	 */
	List<Address> getAll();
}
