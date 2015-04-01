//TransactionMgr.java
package sg.edu.nus.iss.ussa.manager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import sg.edu.nus.iss.ussa.dataio.TransactionIO;
import sg.edu.nus.iss.ussa.domain.Store;
import sg.edu.nus.iss.ussa.domain.Transaction;
import sg.edu.nus.iss.ussa.exception.DataFileException;

public class TransactionManager
{
	/**
	 * TransactionMgr Class
	 * 
	 * @author Liu Xinzhuo
	 * @author A0136010A
	 * @version 1.0
	 */
	private ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
	private TransactionIO td;
	
	public void finalize() throws IOException
	{
		td.saveDataToFile(transactionList);
	}
	
	public void save() throws IOException
	{
		td.saveDataToFile(transactionList);
	}
	
	public TransactionManager(Store store) throws IOException, DataFileException
	{
		td = new TransactionIO(store);
		transactionList = td.loadDataFromFile();
	}
	
	public void setTransaction(ArrayList<Transaction> transactionList)
	{
		this.transactionList = transactionList;
	}
	
	public void addTransaction(Transaction transaction)
	{
		if(transaction.getId()==0)
		{
			transaction.setId(getMaxId()+1);
		}
		transactionList.add(transaction);
	}
	
	public ArrayList<Transaction> getTransactionListByDate(Date date)
	{
		ArrayList<Transaction> result = new ArrayList<Transaction>();
		for(int i = 0;i<transactionList.size();i++)
		{
			Transaction t = (Transaction) transactionList.get(i);
			if (date.equals(t.getDate()))
				result.add(t);
		}
		return result;
	}
	
	public int getMaxId(){
		int maxId = 0;
		
		for(Transaction transaction:this.transactionList){
			if (transaction.getId() > maxId) maxId = transaction.getId();
		}
		return maxId;
	}
}///~
