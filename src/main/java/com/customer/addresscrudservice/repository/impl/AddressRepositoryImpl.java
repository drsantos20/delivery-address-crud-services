package com.customer.addresscrudservice.repository.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.customer.addresscrudservice.exception.GenericAPIException;
import com.customer.addresscrudservice.model.Address;
import com.customer.addresscrudservice.repository.AddressRepository;



/**
 * Address mock generator class
 * 
 * @author andre.s.mafra
 * 
 */
@Component
public class AddressRepositoryImpl implements AddressRepository {

	/**
	 * Address persistence data
	 */
	private Set<Address> addressData;

	@PostConstruct
	public void init() {
		addressData = new HashSet<Address>();
	}

	/**
	 * @see AddressPersistence
	 */
	public String insert(Address address) {
		final String addressId = generateId();
		address.setId(addressId);
		addressData.add(address);
		return addressId;
	}

	/**
	 * @throws GenericAPIException
	 * @see AddressPersistence
	 */
	public String update(Address address) throws GenericAPIException {

		Address found = isInCollection(address.getId());

		if (found != null) {

			Address copy = null;

			try {

				copy = (Address) found.clone();

				if (StringUtils.isNotBlank(address.getNeighbourhood())) {
					copy.setNeighbourhood(address.getNeighbourhood());
				}

				if (StringUtils.isNotBlank(address.getExtraField())) {
					copy.setExtraField(address.getExtraField());
				}

				copy.setZipCodeNumber(address.getZipCodeNumber());
				copy.setCity(address.getCity());
				copy.setState(address.getState());
				copy.setNumber(address.getNumber());
				copy.setStreet(address.getStreet());

				addressData.remove(found);
				addressData.add(copy);

			} catch (CloneNotSupportedException e) {
				throw new GenericAPIException();
			}
			
			return address.getId();

		} else {
			return insert(address);
		}
	}

	/**
	 * @see AddressPersistence
	 */
	public Address get(String id) {
		return isInCollection(id);
	}

	/**
	 * @see AddressPersistence
	 */
	public boolean delete(String id) {
		Address toDelete = isInCollection(id);
		return toDelete != null ? addressData.remove(toDelete) : false;
	}

	/**
	 * Verify if the address is in collection
	 * 
	 * @param address
	 */
	private Address isInCollection(String id) {

		for (Address local : addressData) {
			if (local.getId().equalsIgnoreCase(id)) {
				return local;
			}
		}

		return null;
	}

	/**
	 * This method generate a random id
	 * 
	 * @return
	 */
	private static final String generateId() {
		return UUID.randomUUID().toString();
	}

	/**
	 * Testing purposes only
	 */
	public List<Address> listAll() {
		return new ArrayList<Address>(addressData);
	}

}
