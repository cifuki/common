package helper;

import java.io.File;
import java.io.IOException;


public class UpdataFileType {
	public UpdataFileType(String path,String old,String newt) {
		Rename1( path, old, newt);
	}
 public  void Rename1(String path,String old,String newt) {
	 File file=new File(path);
	 if(file.exists()) {
		 File[] sf=file.listFiles();
		 for(File filech:sf) {
			 if(filech.isDirectory()) {
				 Rename1(filech.getPath(),old,newt);
			 }else {
				 if(filech.getName().endsWith(old)) {
					 filech.renameTo(new File(filech.getParent()+"/"+filech.getName().replace(old, newt)));
				 }
			 }
		 }
	 }
 }


 public static void main(String[] s) throws IOException{
	 new UpdataFileType(ast.Main.path,"txt","java");
	}
}
