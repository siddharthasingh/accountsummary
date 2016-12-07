package com.cub.ao.service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.cub.ao.exception.AccountSummaryException;
import com.cub.ao.exception.RecordNotFoundException;
import com.cub.ao.util.AccountSummaryConstants;
import com.cub.ao.vo.AccountSummaryVo;
import com.cub.ao.vo.AssetSummaryVO;
import com.cub.ao.vo.AssetsListVo;
import com.cub.ao.vo.AssetsVo;
import com.cub.ao.vo.CombinedDepositSummaryResponse;
import com.cub.ao.vo.CombinedDepositVO;
import com.cub.ao.vo.CreditCardStatementVo;
import com.cub.ao.vo.LiabilityListVo;
import com.cub.ao.vo.LiabilityVo;
import com.cub.ao.vo.LoanAccountResponse;
import com.cub.ao.vo.LoanAccountVO;
import com.cub.ao.vo.ResponseMessageVo;
/**
 * This class is the service implementation class for CustomerAccountSummary microservice
 *
 */
@Component
public class AccountSummaryServiceImpl implements AccountSummaryService {
	
	@Autowired
	AccountSummaryVo accSummary;

	@Autowired
	private RestTemplate restTemplate;	
    
    @Value("${server.host}")
	private String hostname;
	
	@Value("${summary.path}")
	private String summaryPath;

	@Value("${asset.port}")
	private int assetPort;

	@Value("${asset.casa.baseuri}")
	private String casaBaseURI;
	
	@Value("${asset.deposit.baseuri}")
	private String depositBaseURI;
	
	@Value("${asset.combinedeposit.baseuri}")
	private String combinedDepositBaseURI;
	
	@Value("${liability.port}")
	private int liabilityPort;
	
	@Value("${creditcard.port}")
	private int creditCardPort;
	

	@Value("${liability.baseuri}")
	private String liabilityBaseURI;
	
	@Value("${liability.creditcard.baseuri}")
	private String liabilityCreditCardURI;
	
