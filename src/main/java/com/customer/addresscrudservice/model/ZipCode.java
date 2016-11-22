package com.customer.addresscrudservice.model;

import lombok.Data;

@Data
public class ZipCode {
   
   private String id;
   private String street;
   private String neighbourhood;
   private String city;
   private String state;
   private String statusMessage;
}
