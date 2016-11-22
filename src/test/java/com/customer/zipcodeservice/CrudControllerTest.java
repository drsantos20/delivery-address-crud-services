package com.customer.zipcodeservice;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.customer.addresscrudservice.AddressCrudServiceApplication;
import com.customer.addresscrudservice.controller.CrudController;
import com.customer.addresscrudservice.model.Address;
import com.customer.addresscrudservice.model.ZipCode;
import com.customer.addresscrudservice.service.AddressService;
import com.customer.addresscrudservice.service.ZipCodeService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AddressCrudServiceApplication.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8090"})
public class CrudControllerTest {

	private MockMvc mockMvc;

	@Mock
	private AddressService addressService;

	@Mock
	private ZipCodeService zipCodeService;

	@InjectMocks
	private CrudController crudController;

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(crudController).dispatchOptions(true).build();
	}

	/**
	 * Create address test
	 * 
	 * @throws Exception
	 */
	@Test
	public void createAddressTest() throws Exception {

		final String zipCodeNumber = "01202001";
		final String id = "00001";

      Address addressIn = new Address();
      addressIn.setZipCodeNumber(zipCodeNumber);
      addressIn.setCity("Georgetown");
      addressIn.setState("SC");
      addressIn.setNumber("76");
      addressIn.setStreet("Holly Street");

		ZipCode customCep = new ZipCode();
		customCep.setId(zipCodeNumber);

		when(zipCodeService.validateZipCode(zipCodeNumber)).thenReturn(customCep);
		when(addressService.insertAddress(addressIn)).thenReturn(id);
		when(crudController.createAddress(addressIn)).thenReturn(Mockito.any(Address.class));

		String body = "{\"street\":\"Holly Street\",\"number\":\"76\",\"zipCodeNumber\":\"01202001\",\"city\":\"Georgetown\",\"state\":\"SC\"}";

		mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(body)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.zipCodeNumber", is(zipCodeNumber)));
	}

	/**
	 * Update address test
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateAddressTest() throws Exception {

		final String zipCodeNumber = "01202001";
		final String id = "00001";

		Address addressIn = new Address();
		addressIn.setZipCodeNumber(zipCodeNumber);
		addressIn.setCity("Lincoln");
		addressIn.setState("NE");
		addressIn.setNumber("192");
		addressIn.setStreet("State Road");

		Address addressOut = new Address();
	   addressOut.setNumber("192");
	   addressOut.setStreet("State Road");
		addressOut.setId(id);
		addressOut.setZipCodeNumber(zipCodeNumber);
		addressOut.setCity("Lincoln");
		addressOut.setState("NE");


		ZipCode zipCode = new ZipCode();
		zipCode.setId(zipCodeNumber);

		when(zipCodeService.validateZipCode(zipCodeNumber)).thenReturn(zipCode);
		when(addressService.updateAddress(addressIn)).thenReturn(id);

		String body = "{\"street\":\"State Road\",\"number\":\"192\",\"zipCodeNumber\":\"01202001\",\"city\":\"NE\",\"state\":\"NE\"}";

		mockMvc.perform(put("/").contentType(MediaType.APPLICATION_JSON).content(body)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().is(HttpStatus.NO_CONTENT.value()));
	}

	/**
	 * Delete address test
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteAddressTest() throws Exception {

		final String id = "00001";

		when(addressService.deleteAddress(id)).thenReturn(true);
		
		mockMvc.perform(delete("/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk());
	}

	/**
	 * Get address test
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAddressTest() throws Exception {

		final String zipCodeNumber = "01202001";
		final String id = "00001";

      Address addressOut = new Address();
      addressOut.setCity("Fairmont");
      addressOut.setState("WV");
      addressOut.setNumber("7264");
      
      addressOut.setId(id);
      addressOut.setZipCodeNumber(zipCodeNumber);
      addressOut.setStreet("Kingston Lane");

		when(addressService.getAddress(id)).thenReturn(addressOut);
		when(crudController.getAddress(id)).thenReturn(addressOut);

		mockMvc.perform(get("/" + id).contentType(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.zipCodeNumber", is(zipCodeNumber)));
	}

}
