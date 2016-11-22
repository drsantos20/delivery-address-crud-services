package com.customer.addresscrudservice.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.customer.addresscrudservice.exception.ZipCodeException;
import com.customer.addresscrudservice.mock.ZipCodeDataMock;
import com.customer.addresscrudservice.model.ZipCode;
import com.customer.addresscrudservice.service.ZipCodeService;

@Component
@PropertySource("ws.properties")
public class ZipCodeServiceImpl implements ZipCodeService {
   
   @Autowired
   private ZipCodeDataMock zipCodeDataMock;
   
   @Value("${cepservice_host}")
   private String uri;
   
   public ZipCode getZipCodeById(String id) throws ZipCodeException {

      final List<ZipCode> cepList = zipCodeDataMock.getAvailableZipCodeList();
      ZipCode zipCodeFound = null;

      for (ZipCode zipCode : cepList) {
         if (zipCode.getId().equals(id)) {
            zipCodeFound = zipCode;
            break;
         }
      }

      if (zipCodeFound == null) {
         throw new ZipCodeException("zipCode " + id + " not found");
      }

      return zipCodeFound;
   }

   @Override
   public ZipCode validateZipCode(String zipCodeNumber) {
      
      final String body = "{ \"zipCodeNumber\": \"" + zipCodeNumber + "\" }";

      ZipCode zipCode = null;

      HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      headers.setContentType(MediaType.APPLICATION_JSON);

      RestTemplate restTemplate = new RestTemplate();
      ResponseEntity<ZipCode> result = restTemplate
            .exchange(uri, HttpMethod.POST, new HttpEntity<String>(body, headers), ZipCode.class);

      if(result.getBody() != null) {
         zipCode = ((ZipCode) result.getBody());
      }
      
      return zipCode;
   }

}
