package poc.raml.accounts.data;

import poc.raml.jaxrs.accounts.model.Account;

/**
 * Created by frascuchon on 13/11/15.
 */
public class EmptyAccount extends Account {

	public static final EmptyAccount INSTANCE = new EmptyAccount();

	public static EmptyAccount getInstance() {
		return INSTANCE;
	};



}
