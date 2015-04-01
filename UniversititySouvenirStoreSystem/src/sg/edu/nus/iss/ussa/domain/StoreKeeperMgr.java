/**
 * 
 */
package sg.edu.nus.iss.ussa.domain;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.dataio.StoreKeeperDao;

/**
 * @author CHARAN
 *
 */
public class StoreKeeperMgr {
	
	private ArrayList<StoreKeeper> storeKeeperList;
	private StoreKeeperDao userDao;
	
	public StoreKeeperMgr() throws IOException
	{
		userDao = new StoreKeeperDao();
		storeKeeperList = new ArrayList<StoreKeeper>();
		storeKeeperList = userDao.loadDataFromFile();
	}
	
	public boolean checkAuthority(String userName,String Password)
	{
		boolean isValidUser = false;
		for(StoreKeeper user:storeKeeperList)
		{
			if(user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equalsIgnoreCase(Password))
			{
				isValidUser = true;
				break;
			}				
		}
		return isValidUser;
	}

}
