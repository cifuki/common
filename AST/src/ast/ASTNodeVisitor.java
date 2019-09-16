package ast;

import java.awt.List;
import java.beans.Expression;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
 
public class ASTNodeVisitor extends ASTVisitor {
 
	public Map m=new HashMap();//��¼�������������
	public Set tokens=new HashSet();
	public String MethodName ="";
	public String Parameter ="";
	public ArrayList<String> APIsq = new ArrayList<String>();
	public String comments="";
	PrintWriter outputStream=null;
	//@Override
	/*public boolean visit(FieldDeclaration node) {
		for (Object obj: node.fragments()) {
			VariableDeclarationFragment v = (VariableDeclarationFragment)obj;
			System.out.println("Field:\t" + v.getName());
			
		}
		
		return true;
	}*/
    
	//�õ�APIsq 
	@Override
	public boolean visit(MethodInvocation node) {
		if((m!=null) &&(node.getExpression()!=null)) {
		if(m.containsKey(node.getExpression().toString())) {
			APIsq.add(m.get(node.getExpression().toString())+"."+node.getName().toString());//������÷����Ķ���
			tokens.add(node.getName().toString());
			//System.out.println(node.arguments());//�����еĲ���
	 	//System.out.println(node.typeArguments());
		}else {
			APIsq.add(node.getExpression().toString()+"."+node.getName());
			tokens.add(node.getExpression().toString());
		 	tokens.add(node.getName().toString());
			}
		}
	 return true;  
	}
	
	/*�õ������� ����������Parameter��
	 * ���������еı������滻�ɱ�������
	 * ��ȡtokens
	 */
	@Override
	public boolean visit(MethodDeclaration node) {
		comments=node.getJavadoc().toString();
		//outputStream.println(node.getJavadoc());//get javadoc
		//outputStream.println("Method:\t" + node.getName());
		MethodName="Method:\t" + node.getName();
		//tokens.add(node.getName().toString());
		//outputStream.println("Parameter list of Method:\t" + node.parameters());
		Parameter="Parameter list of Method:\t" + node.parameters();
				for(Object s : node.getBody().statements()) {
					if(s instanceof VariableDeclarationStatement) {
						VariableDeclarationStatement d;
						d = (VariableDeclarationStatement)s;
			//System.out.println("Field:\t" + d.getType());
						for(Object f : d.fragments()) {
							VariableDeclarationFragment e=(VariableDeclarationFragment)f;
			//System.out.println("\t" + e.getName());
							m.put(e.getName().toString(), d.getType().toString());
							tokens.add(d.getType().toString());
				}
			}
		}
		return true;
	}
 
	
	//ͳһ��������� tokens APIsq ��ע��
	@Override
	public void endVisit(TypeDeclaration node) {
		//System.out.println("Class:\t" + node.getName());
		APISort(APIsq);
		outputStream.println(MethodName);
		outputStream.println(Parameter);
		outputStream.println(APIsq);
		outputStream.println("tokens:");
		String[] basictypes= {"byte","short","int","long","float","double","boolean","char"};
		for(String s:basictypes) {
			tokens.remove(s);
		}
		outputStream.println(tokens);
		outputStream.println(comments);
		outputStream.close();
	}
	
	//����һ���ļ�����
	@Override
	public boolean visit(TypeDeclaration node) {
		try{
			outputStream=new PrintWriter(new FileOutputStream("E://"+node.getName()+".txt"));
			APIsq.add("API sequence:");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void APISort(ArrayList<String> APIsq) {
		/*for(int i=0; i<APIsq.size()-1; i++) {
			int minIndex = i;
			for(int j=minIndex+1;j<APIsq.size();j++){
                if(APIsq.get(j).compareToIgnoreCase(APIsq.get(minIndex))>=0){
                    minIndex = j;
                }
            }
			APIsq.
			int temp = APIsq.get(i);
			APIsq.get(i) = APIsq.get(minIndex);
			APIsq[minIndex] = temp; 
		}*/
		APIsq.sort(null);
	}
}