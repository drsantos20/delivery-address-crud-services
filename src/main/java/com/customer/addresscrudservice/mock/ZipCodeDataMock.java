package com.customer.addresscrudservice.mock;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.customer.addresscrudservice.model.ZipCode;


@Component
public class ZipCodeDataMock {
   
   /**
    * ZipCode available list
    */
   private List<ZipCode> availableZipCodeList;

   /**
    * ZipCode generator
    */
   @PostConstruct
   public void generatorInit() {

      availableZipCodeList = new ArrayList<ZipCode>(1);

      ZipCode zipcode1 = new ZipCode();
      zipcode1.setId("01202001");
      zipcode1.setCity("Georgetown");
      zipcode1.setState("SC");
      zipcode1.setStreet("Holly Street");
      availableZipCodeList.add(zipcode1);

      ZipCode zipcode2 = new ZipCode();
      zipcode2.setId("01202002");
      zipcode2.setCity("Lincoln");
      zipcode2.setState("NE");
      zipcode2.setStreet("State Road");
      availableZipCodeList.add(zipcode2);

      ZipCode zipcode3 = new ZipCode();
      zipcode3.setId("01202003");
      zipcode3.setCity("Burlington");
      zipcode3.setState("MA");
      zipcode3.setStreet("Bridle Court");
      availableZipCodeList.add(zipcode3);

      ZipCode zipcode4 = new ZipCode();
      zipcode4.setId("01202004");
      zipcode4.setCity("Millville");
      zipcode4.setState("NJ");
      zipcode4.setStreet("1st Street");
      availableZipCodeList.add(zipcode4);

      ZipCode zipcode5 = new ZipCode();
      zipcode5.setId("09953100");
      zipcode5.setCity("Summerville");
      zipcode5.setState("SC");
      zipcode5.setStreet("Bayport Ave");
      availableZipCodeList.add(zipcode5);

      ZipCode zipcode6 = new ZipCode();
      zipcode6.setId("10000000");
      zipcode6.setCity("Guarulhos");
      zipcode6.setState("SP");
      zipcode6.setStreet("Rua Netshoes");
      availableZipCodeList.add(zipcode6);

      ZipCode zipcode7 = new ZipCode();
      zipcode7.setId("88456000");
      zipcode7.setCity("Janesville");
      zipcode7.setState("WI");
      zipcode7.setStreet("Bay Meadows St");
      availableZipCodeList.add(zipcode7);
   }

   /**
    * @return the availableZipCodeList
    */
   public List<ZipCode> getAvailableZipCodeList() {
      return availableZipCodeList;
   }

   /**
    * @param availableZipCodeList
    *            the availableZipCodeList to set
    */
   public void setAvailableZipCodeList(List<ZipCode> availableZipCodeList) {
      this.availableZipCodeList = availableZipCodeList;
   }

}
