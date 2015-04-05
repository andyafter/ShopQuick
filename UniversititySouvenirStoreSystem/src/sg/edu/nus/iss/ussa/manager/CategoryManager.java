package sg.edu.nus.iss.ussa.manager;

import java.io.IOException;
import java.util.ArrayList;

import sg.edu.nus.iss.ussa.dataio.CategoryIO;
import sg.edu.nus.iss.ussa.dataio.VendorIO;
import sg.edu.nus.iss.ussa.domain.Category;
import sg.edu.nus.iss.ussa.domain.Vendor;
import sg.edu.nus.iss.ussa.exception.DataFileException;

/**
 *
 * @author Xu Minsheng
 *
 */
public class CategoryManager {

    private ArrayList<Category> categoryList;
	// this VendorList only exist for maintain data consistency
    // for example, if CLO and MUG share one vendor Nancy's , 
    // then in CLO and MUG, their vendors should reference to same instance of vendor  
    //private ArrayList<Vendor> vendorList;

    private CategoryIO categoryDao;
    private VendorIO vendorDao;

    /**
     *
     * @throws IOException
     * @throws DataFileException
     */
    public CategoryManager() throws IOException, DataFileException {
        categoryDao = new CategoryIO();
        vendorDao = new VendorIO();
        loadData();
    }

    /**
     * load data from file
     *
     * @throws IOException
     * @throws DataFileException
     */
    public void loadData() throws IOException, DataFileException {
        // load category basic info.
        categoryList = categoryDao.loadData();
		// load vendor and set to category
        //vendorList = vendorDao.loadDataFromFile(categoryList);
        vendorDao.loadData(categoryList);
    }

    /**
     * save data to file
     *
     * @throws IOException
     */
    public void saveData() throws IOException {
        categoryDao.saveDataToFile(categoryList);
        vendorDao.saveDataToFile(categoryList);
    }

    public void setCategoryList(ArrayList<Category> categoryList) {
        this.categoryList = categoryList;
    }


    public Category getCategoryByCode(String code) {
        for (Category category : this.categoryList) {
            if (code.equals(category.getCode())) {
                return category;
            }
        }
        return null;
    }

    public ArrayList<Category> getCategoryList() {
        return this.categoryList;
    }


    public Vendor getVendorByName(String name) {
        return null;
    }


    public ArrayList<Vendor> getVendorList() {
        return null;
    }


    public void addCategory(String code, String name, ArrayList<Vendor> vendorList) {
        Category category = new Category(code, name, vendorList);
        this.categoryList.add(category);

        this.maintainVendorList();
    }


    public void updCategory(String code, String name) {
        Category category = this.getCategoryByCode(code);
        category.setName(name);
        this.maintainVendorList();
    }

    /**
     *
     * @param code
     */
    public void delCategoryByCode(String code) {
        Category category = this.getCategoryByCode(code);
        this.categoryList.remove(category);
        this.maintainVendorList();
    }

    /**
     * When there is any change about category happens, this method will be
     * called to maintain a non-duplicate vendor list
     */
    private void maintainVendorList() {
        /*
         ArrayList<Vendor> newVendorList = new ArrayList<Vendor>();
         for(Category category : this.categoryList){
         for(Vendor vendor:category.getVendorList()){
         if(newVendorList.contains(vendor)) continue;
         else newVendorList.add(vendor);
         }
         }
         this.vendorList = newVendorList;
         */
    }

}
