

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.cub.ao.service.AccountSummaryService;
import com.cub.ao.vo.AccountSummaryVo;

@Component
public class AccountSummaryServiceImplTest extends AbstractTest {
	
	@Autowired
	private AccountSummaryService accSummaryService;
	private AccountSummaryVo expResult ;
	private String customerId = "1234567";
	
	@Before
    public  void initInputs(){
    	System.out.println("initInputs");
    	System.out.println(accSummaryService);
    	expResult = accSummaryService.getSummaryDetails(customerId);//new ArrayList<CasaSummaryVO>();
    }
	
    /**
     * Test case for checking the customer id is not null;
     */
    @Test
    public void testCustomerId()
    {
    	System.out.println("testCustomerId");
    	assertNotNull(customerId);
    }
	
    @Test
	public void getAOSummaryDetails() {

        System.out.println("GetCaSaSummary");
        AccountSummaryVo result = accSummaryService.getSummaryDetails(customerId);
        System.out.println("expResult=== "+expResult);
        System.out.println("result=== "+result);
        assertEquals(expResult, result);
    }		
}
