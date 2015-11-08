import java.util.List;


public class PackageManager {
	
	private String package_name;
	private String[] package_dependencies;
	
	public String get_package_name(){return package_name;}
	public void set_package_name(String n){package_name=n;}
	public String[] get_package_dependencies(){return package_dependencies;}
	public void set_package_dependencies(String []arr){
		int i=0;
		for(i=0;i<arr.length;i++)package_dependencies[i]=arr[i];
		}
	public PackageManager(){
		package_name="";
		package_dependencies = new String[10];
	}
	public PackageManager(String n,String []arr){
		package_name=n;
		int i=0;
		for(i=0;i<arr.length;i++)package_dependencies[i]=arr[i];
	}
	
}
