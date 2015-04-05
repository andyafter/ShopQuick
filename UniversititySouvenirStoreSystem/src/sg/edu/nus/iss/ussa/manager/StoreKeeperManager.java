/**
 *
 */
package sg.edu.nus.iss.ussa.manager;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.dataio.ShopKeeperIO;
import sg.edu.nus.iss.ussa.domain.ShopKeeper;

/**
 * @author CHARAN
 *
 */
public class StoreKeeperManager {

    private ArrayList<ShopKeeper> storeKeeperList;
    private ShopKeeperIO userDao;

    public StoreKeeperManager() throws IOException {
        userDao = new ShopKeeperIO();
        storeKeeperList = new ArrayList<ShopKeeper>();
        storeKeeperList = userDao.loadData();
    }

    public boolean checkAuthority(String userName, String Password) {

        boolean isValidUser = false;
        for (ShopKeeper user : storeKeeperList) {
            if (user.getUserName().equalsIgnoreCase(userName) && user.getPassword().equals(Password)) {
                // password should be case sensitive
                isValidUser = true;
                break;
            }
        }
        return isValidUser;
    }

}
