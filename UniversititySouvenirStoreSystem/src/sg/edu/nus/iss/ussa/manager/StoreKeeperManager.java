/**
 * 
 */
package sg.edu.nus.iss.ussa.manager;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.dataio.StoreKeeperIO;
import sg.edu.nus.iss.ussa.domain.StoreKeeper;

/**
 * @author CHARAN
 *
 */
public class StoreKeeperManager {
	
	private ArrayList<StoreKeeper> storeKeeperList;
	private StoreKeeperIO userDao;
	
	public StoreKeeperManager() throws IOException
	{
		userDao = new StoreKeeperIO();
		storeKeeperList = new ArrayList<StoreKeeper>();
		storeKeeperList = userDao.loadDataFromFile();
	}
	
	public boolean checkAuthority(String userName,String Password)
	{
            
		boolean isValidUser = false;
		for(StoreKeeper user:storeKeeperList)
		{
			if(user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equals(Password))
			{
                            // password should be case sensitive
				isValidUser = true;
				break;
			}				
		}
		return isValidUser;
	}

}
