package ast;

import java.io.File;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class Main {
	public static String path = "E:\\\\ASTdata\\\\test";
	public Main(String path) {
		CompilationUnit comp = GenAST.getCompilationUnit(path);
		ASTNodeVisitor visitor = new ASTNodeVisitor();
		comp.accept(visitor);
	}
	public static void main(String[] args) throws Exception { 
		ASTNodeVisitor visitor = new ASTNodeVisitor();
		File file1=new File(path);
	   	 if(file1.exists()) {
	   		 File[] sf=file1.listFiles();
	   		 for(int i =0;i<sf.length;i++) {
	   			if(file1.isDirectory()) {
	   				new Main(sf[i].getPath());
	   			}
	   			 }
	      } 
	}
}
