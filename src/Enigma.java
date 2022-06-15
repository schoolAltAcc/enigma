//Artem S.
//Period 7
//Computer Science A
//Enigma checkpoint 6 with NO extra credit (maybe will do some later)

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Enigma{
    public static final String caesar = "BCDEFGHIJKLMNOPQRSTUVWXYZA";
    public static final String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String cba = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
    public static final String rotor1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
    public static final String rotor2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
    public static final String rotor3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
    public static final String rotor4 = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
    public static final String rotor5 = "VZBRGITYUPSDNHLXAWMJQOFECK";
    public static final String reflector = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
	
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
        //file load for school C:\\Users\\2011828\\Desktop\\studentTestCases.txt
        //file load for home C:\Users\wilty\Desktop\coding\java2\studentTestCases
    	if(splitInput[0].equalsIgnoreCase("load")){
    		System.out.println(System.getProperty("user.dir")+"\\studentTestCases.txt");
    		File text = new File(System.getProperty("user.dir")+"\\studentTestCases.txt");
    		if(splitInput.length==2) {
    			text = new File(splitInput[1]);
    		}
    		if(text.exists()) {
    			Scanner parser = new Scanner(text);
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
        		copy = applyCipher(changeInput.trim(), abc, displacement); //input trimmed, might be weird compared to other method
                System.out.println(copy);
        	}else {
        		copy = applyCipher(changeInput.trim(), cba,cba, displacement);
                System.out.println(copy);
        	}
        }else if(splitInput[0].equalsIgnoreCase(">A") ||splitInput[0].equalsIgnoreCase("<A")){
          if(splitInput[0].equalsIgnoreCase(">A")){
                if(input.length() > 2){
                    copy = applyCipher(input.substring(3), abc , rotor1);
                    System.out.println(copy);
                }else{
                    if(copy.length() !=0){
                        copy = applyCipher(copy, abc, rotor1);
                        System.out.println(copy);
                    }else{
                        System.out.println("Error: Nothing Copied");
                    }
                }
           }else{
                if(input.length() > 2){
                    copy = applyCipher(input.substring(3), rotor1, abc);
                    System.out.println(copy);
                }else{
                    if(copy.length() !=0){
                        copy = applyCipher(copy, rotor1,abc);
                        System.out.println(copy);
                    }else{
                        System.out.println("Error: Nothing Copied");
                    }
                }
          }
        }else if(splitInput[0].equalsIgnoreCase(">R") ||splitInput[0].equalsIgnoreCase("<R")){
          if(splitInput[0].equalsIgnoreCase(">R")){
                if(input.length() > 2){
                    copy = applyCipher(input.substring(3), abc , rotor1);
                  	copy = applyCipher(copy, abc, rotor2);
                  	copy = applyCipher(copy, abc, rotor3);
                    System.out.println(copy);
                }else{
                    if(copy.length() !=0){
                        copy = applyCipher(copy, abc, rotor1);
                      	copy = applyCipher(copy, abc, rotor2);
                      	copy = applyCipher(copy, abc, rotor3);
                        System.out.println(copy);
                    }else{
                        System.out.println("Error: Nothing Copied");
                    }
                }
           }else{
                if(input.length() > 2){
                    copy = applyCipher(input.substring(3), rotor3, abc);
                  	copy = applyCipher(copy, rotor2,abc);
                 	copy = applyCipher(copy, rotor1,abc);
                    System.out.println(copy);
                }else{
                    if(copy.length() !=0){
                        copy = applyCipher(copy, rotor3,abc);
                      	copy = applyCipher(copy, rotor2,abc);
                     	copy = applyCipher(copy, rotor1,abc);
                        System.out.println(copy);
                    }else{
                        System.out.println("Error: Nothing Copied");
                    }
                }
          }
        }else if(splitInput.length == 17 || splitInput.length == 16) {
        	String message = "";
        	if(splitInput.length == 17) {
        		message = splitInput[16];
        	}else {
        		message = copy;
        	}
        		//get rotors
        		String[] rotors = new String[3];
        		char[] notches = new char[3]; //third notch unnecessary
        		for(int i = 0; i < 3; i++) {
        			//switch case is just a fancy if so i thought i could use it cuz its simpler to understand
        			switch(splitInput[i]) {
        				case "I":
        					rotors[i] = rotor1;
        					notches[i] = 'R';
        					break;//switch cases fall through so i need to use break, sorry.
        				case "II":
        					rotors[i] = rotor2;
        					notches[i] = 'F';
        					break;
        				case "III":
        					rotors[i] = rotor3;
        					notches[i] = 'W';
        					break;
        				case "IV":
        					rotors[i] = rotor4;
        					notches[i] = 'K';
        					break;
        				case "V":
        					rotors[i] = rotor5;
        					notches[i] = 'A';
        					break;
        				default:
        					System.out.println("Error, bad string on rotor " + (i+1)+", Defaulting to rotor I");
        					rotors[i] = rotor1;
        			}
        		}
        		//get shifts
           		int[] shifts = {abc.indexOf(splitInput[3]),abc.indexOf(splitInput[4]),abc.indexOf(splitInput[5])};
           		//get plugboard
           		String[] plugboard = new String[10];
           		for(int i = 6; i < 16;i++) {
           			plugboard[i-6] = splitInput[i];
           		}
           		//get output
           		copy = enigma(rotors,shifts,notches,plugboard,message);
           		System.out.println(copy);
        	
        }else{
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
    
    //added these in the beginning but too scared to remove for simplicity
    public static String applyCipher(String input, String base, String cipher){
    	return applyCipher(input,base, cipher, 0);
    }
    
    public static String applyCipher(String input, String cipher, int x){
        return applyCipher(input,abc, cipher, x);
    }
    
    public static String applyCipher(String input, String cipher){
        return applyCipher(input,abc, cipher);
    }

    public static String unapplyCipher(String input, String cipher, int x){
        return applyCipher(input, abc, cipher, x);
    }
    
    public static String unapplyCipher(String input, String cipher){
        return applyCipher(input, cipher , abc);
    }
    
    @SuppressWarnings("unused")
	public static String enigma(String[] rotors, int[] rotorshifts, char[] notches , String[] plugboard, String input) {
    	String output = "";
    	//for every char
    	for(Character c : input.toCharArray()) {
    		Character oChar = c;
    		if(!Character.isSpaceChar(c)) {
	    		//right hand rotor += 1 shift with logic for passing notch onto next rotors
	    		rotorshifts[2]++;
	    		//extra credit unused code for notches and double stepping
	    		if(false) {
		    		//notches
		    		if(rotors[2].charAt(rotorshifts[2]%26) == notches[2]) {
		    			rotorshifts[1]++;
		    			if(rotors[1].charAt(rotorshifts[1]%26) == notches[1]) {
							rotorshifts[0]++;
		    			}
	                //double step implementation
		    		}else if(rotors[1].charAt((rotorshifts[1]+1)%26) == notches[1]){
	                    rotorshifts[1]++;
		    			rotorshifts[0]++;
	                }
	    		}
                //temp fix for nonexistant problem, remove later
                for(int i = 0; i < rotorshifts.length; i++){
                    rotorshifts[i] = rotorshifts[i]%26;
                }
	    		//plugboard
	    		oChar = applyPlugboard(plugboard,oChar);
		    	//right rotor
	    		oChar = applyRotor(rotors[2],oChar,rotorshifts[2]); //shift ochar up 3 in abc then down 3 after rotor change in abc test again
		    	//middle rotor
	    		oChar = applyRotor(rotors[1],oChar,rotorshifts[1]);
		    	//left rotor
	    		oChar = applyRotor(rotors[0],oChar,rotorshifts[0]);
		    	//reflector
	    		oChar = applyRotor(reflector,oChar,0);
		    	//left rotor
	    		oChar = unapplyRotor(rotors[0],oChar,rotorshifts[0]);
		    	//middle rotor
	    		oChar = unapplyRotor(rotors[1],oChar,rotorshifts[1]);
		    	//right rotor
	    		oChar = unapplyRotor(rotors[2],oChar,rotorshifts[2]);
		    	//plugboard
	    		oChar = applyPlugboard(plugboard,oChar);
    		}
    		output += oChar;
    	}
    	return output;
    }

	public static Character applyRotor(String rotor, Character oChar, int rotorshift) {
		//up rotor shift letter acording to abc
		int x = (abc.indexOf(oChar)+(rotorshift))%26;
		//find letter in rotor
		oChar = rotor.charAt(x);
		//letter down shift through abc
		oChar = cba.charAt((cba.indexOf(oChar)+(rotorshift))%26);
		return oChar;
	}

    public static Character unapplyRotor(String rotor, Character oChar, int rotorshift) {
		//up rotor shift letter acording to abc
		int x = (abc.indexOf(oChar)+(rotorshift))%26;
		//find letter in rotor
		oChar = (char)(rotor.indexOf((char)(x+65))+65);
		//letter down shift through abc
		oChar = cba.charAt((cba.indexOf(oChar)+(rotorshift))%26);
		return oChar;
	}
	
	public static Character applyPlugboard(String[] plugboard, Character oChar) {
		Character out = oChar;
        //for pair of plugboard settings
		for(String plug : plugboard) {
            //if char equal to a char in plug then set char other char in plug
			if(plug.charAt(0) == oChar) {
				out = plug.charAt(1);
			}else if(plug.charAt(1)== oChar){
				out = plug.charAt(0);
			}
		}
		return out;
	}
}