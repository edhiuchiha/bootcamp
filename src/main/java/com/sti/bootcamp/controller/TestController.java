package com.sti.bootcamp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.bootcamp.dao.CustomerDao;
import com.sti.bootcamp.exception.MyCustomException;
import com.sti.bootcamp.model.Customer;
import com.sti.bootcamp.model.dto.CommonResponse;
import com.sti.bootcamp.model.dto.CustomerDto;

@RestController
@RequestMapping("/customer")
@SuppressWarnings("rawtypes")
public class TestController {
	private static final Logger LOGGER  = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/{id}")
	public CommonResponse getById(@PathVariable("id") String id) throws MyCustomException {		
		LOGGER.info("id : {}", id);
		try {
			Customer customer = customerDao.getById(Integer.valueOf(id));
			
			return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
		} catch (MyCustomException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

//	public String hello(@RequestParam(value="id", defaultValue="") String id){
//		try {
//			com.sti.bootcamp.model.Customer customer = customerDao.getById(Integer.valueOf(id));
//			if(customer==null) {
//				return "Data tidak ditemukan";
//			}else 
//				return "Hello "+customer.getFirstname();
//		} catch (NumberFormatException e) {
//			return "Salah Format Input";
//		} catch (Exception e){
//			return String.format("terjadi kesalahan sistem %s:", e.getMessage());
//		}
//	}
	
//	@PostMapping("/customer")
//	public Customer create(@RequestBody Customer customer) throws MyCustomException {	
//		
//	 customerDao.save(customer);
//	 return customer;	
//	}
//	
//	@PutMapping("/customer")
//	public Customer upcustomer(@RequestBody Customer customer ) throws MyCustomException{
//		return customerDao.save(customer);
//	}
	
//	@DeleteMapping("/customer/{id}")
//	public void delcustomer (@PathVariable ("id") int id) throws MyCustomException {
//		Customer check = customerDao.getById(id);
//		if(check!=null)
//			customerDao.delete(check);
//	}
	
//	@GetMapping("/customer")
//	public List<Customer> getList() throws MyCustomException{	
//		return customerDao.getList();
//	}
	@PostMapping(value="")
	public CommonResponse insert(@RequestBody CustomerDto customerDto) throws MyCustomException {
		try {
			Customer customer = modelMapper.map(customerDto, Customer.class);
			customer.setCustomernumber(0);
			customer =  customerDao.save(customer);
			
			return new CommonResponse<CustomerDto>(modelMapper.map(customer, CustomerDto.class));
		} catch (MyCustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@PutMapping(value="")
	public CommonResponse update(@RequestBody CustomerDto customer) throws MyCustomException {
		try {
			Customer checkCustomer = customerDao.getById(customer.getCustomernumber());
			if(checkCustomer == null) {
				return new CommonResponse("14", "customer tidak ditemukan");
			}
			
			if(customer.getBirthdate()!=null) {
				checkCustomer.setBirthdate(customer.getBirthdate());
			}
			if(customer.getFirstname()!=null) {
				checkCustomer.setFirstname(customer.getFirstname());
			}
			if(customer.getLastname()!=null) {
				checkCustomer.setLastname(customer.getLastname());
			}
			if(customer.getPhonenumber()!=null) {
				checkCustomer.setPhonenumber(customer.getPhonenumber());
			}
			if(customer.getPhonetype()!=null) {
				checkCustomer.setPhonetype(customer.getPhonetype());
			}
			
			checkCustomer =  customerDao.save(checkCustomer);
			
			return new CommonResponse<CustomerDto>(modelMapper.map(checkCustomer, CustomerDto.class));
		} catch (MyCustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@DeleteMapping(value="/{id}")
	public CommonResponse delete(@PathVariable("id") Integer id) throws MyCustomException {
		try {
			Customer checkCustomer = customerDao.getById(id);
			if(checkCustomer == null) {
				return new CommonResponse("06", "customer tidak ditemukan");
			}
			customerDao.delete(checkCustomer);
			return new CommonResponse();
		} catch (MyCustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@GetMapping(value="list")
	public CommonResponse getList(@RequestParam(name="customer", defaultValue="") String customer) throws MyCustomException{
		try {
			LOGGER.info("customer get list, params : {}", customer);
			List<Customer> customers = customerDao.getList();
		
			return new CommonResponse<List<CustomerDto>>(customers.stream().map(cust -> modelMapper.map(cust, CustomerDto.class)).collect(Collectors.toList()));
		} catch (MyCustomException e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}


}