	@Value("${asset.sync.baseuri}")
	private String assetSyncUri;
	
	
	@Value("${liability.sync.baseuri}")
	private String liabilitySyncUri;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountSummaryServiceImpl.class);
	private static final String CLASS_NAME = AccountSummaryServiceImpl.class.getName();
	
	private boolean isSynched = false ;
	
		
	/**
	 * This operation provides account summary details for a given customer Id
	 * @param customerId
	 * @return  AccountSummaryVo
	 */
	@Override
	public AccountSummaryVo getSummaryDetails(String customerId) {
		final String methodName = "getSummaryDetails";
		LOGGER.debug(AccountSummaryConstants.LOG_ENTER_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
				
		try {
			AssetsListVo assetDetails = null;
			LiabilityListVo liabilityDetails = null;
				LOGGER.info("Calling Asset summary method @ service layer");
				assetDetails = getAssetSummary(customerId);	
				LOGGER.info("Calling Liability summary method @ service layer");
				liabilityDetails = getLiabilitySummary(customerId);
				
				
				if(null != assetDetails || null != liabilityDetails) {
					LOGGER.debug("setting up both data list for account summary.");
					accSummary.setListOfAssets(assetDetails);
					accSummary.setListOfLiability(liabilityDetails);						
				}else {
					LOGGER.debug("List is not loaded, in case of data not available.");
					throw new RecordNotFoundException(AccountSummaryConstants.NO_RECORDS_FOUND);
				} 	
		}
		catch (AccountSummaryException e) {
			LOGGER.error(e.getMessage(),e);
			throw new AccountSummaryException(e.getMessage());
		}
		catch (RecordNotFoundException e) {
			LOGGER.error(e.getMessage(),e);
			throw new RecordNotFoundException(e.getMessage());
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(),e);
			throw  e;
		}
		LOGGER.debug(AccountSummaryConstants.LOG_EXIT_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		return accSummary;
	}
	
	/**
	 * This operation ;	
	 * @param customerId
	 * @return
	 */
	public AssetsListVo getAssetSummary(String customerId) {
		final String methodName = "getAssetSummary";
		LOGGER.debug(AccountSummaryConstants.LOG_ENTER_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		
	    String currencyCode = null;
		accSummary.setCustomerId(customerId);
		AssetsListVo assetList = null;
	    Map<String, BigDecimal> assetDataMap = new HashMap<String, BigDecimal>();
	    Map<String, BigDecimal> combinedDataMap = new HashMap<String, BigDecimal>();
	    BigDecimal totalAssets =BigDecimal.ZERO;
	    try {
	    	List<AssetSummaryVO> casaSummaryList = getCaSaSummary(customerId);
			
			if(null != casaSummaryList && !casaSummaryList.isEmpty()){
				
				for(AssetSummaryVO assets : casaSummaryList) {
		    		currencyCode = assets.getCurrencyCode();
		    		BigDecimal	availableBalanceSum = BigDecimal.ZERO;
		    		if(assetDataMap.containsKey(assets.getAccountType())) {	    			
		    			availableBalanceSum = assetDataMap.get(assets.getAccountType()).add(assets.getAvailableBalance());
		    			assetDataMap.put(assets.getAccountType(), availableBalanceSum);	
		    			totalAssets = totalAssets.add(assets.getAvailableBalance());
		    			
		    		}
		    		else {
		    			assetDataMap.put(assets.getAccountType(), assets.getAvailableBalance());
		    			totalAssets = totalAssets.add(assets.getAvailableBalance());
		    		}
		    	}
			}
			
			final String currCode = currencyCode;
		    List<AssetsVo> assetDataList = new ArrayList<AssetsVo>();
		    Set<String> assetDataKeys = assetDataMap.keySet();
		    for(String key:assetDataKeys){
		    	AssetsVo assetData = new AssetsVo();
		    	assetData.setAccountType(key);
		    	assetData.setTypeSum(assetDataMap.get(key).toString());
		    	//assetData.setCurrencyCode(currCode);
		    	assetData.setCurrencyCode(currCode);
		    	assetDataList.add(assetData);
		    }
		    
		    if(!assetDataList.isEmpty()){
		    	assetList = new AssetsListVo();
			    assetList.setAssets(assetDataList);
			    assetList.setAssetsTotal(totalAssets.toString());
		    }
	    } catch (Exception e) {
	    	LOGGER.error(e.getMessage(), e);
	    	}
	    return assetList;
	}	
	public LiabilityListVo getLiabilitySummary(String customerId) {
		final String methodName = "getLiabilitySummary";
		LOGGER.debug(AccountSummaryConstants.LOG_ENTER_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		
		 String currencyCode = null;
		 accSummary.setCustomerId(customerId);
		 LiabilityListVo liabilityList = null;
			
		
		 BigDecimal totalLiability =BigDecimal.ZERO;
		 
		    Map<String, BigDecimal> liabilityDataMap = new HashMap<String, BigDecimal>();

		    try {
		    	LoanAccountResponse loanSummary = getLoanAccountSummary(customerId);
		    	

		    	
	    	if((null != loanSummary) && (loanSummary.getLoanAccountList()!=null && !loanSummary.getLoanAccountList().isEmpty())) {
	    		
	    		liabilityList = new  LiabilityListVo(); 
			    liabilityList.setLiabilityTotal(loanSummary.getOutstandingTotal().toString());
			    accSummary.setCustomerId(loanSummary.getCustomerId());
		    	List<LoanAccountVO> loanSummaryList = loanSummary.getLoanAccountList();
		    	
		    	for(LoanAccountVO loan : loanSummaryList) {
		    		currencyCode = loan.getCurrencyCode();
		    		BigDecimal availableBalanceSum = BigDecimal.ZERO;
		    		if(liabilityDataMap.containsKey(loan.getAccountType() )) {	    			
		    			availableBalanceSum = liabilityDataMap.get(loan.getAccountType()).add(loan.getOutstandingLoanAmount());
		    			liabilityDataMap.put(loan.getAccountType(), availableBalanceSum);
		    			totalLiability = totalLiability.add(loan.getOutstandingLoanAmount());
		    		}
		    		else {
		    			liabilityDataMap.put(loan.getAccountType(), loan.getOutstandingLoanAmount());
		    			totalLiability = totalLiability.add(loan.getOutstandingLoanAmount());
		    		}
		    	
		    	}
		    }   
		    List<LiabilityVo> liabilityDataList = new ArrayList<LiabilityVo>();
			
			final String currCodeCredit = currencyCode;
			Set<String> liabilityDataKeys = liabilityDataMap.keySet();
			for(String key:liabilityDataKeys){
				LiabilityVo liabilityData = new LiabilityVo();
				liabilityData.setAccountType(key);
				liabilityData.setTypeSum(liabilityDataMap.get(key).toString());
				liabilityData.setCurrencyCode(currCodeCredit);
				liabilityDataList.add(liabilityData);
		    }
			
			if (!liabilityDataList.isEmpty()) {
				liabilityList = new LiabilityListVo();
				liabilityList.setLiability(liabilityDataList);
				liabilityList.setLiabilityTotal(totalLiability.toString());
			}
		    } catch (Exception e) {
		    	LOGGER.error(e.getMessage(), e);
		    	}
		    return liabilityList;
		    }
	
	private  List<AssetSummaryVO> getCaSaSummary(String custId)  {
		final String methodName = "getCaSaSummary";
		LOGGER.debug(AccountSummaryConstants.LOG_ENTER_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		List<AssetSummaryVO> resp = null;
		
		URI targetUrl = UriComponentsBuilder.fromUriString(casaBaseURI).scheme("http").host(hostname).port(assetPort)
				.path(summaryPath).queryParam("customerId", custId).build().toUri();
		LOGGER.debug("Calling URI: " + targetUrl);
		try{
			ResponseEntity<List<AssetSummaryVO>> assetResponse =
			        restTemplate.exchange(targetUrl,
			                    HttpMethod.GET, null, new ParameterizedTypeReference<List<AssetSummaryVO>>() {
			            });
			resp = assetResponse.getBody();
		
		}catch (HttpClientErrorException ex){
			if(HttpStatus.NOT_FOUND == ex.getStatusCode()){
				LOGGER.warn("No records found for Customer Id: " + custId);
			}else{
				LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
			}
		}catch(HttpServerErrorException ex){
			LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
		}catch(Exception ex){
			LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
		}
		
		LOGGER.debug(AccountSummaryConstants.LOG_EXIT_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		return resp;
}
	
	private  List<CombinedDepositVO> getCombinedDepositSummary(String custId) {
		final String methodName = "getCombinedDepositSummary";
		LOGGER.debug(AccountSummaryConstants.LOG_ENTER_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		
		List<CombinedDepositVO> resp = null;
		URI targetUrl = UriComponentsBuilder.fromUriString(combinedDepositBaseURI).scheme("http").host(hostname).port(assetPort)
				.path(summaryPath).queryParam("customerId", custId).build().toUri();
		LOGGER.debug("Calling URI:" + targetUrl);
		try{
			ResponseEntity<CombinedDepositSummaryResponse> assetResponse =
			        restTemplate.exchange(targetUrl,
			                    HttpMethod.GET, null, new ParameterizedTypeReference<CombinedDepositSummaryResponse>() {
			            });
			if(null != assetResponse.getBody()){
			 resp = assetResponse.getBody().getListOfCombinedDepositDetails();
			}
		}catch (HttpClientErrorException ex ){
			if(HttpStatus.NOT_FOUND == ex.getStatusCode()){
				LOGGER.warn("No records found for Customer Id: " + custId);
			}else{
				LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
			}
		}catch(Exception ex){
			LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
		}
		LOGGER.debug(AccountSummaryConstants.LOG_EXIT_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		return resp;

	}
	
	private  List<AssetSummaryVO> getDepositSummary(String custId) {
		final String methodName = "getDepositSummary";
		LOGGER.debug(AccountSummaryConstants.LOG_ENTER_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		
		List<AssetSummaryVO> resp = null;
		URI targetUrl = UriComponentsBuilder.fromUriString(depositBaseURI).scheme("http").host(hostname).port(assetPort)
				.path(summaryPath).queryParam("customerId", custId).build().toUri();
		LOGGER.debug("Calling URI:" + targetUrl);
		try{
			ResponseEntity<List<AssetSummaryVO>> assetResponse =
			        restTemplate.exchange(targetUrl,
			                    HttpMethod.GET, null, new ParameterizedTypeReference<List<AssetSummaryVO>>() {
			            });
			 resp = assetResponse.getBody();
		}catch (HttpClientErrorException ex ){
			if(HttpStatus.NOT_FOUND == ex.getStatusCode()){
				LOGGER.warn("No records found for Customer Id: " + custId);
			}else{
				LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
			}
		}catch(Exception ex){
			LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
		}
		LOGGER.debug(AccountSummaryConstants.LOG_EXIT_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		return resp;

	}
	
	public LoanAccountResponse getLoanAccountSummary(String customerId) {

	final String methodName = "getLoanAccountSummary";
	LOGGER.debug(AccountSummaryConstants.LOG_ENTER_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);		
	LoanAccountResponse loanSummary = null;

	URI targetUrl = UriComponentsBuilder.fromUriString(liabilityBaseURI).scheme("http").host(hostname).port(liabilityPort)
			.path(summaryPath).queryParam("customerId", customerId).build().toUri(); 	
	LOGGER.debug("Calling URI: " + targetUrl);
    try {
    
    	ResponseEntity<LoanAccountResponse> liability = restTemplate.exchange (targetUrl,  HttpMethod.GET, null, LoanAccountResponse.class);
    	loanSummary = liability.getBody();
    		    	
    	
	} catch (HttpClientErrorException ex){
		if(HttpStatus.NOT_FOUND == ex.getStatusCode()){
			LOGGER.warn("No records found for Customer Id: " + customerId);
		}else{
			LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
		}
	}catch(HttpServerErrorException ex){
		LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
	}catch(Exception ex){
		LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
	}
	
	LOGGER.debug(AccountSummaryConstants.LOG_EXIT_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
	return loanSummary;
	}
	/**
	 * This operation provides Credit Card details for a given customer Id
	 * @param customerId
	 * @return  AccountSummaryVo
	 */
	
	public CreditCardStatementVo getCreditCardSummary(String customerId) {
		final String methodName = "getCreditCardSummary";
		LOGGER.debug(AccountSummaryConstants.LOG_ENTER_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);		
	/*	BigDecimal	arrears = BigDecimal.ZERO;*/
		CreditCardStatementVo creditCardStatementVo = null;
	    
	    try {
	    		    	
	    	URI targetUrl = UriComponentsBuilder.fromUriString(liabilityCreditCardURI).scheme("http").host(hostname).port(creditCardPort)
					.queryParam("customerid", customerId).build().toUri(); 	
	    	/*ResponseEntity<CreditCardStatementVo> creditCard = restTemplate.exchange (targetUrl,  HttpMethod.GET, null, LoanAccountResponse.class);*/
	    	ResponseEntity<CreditCardStatementVo> creditCard = restTemplate.exchange(targetUrl,  HttpMethod.GET, null, CreditCardStatementVo.class);
	    	
	    	creditCardStatementVo = creditCard.getBody();
	    		    	
	    	/*if(null != creditCardStatementVo)
	    	{
	    		arrears = creditCardStatementVo.getArearesAmt();
	    	}*/
	    	
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	    LOGGER.debug(AccountSummaryConstants.LOG_EXIT_METHOD + methodName + AccountSummaryConstants.LOG_OF_CLASS + CLASS_NAME);
		return creditCardStatementVo;
	}
	
	
	public boolean synchronizeAsset(String customerId) {
		ResponseMessageVo resp = null;
		boolean isSync = false;
		URI targetUrl = UriComponentsBuilder.fromUriString(assetSyncUri).scheme("http").host(hostname).port(assetPort)
				.queryParam("customerId", customerId).build().toUri();
		LOGGER.debug("Calling URI: " + targetUrl);
		try{
			ResponseEntity<ResponseMessageVo> assetSyncResponse =
			        restTemplate.exchange(targetUrl,
			                    HttpMethod.GET, null, new ParameterizedTypeReference<ResponseMessageVo>() {
			            });
			resp = assetSyncResponse.getBody();
			LOGGER.info("Response from asset  sync : " + resp.getMessageCode() + ": : " + resp.getMessage() );
			isSync= true;
			
			
		}catch(Exception ex){
			isSync= true;
			LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
		}
		return isSync;
	}
	public boolean synchronizeLiability(String customerId) {
		ResponseMessageVo resp = null;
		boolean isSync = false;
		URI targetUrl = UriComponentsBuilder.fromUriString(liabilitySyncUri).scheme("http").host(hostname).port(liabilityPort)
				.queryParam("customerId", customerId).build().toUri();
		LOGGER.debug("Calling URI: " + targetUrl);
		try{
			ResponseEntity<ResponseMessageVo> assetSyncResponse =
			        restTemplate.exchange(targetUrl,
			                    HttpMethod.GET, null, new ParameterizedTypeReference<ResponseMessageVo>() {
			            });
			resp = assetSyncResponse.getBody();
			LOGGER.info("Response from liability  sync : " + resp.getMessageCode() + ": : " + resp.getMessage() );
			isSync= true;
			
			
		}catch(Exception ex){
			isSync= true;
			LOGGER.error(AccountSummaryConstants.ERROR_SERVICE_CALL+ targetUrl,ex);
		}
		return isSync;
	}
}
