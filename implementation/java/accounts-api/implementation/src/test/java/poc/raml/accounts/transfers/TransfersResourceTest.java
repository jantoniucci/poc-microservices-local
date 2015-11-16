package poc.raml.accounts.transfers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import poc.raml.accounts.security.SessionManager;
import poc.raml.accounts.transfers.services.TransfersService;
import poc.raml.jaxrs.accounts.model.Transfer;
import poc.raml.jaxrs.accounts.model.Transfers;
import poc.raml.jaxrs.accounts.resource.TransfersResource;

import java.util.Arrays;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

/**
 * Created by frascuchon on 13/11/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class TransfersResourceTest {

	private TransfersResource transfersResource;

	@Mock
	private TransfersService transfersService;

	@Mock
	private SessionManager sessionManager;

	@Before
	public void setUp() throws Exception {
		reset(transfersService, sessionManager);
		transfersResource = new TransfersResourceImpl(transfersService, sessionManager);
	}

	@Test
	public void testGetTransfersOK() throws Exception {

		String accountId = "accountId";
		String accessToken = "access-token";

		doReturn(Arrays.asList(new Transfer())).when(transfersService).getAccountsTransfers(accountId);
		doReturn(accountId).when(sessionManager).getCurrentAccountId(accessToken);

		TransfersResource.GetTransfersResponse response =
						transfersResource.getTransfers("", "", TransfersResource.Order.asc, 0, 100, accessToken);

		Transfers entity = (Transfers) response.getEntity();
		Assert.assertEquals(1, entity.getSize().intValue());
	}
}