package com.customer.addresscrudservice.repository;

import java.util.List;

import com.customer.addresscrudservice.exception.GenericAPIException;
import com.customer.addresscrudservice.model.Address;


/**
 * Address persistence sign methods
 * 
 * @author andre.s.mafra
 * 
 */
public interface AddressRepository {

	/**
	 * Insert a Address model into db
	 * 
	 * @param address
	 * @return
	 */
	String insert(Address address);

	/**
	 * Update a Address model into db
	 * 
	 * @param address
	 * @throws GenericAPIException
	 */
	String update(Address address) throws GenericAPIException;

	/**
	 * Get a Address model from db
	 * 
	 * @param id
	 * @return
	 */
	Address get(String id);

	/**
	 * Delete a Address model from db
	 * 
	 * @param id
	 */
	boolean delete(String id);

	/**
	 * List all address (Testing purposes)
	 * 
	 * @return
	 */
	List<Address> listAll();
}
