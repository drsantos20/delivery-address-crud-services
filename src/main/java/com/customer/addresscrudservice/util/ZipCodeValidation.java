package com.customer.addresscrudservice.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.customer.addresscrudservice.exception.InvalidAddressInputException;
import com.customer.addresscrudservice.exception.ZipCodeException;
import com.customer.addresscrudservice.model.Address;
import com.customer.addresscrudservice.model.ZipCode;

@Component
public class ZipCodeValidation {

   public static final void validateCEP(final ZipCode zipCode) throws ZipCodeException {

      if (zipCode == null) {
         throw new ZipCodeException("invalid zip code");
      }
      else if (!StringUtils.isNumeric(zipCode.getId()) || zipCode.getId().length() != 8) {
         throw new ZipCodeException("zip code " + zipCode.getId() + " is invalid");
      }

   }

   public static final void validateInput(Address address) throws InvalidAddressInputException {

      if (address == null || StringUtils.isBlank(address.getZipCodeNumber()) || StringUtils.isBlank(address.getCity()) || StringUtils.isBlank(address.getState()) || StringUtils.isBlank(address.getNumber()) || StringUtils.isBlank(address.getStreet())) {

         throw new InvalidAddressInputException();
      }
   }

}
