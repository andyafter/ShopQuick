/**
 *
 */
package sg.edu.nus.iss.ussa.dataio;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.domain.ShopKeeper;
import sg.edu.nus.iss.ussa.exception.DataFileException;

/**
 * @author CHARAN
 *
 */
public class ShopKeeperIO extends DataIO {

    private static final String fileName = "StoreKeepers.dat";
    private static final String comma = ",";
    private static final int columns = 2;

    public ArrayList<ShopKeeper> loadData() throws IOException {
        ArrayList<String> data = new ArrayList<String>();
        ArrayList<ShopKeeper> shopKeeperList = new ArrayList<ShopKeeper>();
        data = super.loadFile(super.getcDatafolderpath() + fileName);
        for (String userData : data) {
            String[] filterData = userData.split(comma);
            ShopKeeper user = new ShopKeeper();
            if (filterData.length == columns) {
                user.setUserName(filterData[0]);
                user.setPassword(filterData[1]);
                shopKeeperList.add(user);
            }
        }
        return shopKeeperList;
    }

}
