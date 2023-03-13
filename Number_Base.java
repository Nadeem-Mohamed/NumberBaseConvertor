//import statements requried for the program
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Number_Base implements ActionListener 
{
  //initializing variables for textfields and menus
  static JTextField frombox;
  static JTextField tobox;
  static JComboBox<String> frombase;
  static JComboBox<String> tobase;
  static JButton reset;
  static JButton done;
  
  public void menu_App() 
  {
    //setting up menu
    String[] numberbase = { "Decimal", "Hexa Decimal", "Binary" };
    //the main title
    JFrame jFrame = new JFrame("Number Base Conversion");
    //creating the panel
    JPanel main_content = new JPanel();
    jFrame.add(main_content);
    //setting up the from menu
    frombase = new JComboBox<>(numberbase);
    frombase.setBounds(0, 30, 140, 20);
    main_content.add(frombase);
    //setting up the to menu
    tobase = new JComboBox<>(numberbase);
    tobase.setBounds(0, 80, 140, 20);
    main_content.add(tobase);
    //setting up the from textfield
    frombox = new JTextField(10);
    frombox.setBounds(150, 30, 130, 20);
    main_content.add(frombox);
    //setting up the to textfield
    tobox = new JTextField(10);
    tobox.setBounds(150, 80, 130, 20);
    main_content.add(tobox);
    //creating the convert button
    done=new JButton("Convert");
    done.setBounds(0,130,100,20);
    main_content.add(done);
    //creating the reset button
    reset=new JButton("Reset");
    reset.setBounds(150,130,130,20);
    main_content.add(reset);
    //performing the actions
    frombase.addActionListener(this);
    tobase.addActionListener(this);
    frombox.addActionListener(this);
    tobox.addActionListener(this);
    reset.addActionListener(this);
    done.addActionListener(this);
    
    jFrame.setSize(600, 500);
    main_content.setLayout(null);
    jFrame.setVisible(true);

  }

  public void actionPerformed(ActionEvent e) 
  {
    //resetting the textfields to nothing when pressed
    if(e.getSource()==reset)
    {
      frombox.setText("");
      tobox.setText("");
      return;
    }

    //getting the current number base
    String from = (String) frombase.getSelectedItem();
    String too = (String) tobase.getSelectedItem();
    
    //getting the input from textfield
    String inp = frombox.getText();
    //checking if input is a special character
    if(inp.matches(".*[^0-9].*")){
      tobox.setText("Invalid input");
    }
    //converting the input to uppercase
    inp=inp.toUpperCase();
    int fromint=0;

    //converting to integer if the number base is decimal or binary
    if (from=="Decimal"|| from=="Binary")
    {
      fromint = Integer.parseInt(inp);
    }

    //converting binary to decimal
    if (from.equals("Binary") && too.equals("Decimal")) 
    {
      try 
        {
        int decimal = 0;
        int n = 0;
        boolean isvalid = true;

          
        while (true) 
        {
          if (fromint == 0) 
          {
            break;
          } 
            
          else 
          {
            //checking if the input is valid for binary
            int temp = fromint % 10;
            if (temp != 0 && temp != 1) 
            {
              isvalid = false;
              break;
            }
            //multiplying each inut with the corresponding power of 2
            decimal += temp * Math.pow(2, n);
            fromint = fromint / 10;
            n++;
          }
        }
          
        if (isvalid) 
        {
          //showing the output when convert button is pressed
          if(e.getSource()==done)
          {
            tobox.setText(String.valueOf(decimal));
          }
        } 
        else 
        {
          tobox.setText("Invalid input");
        }
          
      } 
      catch (NumberFormatException ex) 
      {
        tobox.setText("Invalid input");
      }
    }
      
    //converting binary to hexa decimal
    else if (from.equals("Binary") && too.equals("Hexa Decimal")) 
    {
     
      int c = 0;
      long s1 = 0;
      String z = "";
      String binary=String.valueOf(fromint);

      //checking if input is valid for binary
      for(int i=0;i<binary.length();i++){
        char check=binary.charAt(i);
        if(check!='0' && check!='1'){
          tobox.setText("Invalid input");
        }
      }

      //multiplying each digit by corresponding power of 2 and the n grouping the resulting answers into groups of 4 to check the hex representation
      while (fromint > 0) 
      {
        s1 = s1 + (long) (Math.pow(2, c) * (long) (fromint % 10));
        fromint = fromint / 10;
        c++;
      }
      
      while (s1 > 0) 
      {
        long j = s1 % 16;
        if (j == 10) 
        {
          z = "A" + z;
        } 
        else if (j == 11) 
        {
          z = "B" + z;
        } 
        else if (j == 12) 
        {
          z = "C" + z;
        } 
        else if (j == 13) 
        {
          z = "D" + z;
        } 
        else if (j == 14) 
        {
          z = "E" + z;
        }
        else if (j == 15) 
        {
          z = "F" + z;
        } 
        else 
        {
          z = j + z;
        }
        s1 = s1 / 16;
      }

      //showing output when convert is pressed
      if(e.getSource()==done)
      {
      tobox.setText(String.valueOf(z));
      }
      }
      
     
    //showing same bases if the bases are the same  
    else if (from.equals("Binary") && too.equals("Binary")) 
    {
      if(e.getSource()==done)
      {
      tobox.setText("Same bases");
      }
    } 

    //converting decimal to binary
    else if (from.equals("Decimal") && too.equals("Binary")) 
    {
      int b_number = 0;
      int cnt = 0;

      //dividing by two and adding the remainders in reverse order where every digit is is multiplied by the corresponding power of 2
      while (fromint != 0) 
      {
        int rem = fromint % 2;
        double c = Math.pow(10, cnt);
        b_number += rem * c;
        fromint /= 2;
        cnt++;
      }

      //showing output when convert is pressed
      if(e.getSource()==done)
      {
      tobox.setText(String.valueOf(b_number));
      }
    }

    //converting decimal to hexa decimal
    else if (from.equals("Decimal") && too.equals("Hexa Decimal"))
    {
      int rem;  
      String hex="";   
      char hexchars[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};        
      //dividing by 16 and adding the remainders in reverse order where every digit is converted to its corresponding hex
      while(fromint>0)  
      {  
        rem=fromint%16;   
        hex=hexchars[rem]+hex;   
        fromint=fromint/16;  
      }  
      
      //showing output when convert is pressed
      if(e.getSource()==done)
      {
      tobox.setText(String.valueOf(hex));
      }
    }
      
    //showing same bases if the bases are the same 
    else if(from.equals("Decimal") && too.equals("Decimal"))
    {
      if(e.getSource()==done)
      {
      tobox.setText("Same bases");
      }
    }

    //Converting hex to binary
    else if (from.equals("Hexa Decimal") && too.equals("Binary")) 
    {
      
    //checking if input contains letters that are not from A to F
    for(int i=0; i<inp.length();i++){
      char c=inp.charAt(i);
      if(!Character.isDigit(c) && (c<'A'||c>'F') )
      {
        tobox.setText("Invalid input");
        return;
      }
    }
      
    String binary = "";
    String hexChars = "0123456789ABCDEF";

    //checking if the input is valid for hex
    for (int i = 0; i < inp.length(); i++) 
    {
      char c = inp.charAt(i);
      int val = hexChars.indexOf(c);

      if(val<0)
      {
        tobox.setText("Invalid input");
        return;
      }

      //converting to binary and adding 0 until it reaches at least 4 characters
      String binVal = Integer.toBinaryString(val);
        
      while (binVal.length() < 4) 
      {
        binVal = "0" + binVal;
      }
      
      binary += binVal;
    }
  //showing output when convert is pressed
  if(e.getSource()==done)
  {
  tobox.setText(binary);
  }
}

    //converting hex to decimal
    else if (from.equals("Hexa Decimal") && too.equals("Decimal"))
    {
      //checking if input contains letters that are not from A to F
      for(int i=0; i<inp.length();i++){
      char c=inp.charAt(i);
      if(!Character.isDigit(c) && (c<'A'||c>'F') )
      {
        tobox.setText("Invalid input");
        return;
      }
    }
      int base=1;
      int dec=0;
      //converting each character to its corresponding decimal number
      for(int i=inp.length()-1;i>=0; i--)
      {
        if(inp.charAt(i)>='0' && inp.charAt(i)<='9')
        {
          dec+=(inp.charAt(i)-48)*base;
          base*=16;
        }
          
        else if(inp.charAt(i)>='A' && inp.charAt(i)<='F')
        {
          dec+=(inp.charAt(i)-55)*base;
          base*=16;
        }
      }

      //showing output when convert is pressed
      if(e.getSource()==done)
      {
      tobox.setText(String.valueOf(dec));
      }
    }

    //showing same bases if the bases are same
    else if(from.equals("Hexa Decimal") && too.equals("Hexa Decimal")){
      if(e.getSource()==done)
      {
      tobox.setText("Same bases");
      }
    }
  }
}