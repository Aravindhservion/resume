package task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Aswin {

	public static void main(String... args) throws IOException {
		File[] srcfiles = new File("/Users/aravindh/Desktop/src").listFiles();
		//File filename = null;
		File[] destfiles = new File("/Users/aravindh/Desktop/dest").listFiles();
		
		String path="/Users/aravindh/Desktop/dest";
		String userin=null;
		int[] Filename;
		ArrayList<String> FoldersName = new ArrayList<String>();
		Scanner sc=new Scanner(System.in);
		File destfiles1 = new File("/Users/aravindh/Desktop/dest");
		for(int i=0;i<=2;i++){
		for(File file : destfiles1.listFiles()){
			if(file.isDirectory() && !file.getName().startsWith(".") && !file.isHidden()){
				System.out.println(file.getName()+"\n");
				FoldersName.add(file.getName());
			}
		}
		
		System.out.println("enter ur choice:\n");
		userin=sc.nextLine();
		path=path.concat("/");
		path=path.concat(FoldersName.get(Integer.parseInt(userin)-1));
		System.out.println(path);
		destfiles1=new File(path);
		FoldersName.removeAll(FoldersName);
		}
		File dest = new File("/Users/aravindh/Desktop/dest");
		for (File file : srcfiles) {
			if(file.isDirectory()){
				copyFolder(file,dest);
			}
			showFiles(destfiles1.listFiles(),file);
	    }
		//File filename=new File("/Users/aravindh/Desktop/src/config.php");
	    
	    
	}

	public static void showFiles(File[] files,File filename) throws IOException {
		//File dest1 = new File("/Users/aravindh/Desktop/dest");
	    for (File file : files) {
	        if (file.isDirectory() ) {
	            //System.out.println("Directory: " + file.getName());
	            showFiles(file.listFiles(),filename); // Calls same method again.
	        } 
	        else {
	        	//System.out.println("File: " + file.getName());
	        	if(file.getName().equalsIgnoreCase(filename.getName())){
	        		//System.out.println("no");
	        		try {	        		
	        		String d=file.getCanonicalPath();
					File dest=new File(d);
	        	InputStream in = new FileInputStream(filename);
    	        OutputStream out = new FileOutputStream(dest);

    	        byte[] buffer = new byte[1024];

    	        int length;
    	        //copy the file content in bytes
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }

    	        in.close();
    	        out.close();
    	        System.out.println("File copied from src :"  + dest);
	            //System.out.println("File: " + file.getName());
	        	}

				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        }
	    }
	}
	
}
	 public static void copyFolder(File src,File dest)
		    	throws IOException{
		// File dest = new File("/Users/aravindh/Desktop/dest");
		        
		    	if(dest.isDirectory()){
		    		//dest.delete();
		    		
		    		for(File file: dest.listFiles()) 
		    		    if (!file.isDirectory()) 
		    		        file.delete();
		    		
		    		System.out.println("destination directory is deleted");
		    	}
		    	
		    	
		    	if(src.isDirectory()){

		    		//if directory not exists, create it
		    		if(!dest.exists()){
		    		   dest.mkdir();
		    		   dest.renameTo(src);
		    		   System.out.println("Directory copied from "
		                              + src + "  to " + dest);
		    		}

		    		//list all the directory contents
		    		String files[] = src.list();

		    		for (String file : files) {
		    		   //construct the src and dest file structure
		    		   File srcFile = new File(src, file);
		    		   File destFile = new File(dest, file);
		    		   //recursive copy
		    		   copyFolder(srcFile,destFile);
		    		}

		    	}
	 }
	
}
