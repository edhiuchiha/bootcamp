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

import com.mysql.jdbc.StringUtils;
import com.sti.bootcamp.dao.AccountDao;
import com.sti.bootcamp.dao.TransactionDao;
import com.sti.bootcamp.exception.MyCustomException;
import com.sti.bootcamp.model.Account;
import com.sti.bootcamp.model.Transaction;
import com.sti.bootcamp.model.dto.CommonResponse;
import com.sti.bootcamp.model.dto.TransactionDto;

@RestController
@RequestMapping("/transaction")
@SuppressWarnings("rawtypes")
public class TransactionController {
private static final Logger LOGGER  = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private AccountDao accountDao;
	
//	@GetMapping("/hello")
//	public String hello(@RequestParam(value="id", defaultValue="") int id){
//		try {
//			com.sti.bootcamp.model.Transaction transaction = transactionDao.getById(id);
//			if(transaction==null) {
//				return "Data tidak ditemukan";
//			}else 
//				return "Hello "+transaction.getAmount();
//		} catch (NumberFormatException e) {
//			return "Salah Format Input";
//		} catch (MyCustomException e){
//			return String.format("terjadi kesalahan sistem %s:", e.getMessage());
//		}
//	}
	
//	@PostMapping("/transaction")
//	public Transaction transaction(@RequestBody Transaction transaction) throws MyCustomException {	
//		
//		return transactionDao.save(transaction);
//	}
//	
//	@PutMapping("/transaction")
//	public Transaction up(@RequestBody Transaction transaction ) throws MyCustomException{
//		return transactionDao.save(transaction);
//	}
//	
//	@DeleteMapping("/transaction/{id}")
//	public void del (@PathVariable ("id") Transaction transaction ) throws MyCustomException {
//		transactionDao.delete(transaction);
//	}
//	
//	@GetMapping("/transaction")
//	public List<Transaction> getList() throws MyCustomException{	
//		return transactionDao.getList();
//	}
	@GetMapping("/{id}")
	public CommonResponse getById(@PathVariable("id") String id) throws MyCustomException {		
		LOGGER.info("id : {}", id);
		try {
			Transaction transaction = transactionDao.getById(Integer.valueOf(id));
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
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
	
	@PostMapping(value="")
	public CommonResponse insert(@RequestBody TransactionDto transactionDto) throws MyCustomException {
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);
			transaction.setTransactionnumber(0);
			transaction =  transactionDao.save(transaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
		} catch (MyCustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@PutMapping(value="")
	public CommonResponse update(@RequestBody TransactionDto transaction) throws MyCustomException {
		try {
			Transaction checkTransaction = transactionDao.getById(transaction.getTransactionnumber());
			if(checkTransaction == null) {
				return new CommonResponse("14", "transaction tidak ditemukan");
			}
			
			if(transaction.getType()!=null) {
				checkTransaction.setType(transaction.getType());
			}
			if(transaction.getAmount()!=null) {
				checkTransaction.setAmount(transaction.getAmount());
			}
			if(transaction.getAmountsign()!=null) {
				checkTransaction.setAmountsign(transaction.getAmountsign());
			}
			
			checkTransaction =  transactionDao.save(checkTransaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(checkTransaction, TransactionDto.class));
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
			Transaction checkTransaction = transactionDao.getById(id);
			if(checkTransaction == null) {
				return new CommonResponse("06", "transaction tidak ditemukan");
			}
			transactionDao.delete(checkTransaction);
			return new CommonResponse();
		} catch (MyCustomException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}

	@GetMapping(value="/list")
	public CommonResponse getList(@RequestParam(name="account", defaultValue="") String account) throws MyCustomException{
		try {
			List<Transaction> transactions;
			if(!StringUtils.isNullOrEmpty(account)) {
				Account checkAccount = accountDao.getById(Integer.parseInt(account));
				if(checkAccount==null) {
					throw new MyCustomException("customer tidak ditemukan");
				}
				transactions =  transactionDao.getListByAccountnumber(checkAccount);
			}else {
				transactions = transactionDao.getList();
			}
			
			LOGGER.info("transaction get list, params : {}", account);
			
		
			return new CommonResponse<List<TransactionDto>>(transactions.stream().map(trans -> modelMapper.map(trans, TransactionDto.class)).collect(Collectors.toList()));
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
