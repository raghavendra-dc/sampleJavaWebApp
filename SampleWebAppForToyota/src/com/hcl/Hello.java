package com.hcl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

import com.hcl.getterSetter;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

@Controller  
public class Hello {  
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)  
    public ModelAndView login(@ModelAttribute("SpringWeblogicProject")getterSetter getterSetter) { 
		String userName=getterSetter.getUserName();
		String password=getterSetter.getPassword();
		System.out.println("inputs::: " +userName +" , " + password);
		
		ActiveDirectory authentication = new ActiveDirectory("raghav.hcl.com");
	    try {
	        boolean authResult = authentication.authenticate(userName, password);
	        System.out.print("Auth: " + authResult);
	        if(authResult){
			return new ModelAndView("welcome", "userName" , userName);
	        }
	    } catch (LoginException e) {
	    	System.out.println("coming inside ad error catch");
	    	e.printStackTrace();
	    }
	    String errorMessage=("please provide proper username and password");
	    return new ModelAndView("index", "errorMessage" , errorMessage);
	    
	    
		/*if(userName.equals("raghav")){
			System.out.println("coming if");
		return new ModelAndView("welcome", "userName" , userName);
		}
		else{
			System.out.println("coming else");
			String errorMessage=("please provide proper username and password");
			return new ModelAndView("index", "errorMessage" , errorMessage);
		}*/
		
	}
	
	
	
    @RequestMapping(value = "/view", method = RequestMethod.GET)  
    public String  view(ModelMap model) {  
    	String message = "Hi Guys, Welcome to spring project"; 
    	System.out.println("message ::: " + message);
    	/*try {
		      // create the Oracle DataSource and set the URL
		     // OracleDataSource ods = new OracleDataSource();
		      //ods.setURL("jdbc.oracle.thin:SYSTEM/hcl1234@localhost:1521/localOracleServer");
		      
		      OracleDataSource dataSource = new OracleDataSource();
		      dataSource.setServerName("localhost");
		      dataSource.setUser("SYSTEM");
		      dataSource.setPassword("hcl1234");
		      dataSource.setDatabaseName("xe");
		      dataSource.setPortNumber(1521);
		      dataSource.setDriverType("thin");
		     //return dataSource.getConnection();
		       
		      // connect to the database and turn off auto commit
		      OracleConnection ocon = (OracleConnection)dataSource.getConnection();
		     // ocon.setAutoCommit(false);
		       
		      // create the statement and execute the query
		      Statement stmt = ocon.createStatement();
		      ResultSet rset = stmt.executeQuery("select ID,NAME from TESTTABLE");
		      String id="";
		      String name="";
		      int i=0;
		      // print out the results
		      while(rset.next()) {
		    	  if(i==0){
		    		  id=Integer.toString(rset.getInt(1));
			    	  name=rset.getString(2);
		    	  }else{
		    	  id=id+ ", " +Integer.toString(rset.getInt(1));
		    	  name=name + ", " + rset.getString(2);
		    	  }
		    	  i++;
		    	  System.out.println("data from table::: " + rset.getInt(1) + ", " + rset.getString(2));
		    	                        
		      }
		      model.addAttribute("ID", id);
	          model.addAttribute("NAME", name);
		       
		    } catch (SQLException e) {
		      System.out.println(e.getMessage());
		    }
    	    	*/
    	
    	
    	Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");// populates the data of the
                                            // configuration file

        // creating seession factory object
        SessionFactory factory = cfg.buildSessionFactory();

        // creating session object
        Session session = factory.openSession();

        // creating transaction object
        Transaction t = session.beginTransaction();

        Query query = session.createQuery("from getterSetter");
        
        final List<getterSetter> list = new LinkedList<>();
        for(final Object o : query.list()) {
            list.add((getterSetter)o);
        }
        String id="";
	      String name="";
        for (int i = 0; i < list.size(); i++) {
    		System.out.println("data from oracle table::: " + list.get(i).getNAME() + ", "+ list.get(i).getID());
    		if(i==0){
	    		  id=Integer.toString( list.get(i).getID());
		    	  name=list.get(i).getNAME();
	    	  }else{
	    	  id=id+ ", " +Integer.toString( list.get(i).getID());
	    	  name=name + ", " + list.get(i).getNAME();
	    	  }
    	}
        
        model.addAttribute("ID", id);
        model.addAttribute("NAME", name);
                
        t.commit();
        session.close();

    	
    	 
        return "dataPage";  
    }  
}  
