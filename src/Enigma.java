//Artem S.
//Period 7
//Computer Science A
//Enigma checkpoint 1

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Enigma{
    public static final String caesar = "BCDEFGHIJKLMNOPQRSTUVWXYZA";
	
	public static void main(String[] args) throws FileNotFoundException{

        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to Enigma");
        String input = "";
        String copy = "";

        do{
            System.out.print("Enter: ");
            input = in.nextLine();
            copy = parseInput(input,copy);
        }while(!input.equalsIgnoreCase("quit"));
        in.close();
    }

    public static String parseInput(String input,String copy) throws FileNotFoundException {
    	String[] splitInput = input.split(" ");
        //move to method and make not c dependant so to not have this list of ifs every cipher
    	if(splitInput[0].equalsIgnoreCase("load")){
    		File text = new File(splitInput[1]);
    		if(text.exists()) {
    			Scanner parser = new Scanner(text);//load C:\Users\2011828\Desktop\studentTestCases.txt
				do{
		            System.out.print("Enter: ");
		            input = parser.nextLine();
		            
		            if(input.contains(" : ")) {
		            	
		            	String[] info = input.split(" : ",2);
		            	System.out.println(info[0]);
		            	copy = parseInput(info[0],copy);
		            	if(!info[1].equals(copy)) {
		            		System.out.println("Test Failed: input("+info[0]+"), Expected("+info[1]+"), Got("+copy+")");
		            	}else {
		            		System.out.println("Test Success");
		            	}
		            }else {
		            	System.out.println(input);
		            	copy = parseInput(input,copy);
		            }
		            System.out.println();
		        }while(parser.hasNextLine());
				parser.close();
			}
    	}else if(splitInput[0].equalsIgnoreCase(">C") ||splitInput[0].equalsIgnoreCase("<C")){
            if(splitInput[0].equalsIgnoreCase(">C")){
                if(input.length() > 2){
                    copy = applyCipher(input.substring(3), caesar);
                    System.out.println(copy);
                }else{
                    if(copy.length() !=0){
                        copy = applyCipher(copy, caesar);
                        System.out.println(copy);
                    }else{
                        System.out.println("Error: Nothing Copied");
                    }
                }
            }else{
                if(input.length() > 2){
                    copy = unapplyCipher(input.substring(3), caesar);
                    System.out.println(copy);
                }else{
                    if(copy.length() !=0){
                        copy = unapplyCipher(copy, caesar);
                        System.out.println(copy);
                    }else{
                        System.out.println("Error: Nothing Copied");
                    }
                }
            }
        }else if(splitInput[0].contains(">C") || splitInput[0].contains("<C")) {
        	//reduce length by putting in method at some point, small differences than one above but still
        	int displacement = Integer.parseInt(splitInput[0].replace(">C", "").replace("<C", ""));
        	String changeInput = "";
        	if(!input.replace(splitInput[0],"").equals("")){
                changeInput = input.replace(splitInput[0],"");
            }else{
                //then we want to apply effect on copy
            	changeInput = copy;
            }
        	//encrypt or decrypt
        	if(input.contains(">")) {
        		copy = applyCipher(changeInput.trim(), caesar, displacement); //input trimmed, might be weird compared to other method
                System.out.println(copy);
        	}else {
        		copy = unapplyCipher(changeInput.trim(), caesar, displacement);
                System.out.println(copy);
        	}
        }else {
        	System.out.println(input);
        }
        return copy;
    }
    
    public static String applyCipher(String input, String base, String cipher, int y ){
        String output = "";
        String lbase = base.toLowerCase();
        String lcipher = cipher.toLowerCase();
        if(base.length() != cipher.length()){
            return "Cipher Size Error";
        }

        for(Character a : input.toCharArray()){
        	if(!Character.isSpaceChar(a)) {
	            int x = base.indexOf(a);
	            if(x!= -1){
	                output += cipher.charAt((x+y)%cipher.length()); 
	            }
	            x = lbase.indexOf(a);
	            if(x!= -1){
	                output += lcipher.charAt((x+y)%cipher.length()); 
	            }
        	}else {
        		output += a;
        	}
        }
        return output;
    }
    
    public static String applyCipher(String input, String base, String cipher){
    	return applyCipher(input,base, cipher, 0);
    }
    
    public static String applyCipher(String input, String cipher, int x){
        return applyCipher(input,"ABCDEFGHIJKLMNOPQRSTUVWXYZ", cipher, x);
    }
    
    public static String applyCipher(String input, String cipher){
        return applyCipher(input,"ABCDEFGHIJKLMNOPQRSTUVWXYZ", cipher);
    }

    public static String unapplyCipher(String input, String cipher, int x){
        return applyCipher(input, cipher , "ABCDEFGHIJKLMNOPQRSTUVWXYZ", x);
    }
    
    public static String unapplyCipher(String input, String cipher){
        return applyCipher(input, cipher , "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }
}