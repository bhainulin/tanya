package com.epam.jmp.main;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.epam.jmp.api.IAccountEJBRemote;
import com.epam.jmp.api.IBankEJBRemote;
import com.epam.jmp.api.ICurrencyRatioEJBRemote;
import com.epam.jmp.api.IPersonEJBRemote;
import com.epam.jmp.hello.HelloWorldBeanRemote;
import com.epam.jmp.model.Account;
import com.epam.jmp.model.Bank;
import com.epam.jmp.model.CurrencyRatio;
import com.epam.jmp.session.ITransfer;

public class CopyOfRunner {
	
	private static String helloWorldLN;
	private static String personLN;	
	private static String accountLN;
	private static String bankLN;
	private static String currencyRatioLN;
	private static String transferLN;
	
	static {
		helloWorldLN = getLookupName("HelloWorldBean", "com.epam.jmp.hello.HelloWorldBeanRemote");
		personLN = getLookupName("PersonBean", "com.epam.jmp.api.IPersonEJBRemote");
		accountLN = getLookupName("AccountBean", "com.epam.jmp.api.IAccountEJBRemote");
		bankLN = getLookupName("BankBean", "com.epam.jmp.api.IBankEJBRemote");
		currencyRatioLN = getLookupName("CurrencyRatioBean", "com.epam.jmp.api.ICurrencyRatioEJBRemote");
		transferLN = getLookupName("TransferBean", "com.epam.jmp.session.ITransfer");
	}
	
	public static void main(String[] args) {
		HelloWorldBeanRemote helloBean = (HelloWorldBeanRemote)doLookup(helloWorldLN);
		IPersonEJBRemote personBean = (IPersonEJBRemote)doLookup(personLN);
		IAccountEJBRemote accountBean = (IAccountEJBRemote)doLookup(accountLN);
		IBankEJBRemote bankBean = (IBankEJBRemote)doLookup(bankLN);
		ICurrencyRatioEJBRemote currencyRatioBean = (ICurrencyRatioEJBRemote)doLookup(currencyRatioLN);
		ITransfer transferbean = (ITransfer)doLookup(transferLN);
	    
		System.out.println("*******List********");
		System.out.println(helloBean.sayHello()); 
	    System.out.println(personBean.getList());
	    System.out.println(accountBean.getList());
	    System.out.println(bankBean.getList());
	    System.out.println(currencyRatioBean.getList());
	    System.out.println("**********************");
	    
	    
	    System.out.println("*****Account 1*******");
	    //Let's work with account 1
	    Account account = accountBean.fetchById(1);
	    System.out.println(account);
	    Bank bank = bankBean.fetchById(account.getIdBank());
	    System.out.println(bank);
	    List<CurrencyRatio> currencyRatioList = currencyRatioBean.fetchByBankId(bank.getIdBank());
	    System.out.println("currencyRatioList: "+currencyRatioList);
	    CurrencyRatio currencyRatio = null;
	    for(CurrencyRatio currency: currencyRatioList){
	    	if(currency.getInitial().equals(account.getCurrencyCode())){
	    		currencyRatio = currency;
	    		break;
	    	}
	    }
	    System.out.println("currencyRatio for account: "+currencyRatio);
	    
	    account =  transferbean.exchange(account, currencyRatio);
	    System.out.println("Account after exchange: " + account);
	}
	
	
	private static Object doLookup(String lookupName) {
	    Context context = null;
	    Object bean = null;
        try {
          context = getContext();
          bean = context.lookup(lookupName);
 
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return bean;
	}
	
    private static Context getContext() throws NamingException {
        Hashtable<String, Object> p = new Hashtable<String, Object>();
        p.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        p.put("jboss.naming.client.ejb.context", true);
        p.put(Context.PROVIDER_URL, "remote://127.0.0.1:4447/");
        p.put(InitialContext.SECURITY_PRINCIPAL, "adminUser");
        p.put(InitialContext.SECURITY_CREDENTIALS, "password");
        p.put("jboss.naming.client.connect.options.org.xnio.Options.SASL_POLICY_NOPLAINTEXT", "false");
        final Context context = new InitialContext(p);
        return context;
    }
    
    /**
     * 
     * @param beanName // The EJB bean implementation class name: beanName = "HelloWorldBean";
     * @param interfaceName // Fully qualified remote interface name: interfaceName = "com.epam.jmp.hello.HelloWorldBeanRemote";
     * @return
     */
    private static String getLookupName(String beanName, final String interfaceName) {
        /*
         * The module name is the JAR name of the deployed EJB without the .jar suffix.
         */
        String moduleName = "bank-1.0-SNAPSHOT";
        // Create a look up string name
        String name = moduleName + "/" + beanName + "!" + interfaceName;
        return name;
    }

}
