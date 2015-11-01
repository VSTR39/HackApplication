import java.util.ArrayList;
import java.util.Scanner;


public class secondApplicationProblem2 {
	public static int checkForwardVertically(int a,int b,char [][]table,String str,int maxCharacters){
		String word="";
		int wordcount=0,x=0;
		while(table[a][b]==str.charAt(x)){
			word=word+str.charAt(x);
			b++;
			x++;
			
			if(x==str.length()||b==maxCharacters)break;			
			
		}
		if(word.equalsIgnoreCase(str))wordcount++;
		return wordcount;
	}//works
	
	public static int checkForwardHorizontally(int a,int b,char [][]table,String str,int numberOfRows){
		String word="";
		int wordcount=0,x=0;
		while(table[a][b]==str.charAt(x)){
			word=word+str.charAt(x);
			a++;
			x++;
			if(x==str.length()||a==numberOfRows)break;			
			
		}
		if(word.equalsIgnoreCase(str))wordcount++;
		return wordcount;
	}
	//works
	
	public static int checkForwardDiagonally(int a,int b,char [][]table,String str,int numberOfRows,int maxCharacters){
		String word="";
		int wordcount=0,x=0;
		while(table[a][b]==str.charAt(x)){
			word=word+str.charAt(x);
			a++;
			b++;
			x++;
			if(x==str.length()||a==numberOfRows||b==maxCharacters)break;
			
		}
		if(word.equalsIgnoreCase(str))wordcount++;
		return wordcount;
	}
	//works
	
	public static int checkBackwardVertically(int a,int b,char [][]table,String str){
		String word="";
		int wordcount=0,x=0;
		while(table[a][b]==str.charAt(x)){			
			word=word+str.charAt(x);
			b--;
			x++;
			if(x==str.length()||b==-1)break;
			
		}
		if(word.equalsIgnoreCase(str))wordcount++;
		return wordcount;
	}
	
	public static int checkBackwardHorizontally(int a,int b,char [][]table,String str){
		String word="";
		
		int wordcount=0,x=0;
		while(table[a][b]==str.charAt(x)){
			
			word=word+str.charAt(x);
			a--;
			x++;
			if(x==str.length()||a==-1)break;
			
		}
		if(word.equalsIgnoreCase(str))wordcount++;
		return wordcount;
	}
	public static int checkBackwardDiagonally(int a,int b,char [][]table,String str){
		String word="";
		int wordcount=0,x=0;
		while(table[a][b]==str.charAt(x)){
			word=word+str.charAt(x);
			a--;
			b--;
			x++;
			if(x==str.length()||a==-1||b==-1)break;
			
		}
		if(word.equalsIgnoreCase(str))wordcount++;
		return wordcount;
	}
	
	public static int checkBackwardMinorDiagonall(int a,int b,char [][]table,String str){
		String word="";
		int wordcount=0,x=0;
		while(table[a][b]==str.charAt(x)){
			word=word+str.charAt(x);
			x++;
			a++;
			b--;
			if(x==str.length()||a==-1||b==-1)break;
			
		}
		if(word.equalsIgnoreCase(str))wordcount++;
		return wordcount;
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0,j=0,wordcount=0,numberOfRows=0,max=0,maxCharacters=0,n=0,count=0;
		String line;
		boolean flag=false;
		ArrayList<String> rows = new ArrayList<String>();
		Scanner input = new Scanner(System.in);
		System.out.println("Enter data to fill the talbe:");
		System.out.println("Each line is a row,and elements must be sepparated with a space,for instance:");
		System.out.println("i v a n");
		System.out.println("--- --- ---");
		System.out.println("e **v** **n** h");
		System.out.println("i n **a** v");
		System.out.println("m v **v** **n**");
		System.out.println("q r **i** t ");
		System.out.println("Will result in the following table:");
		System.out.println("| i    | v     | a     | n     |");
		System.out.println("|---   |---    |---    |---    |");
		System.out.println("| e    | **v** | **n** | h     |");
		System.out.println("| i    | n     | **a** | v     |");
		System.out.println("| m    | v     | **v** | **n** |");
		System.out.println("| q    | r     | **i** | t     |");
		System.out.println("You should chose row size prior to the data entry.");
		System.out.println("Enter number of rows:");
		numberOfRows=input.nextInt();
		line=input.nextLine();
		for(i=0;i<numberOfRows;i++){
			line=input.nextLine();			
			rows.add(line);
		}
		
		int charactersPerRowArray[] = new int [numberOfRows];
		for(i=0;i<numberOfRows;i++)charactersPerRowArray[i]=0;
		for(i=0;i<numberOfRows;i++){
			if(rows.get(i).length()>max){
				max=rows.get(i).length();
			}
		}
		for(i=0;i<numberOfRows;i++){
		if(rows.get(i).length()==max){
			line=rows.get(i);
			for(j=0;j<max;j++){
				if(Character.isLetter(line.charAt(j))){
					charactersPerRowArray[i]++;
					}
				}
			}
		}
		for(i=0;i<numberOfRows;i++){
			if(charactersPerRowArray[i]>maxCharacters)maxCharacters=charactersPerRowArray[i];
			
		}
		i=0;j=0;
		
		char table[][]= new char[numberOfRows][maxCharacters];
		
		System.out.println("Your table is");
		for(i=0;i<numberOfRows;i++){
			
			for(j=0;j<rows.get(i).length();j++){
				
				if(Character.isLetter(rows.get(i).charAt(j))){
					
					table[i][n]=rows.get(i).charAt(j);
					System.out.print(table[i][n]);
					n++;
				}
				
				
			}n=0;System.out.println();
		}
		System.out.println("Enter a word:");
		line=input.next();
		
		i=0;j=0;
		for(i=0;i<3;i++){
			wordcount=wordcount+checkForwardVertically(i,j,table,line,numberOfRows);
			if(i==0&&j==0)wordcount=wordcount+checkForwardDiagonally(i,j,table,line,numberOfRows,maxCharacters);
			for(j=0;j<3;j++){
				if(i==0&&j==0);
				else wordcount=wordcount+checkForwardDiagonally(i,j,table,line,numberOfRows,maxCharacters);
				wordcount=wordcount+checkForwardHorizontally(i,j,table,line,maxCharacters);
			}
			j=i;
			
		}
		i=numberOfRows-1;j=maxCharacters-1;
		for(i=numberOfRows-1;i>=0;i--){
			wordcount=wordcount+checkBackwardVertically(i,j,table,line);
			if(i==0&&j==0)wordcount=wordcount+checkBackwardDiagonally(i,j,table,line);
			wordcount = wordcount + checkBackwardMinorDiagonall(i,j,table,line);
			for(j=maxCharacters-1;j>=0;j--){
				wordcount=wordcount+checkBackwardHorizontally(i,j,table,line);
				if(i==0&&j==0);
				else wordcount=wordcount+checkBackwardDiagonally(i,j,table,line);
				wordcount = wordcount + checkBackwardMinorDiagonall(i,j,table,line);
			}

			 j=maxCharacters-1;
			
		}
		
		System.out.println("wordcount:"+wordcount);
	}

}