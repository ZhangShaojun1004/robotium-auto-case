package PACKAGE.test.helper;
import java.io.File;
import java.io.IOException;

public class FileOperation {

	public void createDirectory(String directory){  
	    File file = new File(directory);  
	         file.mkdirs();  
	}  
	
	public void deleteDirectory(String filePath){ 	
	    File file = new File(filePath);  
	    if(!file.exists()){  
	        return;  
	    }  
	      
	    if(file.isFile()){  
	        file.delete();  
	    }else if(file.isDirectory()){  
	        File[] files = file.listFiles();  
	        for (File myfile : files) {  
	            deleteDirectory(filePath + "/" + myfile.getName());  
	        }  	          
	        file.delete();  
	    }  
	}  
	
	public void pullPhotosFromPhone(String directory){
		String full_path="/Users/guoshun/Documents/workspace/Robotium_auto_test/ScreenShot/"+directory;
		deleteDirectory(full_path);
		String commands ="/Users/guoshun/Documents/adt-android/sdk/platform-tools/adb pull /storage/sdcard0/Robotium-Screenshots "+full_path; 
		System.out.println(commands);
		try {
		   Runtime run=Runtime.getRuntime();
		   run.exec(commands);
		} catch (IOException e) {			
		   e.printStackTrace();
		}			
	} 
	public void deletePhotoOnPhone(){
		String command="/Users/guoshun/Documents/adt-android/sdk/platform-tools/adb shell rm -rf /storage/sdcard0/Robotium-Screenshots";
		System.out.println(command);
		try {
			   Runtime run=Runtime.getRuntime();
			   run.exec(command);
			} catch (IOException e) {			
			   e.printStackTrace();
			}
	}
	

}
