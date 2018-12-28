package com.sti.bootcamp.controller;

import java.util.Date;
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

import com.mysql.jdbc.StringUtils;
import com.sti.bootcamp.dao.AccountDao;
import com.sti.bootcamp.dao.CustomerDao;
import com.sti.bootcamp.exception.MyCustomException;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Customer;
import com.sti.bootcamp.model.dto.AccountDto;
import com.sti.bootcamp.model.dto.CommonResponse;
import com.sti.bootcamp.model.dto.CustomerDto;

@RestController
@RequestMapping("/account")
@SuppressWarnings("rawtypes")
public class AccountController {
	private static final Logger LOGGER  = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	public AccountDao accountDao;
	
	@Autowired
	public CustomerDao customerDao;
	
//	@GetMapping("/account")
//	public String hello(@RequestParam(value="id", defaultValue="") int id){
//		try {
//			Account account = accountDao.getById(id);
//			if(account==null) {
//				return "Data tidak ditemukan";
//			}else 
//				return "Hello your account number = "+account.getAccountnumber()+"  your balance = "+account.getBalance();
//		} catch (NumberFormatException e) {
//			return "Salah Format Input";
//		} catch (MyCustomException e) {
//			return "Harus Angka";
//		}	
//		catch (Exception e){
//			return String.format("terjadi kesalahan sistem %s:", e.getMessage());
//		}
//	}
//	
//	@PostMapping("/account")
//	public Account account1(@RequestBody Account account ) throws MyCustomException {	
//		
//		return accountDao.save(account);
//	}
//	
//	@PutMapping("/account")
//	public Account account(@RequestBody Account account ) throws MyCustomException{
//		return accountDao.save(account);
//	}
//	
//	@DeleteMapping("/account/{id}")
//	public void delcustomer (@PathVariable ("id") Account account ) throws MyCustomException {
//		accountDao.delete(account);
//	}
//	
//	@GetMapping("/list")
//	public List<Account> getList() throws MyCustomException{
//		List<Account> list = accountDao.getList();
//		try {
//			if(list != null && list.isEmpty()) {
//				return list;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return list;
//	}
//	
//	@GetMapping(value="/listbycustomer")
//	public List<Account> getList(@RequestParam(name="customer", defaultValue="") String id) throws MyCustomException{
//		if(!StringUtils.isNullOrEmpty(id)) {
//			Customer checkCustomer = customerDao.getById(Integer.parseInt(id));
//			if(checkCustomer==null) {
//				throw new MyCustomException("customer tidak ditemukan");
//			}
//			return accountDao.getListByCustomer(checkCustomer);
//		}else {
//			return accountDao.getList();
//		}
//	}
	@PostMapping(value="")
	public CommonResponse insert(@RequestBody AccountDto accountDto) throws MyCustomException {
		try {
			Account account = modelMapper.map(accountDto, Account.class);
			account.setAccountnumber(0);
			account =  accountDao.save(account);
			
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (MyCustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@PutMapping(value="")
	public CommonResponse update(@RequestBody AccountDto account) throws MyCustomException {
		try {
			Account checkAccount = accountDao.getById(account.getAccountnumber());
			if(checkAccount == null) {
				return new CommonResponse("14", "Account tidak ditemukan");
			}
//			if(account.getBalance()!=null) {
//				checkAccount.setBalance(account.getBalance());
//			}
//			if(account.getOpendate()!=null) {
//				checkAccount.setOpendate(account.getOpendate());
//			}
			if(account.getCustomer()!=null) {
				checkAccount.setCustomer(account.getCustomer());
			}
			
			checkAccount =  accountDao.save(checkAccount);
			
			return new CommonResponse<AccountDto>(modelMapper.map(checkAccount, AccountDto.class));
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
			Account checkAccount = accountDao.getById(id);
			if(checkAccount == null) {
				return new CommonResponse("06", "account tidak ditemukan");
			}
			accountDao.delete(checkAccount);
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
			List<Account> accounts;
			
			if(!StringUtils.isNullOrEmpty(customer)) {
				Customer checkCustomer = customerDao.getById(Integer.parseInt(customer));
				if(checkCustomer==null) {
					throw new MyCustomException("customer tidak ditemukan");
				}
				accounts =  accountDao.getListByCustomer(checkCustomer);
			}else {
				accounts = accountDao.getList();
			}
			LOGGER.info("account get list, params : {}", customer);
			
		
			
			return new CommonResponse<List<AccountDto>>(accounts.stream().map(acc -> modelMapper.map(acc, AccountDto.class)).collect(Collectors.toList()));
			
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
