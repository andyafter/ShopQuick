/**
 * 
 */
package sg.edu.nus.iss.ussa.dataio;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.domain.StoreKeeper;
import sg.edu.nus.iss.ussa.exception.DataFileException;

/**
 * @author CHARAN
 *
 */
public class StoreKeeperDao extends BaseDao {
	
	private static final String FILE_NAME = "StoreKeepers.dat";
	private static final String FILE_SEP = ",";
	private static final int NUM_FIELD = 2;
	
	public ArrayList<StoreKeeper> loadDataFromFile() throws IOException
	{
		ArrayList<String> storeKeeperData= new ArrayList<String>();
		ArrayList<StoreKeeper> storeKeeperList= new ArrayList<StoreKeeper>();
		storeKeeperData=super.loadStringFromFile(super.getcDatafolderpath()+FILE_NAME);
		for(String userData : storeKeeperData)
		{
			String[] filterData = userData.split(FILE_SEP);
			StoreKeeper user = new StoreKeeper();
			if(filterData.length == NUM_FIELD)
			{
				user.setUserName(filterData[0]);
				user.setPassword(filterData[1]);
				storeKeeperList.add(user);
			}
		}
		return storeKeeperList;
	}

}
