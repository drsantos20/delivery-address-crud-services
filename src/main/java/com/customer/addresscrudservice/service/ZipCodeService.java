package com.customer.addresscrudservice.service;

import com.customer.addresscrudservice.exception.ZipCodeException;
import com.customer.addresscrudservice.model.ZipCode;

public interface ZipCodeService {
   
   ZipCode getZipCodeById(final String id) throws ZipCodeException;
   
   ZipCode validateZipCode(final String id);

}
