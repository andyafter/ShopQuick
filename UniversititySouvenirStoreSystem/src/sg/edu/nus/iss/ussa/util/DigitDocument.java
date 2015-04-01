package sg.edu.nus.iss.ussa.util;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/*
 * digit input limited for JTextField
 * or with limited length of input
 * 
 * For example:
 * JTextField jtf = new JTextField();
 * jtf.setDocument(new DigitDocument())
 * or limits assigned length using int parameter,
 * jtf.setDocument(new DigitDocument(4));
 * 
 * Hint:
 * When setDocument for JTextfield, the data of JTextfield would be erased automatically.
 * So when u initial a JTextfield with a string, Using setText after setDocument to add your string.
 * 
 * @ XIE JIABAO
 */

public class DigitDocument extends PlainDocument{
	
	private int limitedLength;
	
	public DigitDocument(){
		super();
	}
	
	public DigitDocument(int length){
		super();
		this.limitedLength = length;
	}
	
	@Override
	public void insertString(int offset, String str, AttributeSet a) throws BadLocationException{
		if(str == null){
			return;
		}
		if(limitedLength<=0){
			char[] upper = str.toCharArray();  
            int length = 0;  
            Character dot = new Character('.');
            for (int i = 0; i < upper.length; i++)
            {  
            	if (Character.isDigit(upper[i])||dot.equals(upper[i]))
                {  
                    upper[length++] = upper[i];
                }  
            }  
            super.insertString(offset, new String(upper,0,length),a);
		}else{
			if(this.getLength()+str.length()<=limitedLength){
				char[] upper = str.toCharArray();  
				int length = 0; 
				Character dot = new Character('.');
				for (int i = 0; i < upper.length; i++)
				{  
					if (Character.isDigit(upper[i])||dot.equals(upper[i]))
					{  
						upper[length++] = upper[i];  
					}  
				}  
				super.insertString(offset, new String(upper,0,length),a);
			}
		}
	}

	public int getLimitedLength() {
		return limitedLength;
	}
}
