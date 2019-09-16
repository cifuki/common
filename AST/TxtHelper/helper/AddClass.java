package helper;

import java.io.*;



public class AddClass {
    public static BufferedReader bufread;
    //指定文件路径和名称
   // private static String path ;
    //private static String readStr ="";

    public AddClass(String path1,String name) throws IOException {
    	String path=path1;
    	String b;
    	b=AddClass.readTxtFile(path);
    	AddClass.writeTxtFile(path,"abc",b);
    	AddClass.replaceTxtByStr(path,"abc", "public class "+name+" {");
    	AddClass.replaceTxtByStr(path,"abc", "}");
    }

    /**
     * 创建文本文件.
     * @throws IOException 
     * 
     */
    public static void creatTxtFile(String path) throws IOException{
    	File filename = new File(path);
        if (!filename.exists()) {
            filename.createNewFile();
            System.err.println(filename + "已创建！");
        }
    }
    
    /**
     * 读取文本文件.
     * 
     */
    public static String readTxtFile(String path){
    	File filename = new File(path);
        String read;
        String readStr="";
        FileReader fileread;
        try {
            fileread = new FileReader(filename);
            bufread = new BufferedReader(fileread);
            try {
                while ((read = bufread.readLine()) != null) {
                    readStr = readStr + read+ "\r\n";
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return readStr;
    }
    
    /**
     * 写文件.
     * 
     */
    public static void writeTxtFile(String path,String newStr,String readStr) throws IOException{
        //先读取原有文件内容，然后进行写入操作
    	File filename = new File(path);
        String filein = newStr + "\r\n" + readStr + "\r\n" + newStr;
        RandomAccessFile mm = null;
        try {
            mm = new RandomAccessFile(filename, "rw");
            mm.writeBytes(filein);
        } catch (IOException e1) {
            // TODO 自动生成 catch 块
            e1.printStackTrace();
        } finally {
            if (mm != null) {
                try {
                    mm.close();
                } catch (IOException e2) {
                    // TODO 自动生成 catch 块
                    e2.printStackTrace();
                }
            }
        }
    }
    
    /**
     * 将文件中指定内容的第一行替换为其它内容.
     * 
     * @param oldStr
     *            查找内容
     * @param replaceStr
     *            替换内容
     */
    public static void replaceTxtByStr(String path,String oldStr,String replaceStr) {
        String temp = "";
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuffer buf = new StringBuffer();

            // 保存该行前面的内容
            for (int j = 1; (temp = br.readLine()) != null
                    && !temp.equals(oldStr); j++) {
                buf = buf.append(temp);
                buf = buf.append(System.getProperty("line.separator"));
            }

            // 将内容插入
            buf = buf.append(replaceStr);

            // 保存该行后面的内容
            while ((temp = br.readLine()) != null) {
                buf = buf.append(System.getProperty("line.separator"));
                buf = buf.append(temp);
            }

            br.close();
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * main方法测试
     * @param s
     * @throws IOException
     */
    public static void main(String[] s) throws IOException {
    	File file=new File(ast.Main.path);
   	 if(file.exists()) {
   		 File[] sf=file.listFiles();
   		 int i=0;
   		 for(File filech:sf) {
   			 i++;
   			new AddClass(filech.getPath(),"test"+i);
   			 }
   		 }

    }
}