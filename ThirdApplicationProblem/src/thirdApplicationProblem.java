import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;


public class thirdApplicationProblem {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	/*
	 * Програмата е оргарнизирана по следният начин:
	 * -дефиниран е клас за управление на пакети,чиито обекти съдържат 2 неща:
	 * 		*името на пакета
	 * 		*списък със зависимостите на пакета
	 * Потребителя избира директорията в която се намират файловете all_packages.json,dependencies.json
	 * и директорията installed_modules
	 * Всеки един от Файловете се чете ред по ред ,като от тях се извлича само необходимата за обектите от
	 * класа за управление на пакети информация
	 * След като двата файла са прочетени и информацията от тях е извлечена програмата
	 * автоматично започва да преглежда данните извлеченни от файла dependencies.json и започва да инсталира
	 * пакетите посочени е него.За откриване на зависимости се използва рекурсивен алгоритъм който преглежда
	 * всичките пакети(тези взети от dependencies.json като при инсталирането проверява дали някой от тях вече не е 
	 * бил инсталиран).
	 */

	public static String []formatRow(String line){
		/*
		 * Тази функция приема като аргумент една линия(стринг) и премахва от нея всичко което не е
		 * буква или кавичка.Връщаният резултат представлява масив от "думите" записани на една линия.
		 */
		String refinedLine="";
		int i=0;
		double count=0;

			for(i=0;i<line.length();i++){
				while(Character.isLetter(line.charAt(i))||line.charAt(i)=='"'){
					if(line.charAt(i)=='"'){
						refinedLine = refinedLine + " ";
						count++;
						i++;
					}
					else {
						refinedLine=refinedLine+line.charAt(i);
						i++;
					}
				}
			}
			
			String []words = new String[(int) (count/2)];
			for(i=0;i<words.length;i++)words[i]="";
			int j=0;
			for(i=0;i<refinedLine.length();i++){
				if(Character.isLetter(refinedLine.charAt(i))){
					while(Character.isLetter(refinedLine.charAt(i))){
					words[j]=words[j]+refinedLine.charAt(i);
					i++;
					}
					j++;
				}
			}
					
				
		return words;
	}
	public static PackageManager[] extractPackageData(int size,String filepath) throws IOException{
		/*тази функция чете ред по ред съдържанието на файла предаден чрез името му
		 * и го записва в масив от обекти от тип PackageManager,като първата дума
		 * се тълкува като име на пакета,а всяка следваща като част от списъка със зависимости 
		 */
		BufferedReader reader = new BufferedReader(new FileReader(new File(filepath)));
		Scanner read1 = new Scanner(reader);
		PackageManager []result = new PackageManager[size];
		int i=0;
		for(i=0;i<size;i++){
			result[i]=new PackageManager();
		}
		String line="";
		
		int j=0;
		
		while(read1.hasNext()){
			
			line = read1.nextLine();
			String []refinedLine=formatRow(line);
			if(refinedLine.length>1){
				String []dependencyList = new String[refinedLine.length-1];
				for(i=0;i<refinedLine.length;i++){
				if(i==0){
					result[j].set_package_name(refinedLine[i]);
				}
				else {
					dependencyList[i-1]=refinedLine[i];	
				}
			}
			result[j].set_package_dependencies(dependencyList);
			j++;

			}
			
		}
		read1.close();
		return result;
	}
	public static void installDependency(PackageManager[] packages,String pack,List<String> inst_m){
		int i=0,j=0,index=0;
		/*тази функция приема като аргументи масив от обекти от тип PackageManager,име на пакета,който
		 * трябва да се инсталира и списък със инсталирани обекти.
		 *Масивът се обхожда като името на пакета който трябва да се инсталира се сравнява с имената
		 *на пакетите записани в обекта.Ако има съвпадение в имената се четат зависимостите на този обект.
		 *Извежда се съобщение за всяка зависимост.
		 *Проверява се дали някоя от тях не е инсталирана - ако е се извежда съобщение за това.
		 *Ако зависимостта не е инсталирана(не е вдигнат флага) се вика отново тази функция,като 
		 *зависимостта се подава като параметър.Функцията приключва изпълнението си когато стигнем до пакет без зависимости
		 *и/или с инсталирани такива.
		 */
		boolean flag=false;
		for(i=0;i<packages.length;i++){
			if(packages[i].get_package_name().equalsIgnoreCase(pack)){
				String []temporary = packages[i].get_package_dependencies();
				for(j=0;j<temporary.length;j++){
					if(temporary[j]!=null){
						System.out.println("Installing:"+packages[i].get_package_name());
						System.out.println(packages[i].get_package_name() + " needs " + temporary[j]);
						for(index=0;index<inst_m.size();index++){
							if(inst_m.get(index).equalsIgnoreCase(temporary[j])){
								System.out.println(temporary[j] + " already installed");
								flag=true;
								break;
							}															
						
						}
						if(!flag){
							installDependency(packages,temporary[j],inst_m);
							System.out.println(temporary[j] + " installed");
						}
						else flag=false;
							
					}
				}
			}
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		List<String> installed_modules = new ArrayList<String>();
		int all_packages_count=0;
		int dependencies_count=0;
		Scanner input = new Scanner(System.in);
		int i=0,j=0,index=0;
		String path,temp;
		System.out.println("Enter Installation Directory:");
		path=input.next();
		File folder = new File(path);
		System.out.println("Installation Directory Contents:");
		File[] listOfFiles = folder.listFiles();

		for (i=0;i<listOfFiles.length;i++) {
		   if (listOfFiles[i].isFile()) {
			   if(listOfFiles[i].getName().equalsIgnoreCase("all_packages.json")){
				   BufferedReader reader = new BufferedReader(new FileReader(new File(path+"/all_packages.json")));
				   Scanner read = new Scanner(reader);
				   while(read.hasNext()){
					   read.nextLine();
					   all_packages_count++;
				   }
				   read.close();
			   }
			   
			   if(listOfFiles[i].getName().equalsIgnoreCase("dependencies.json")){
				   BufferedReader reader = new BufferedReader(new FileReader(new File(path+"/dependencies.json")));
				   Scanner read = new Scanner(reader);
				   while(read.hasNext()){
					   read.nextLine();
					   dependencies_count++;
				   }
				   read.close();
			   }
				   
		     System.out.println("File:" + listOfFiles[i].getName());		     
		     } else if (listOfFiles[i].isDirectory()) {
		    	 if(listOfFiles[i].getName().equalsIgnoreCase("installed_modules")){
		    		 temp=path+"/installed_modules";
		    		 File tempFolder = new File(temp);
		    		 File[] listTemp = tempFolder.listFiles();
		    		 for(j=0;j<listTemp.length;j++){
		    			 if(listTemp[j].isDirectory()){
		    				 installed_modules.add(listTemp[j].getName()); 
		    			 }
		    		 }
		    	 }
		     System.out.println("Directory:" + listOfFiles[i].getName());
		     }
		   }
		//System.out.println(all_packages_count);
		PackageManager all_packages[] = extractPackageData(all_packages_count,(path+"/all_packages.json"));
		PackageManager dependencies[] = extractPackageData(dependencies_count,(path+"/dependencies.json")); 
		for(i=0;i<dependencies.length;i++){
			if(i==0){
			System.out.println("Preparing: " + dependencies[i].get_package_name());
			String []temporary = dependencies[i].get_package_dependencies();
			File dir = new File(temporary[i]);
			if(dir.mkdir())System.out.println(temporary[i] + " successfully created ");
			}
			else{
				if(dependencies[i].get_package_name()!="")System.out.println("Package " + dependencies[i].get_package_name() + " needed. ");
				String []temporary = dependencies[i].get_package_dependencies();
				for(j=0;j<temporary.length;j++){
					if(temporary[j]!=null){
						System.out.println("Package " + dependencies[i].get_package_name() + " Needs " + temporary[j]);	
						installDependency(all_packages,temporary[j],installed_modules);
						System.out.println("Installation completed successfully.");
					}
					
				}
			}
						
		}
		input.close();
	}


}
