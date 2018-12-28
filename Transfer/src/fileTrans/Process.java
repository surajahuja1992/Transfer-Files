package fileTrans;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Process
{
    public static void main(String args[])
    {
    	List<String> paths = new ArrayList<String>();
        try {
			BufferedReader br =  new BufferedReader(new FileReader(args[0]));
			String line = br.readLine();
			while (line != null) {
				paths.add(line);
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			System.out.println("Error in reading Config File"+e);
		}
    	Date date = new  Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");  
        String strDate= formatter.format(date);
    	File srcFolder = new File(paths.get(0));
        File destFolder = new File(paths.get(1)+"\\"+strDate);     

        if(!srcFolder.exists())
        {
             System.out.println("Source Path does not exist.");
             System.exit(0);
        }
        else{

               try{
            	   copyDirectory(srcFolder,destFolder);
                          }
               catch(Exception e)
                {
            	   System.out.println("Error in copying files");
                   System.exit(0);
                    }
            }
        System.exit(0);
    }

    public static void copyDirectory(File src , File target) throws IOException 
    {
        if (src.isDirectory()) 
        {
        	if (!target.exists())
        	{
        		target.mkdir();
                }
        		String[] children = src.list();
                for (int i=0; i<children.length; i++) 
                {
                	copyDirectory(new File(src, children[i]),new File(target, children[i]));
                }
        }
        else 
        {
        	InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(target);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) 
            {
            	out.write(buf, 0, len);
            }
            in.close();
            out.close();
         }
    }    

}
