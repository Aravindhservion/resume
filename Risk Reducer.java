
package task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CopyDirectoryExample {

	public static void main(String... args) throws IOException {
    try {
			
			Properties properties = new Properties();
			properties.load(new FileInputStream("src/task/citi.properties"));
			 String srcpath=properties.getProperty("srcpath");
				File[] srcfiles = new File(srcpath).listFiles();
				//File filename = null;
				File[] destfiles = new File(properties.getProperty("destpath")).listFiles();
				
				String path=properties.getProperty("destpath");
				String userin=null;
				int[] Filename;
				ArrayList<String> FoldersName = new ArrayList<String>();
				Scanner sc=new Scanner(System.in);
				File destfiles1 = new File(properties.getProperty("destpath"));
				String loop=properties.getProperty("Loop");
				
				if(loop.equalsIgnoreCase("yes")){
					System.out.println("Do you want to continue to browse the folder(Y/N):");
					if(sc.nextLine().equalsIgnoreCase("y"))
					{
						
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
							i--;
							
							System.out.println("Do you want to continue(Y/N):");
							if(sc.nextLine().equalsIgnoreCase("y"))
							{
								
							}
							else{
								break;
							}
								
						}
					}
				
				}
				
				File dest = new File(properties.getProperty("destpath"));
				for (File file : srcfiles) {
					if(file.isDirectory()){
							copyFolder(file,destfiles1,srcpath);
					}
					else
					showFiles(destfiles1.listFiles(),file);
			    }
				//File filename=new File("/Users/aravindh/Desktop/src/config.php");
			    
    	    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
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
	 public static void copyFolder(File src,File dest,String srcpath)
		    	throws IOException{
		// File dest = new File("/Users/aravindh/Desktop/dest");
		        
		    	for(File file : src.listFiles()){
		    		if(file.isDirectory()){
		    			copyFolder(file,dest,srcpath);
		    		}
		    		else
		    		{
		    			
		    			pathSpecificCopy(file,dest,srcpath);
		    		}
		    	}
	 }
	 
	 public static void pathSpecificCopy(File src,File dest,String srcpath) throws IOException{
		 
		 for (File file : dest.listFiles()) {
		        if (file.isDirectory() ) {
		            //System.out.println("Directory: " + file.getName());
		            //copyFolder(file,dest); // Calls same method again.
		        	pathSpecificCopy(src,file,srcpath);
		        	
		        } 
		        else {
		        	String sour=src.getCanonicalPath();
		        	String des=file.getCanonicalPath();
		        	System.out.println(sour+"=="+des);
		        	//sour="/xxx/xx/resume.docx";
		        	sour=sour.replaceAll(srcpath,"");
		        	
		        	//String sour1="y/yy/config.php";
		        	System.out.println(sour);
		        	if(des.endsWith(sour) ){
		        	System.out.println("success");
		        	try {	        		
		        		//String d=file.getCanonicalPath();
						//File dest=new File(d);
		        	InputStream in = new FileInputStream(src);
	    	        OutputStream out = new FileOutputStream(file);

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
}

    
