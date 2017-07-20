/**
 * Created by wxj on 2017/7/20.
 */
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
/**
 * Created by wxj on 2017/7/19.
 */
public class HomeWork1 {

    public static void main(String[] args) {
        Properties prop = new Properties();
        HashMap <String,String> mapa= new HashMap<String,String>();
        HashMap <String,String> mapb= new HashMap<String,String>();

        InputStream in = null;
        InputStream in1 = null;
        FileWriter write = null;

        try{

            in = new BufferedInputStream(new FileInputStream(new File("/Users/wxj/HomeWork/src/a.properties")));
            in1 = new BufferedInputStream(new FileInputStream(new File("/Users/wxj/HomeWork/src/b.properties")));
            File file = new File("/Users/wxj/HomeWork/diff.txt");
            write = new FileWriter(file.getName());

            //加载
            prop.load(in);
            //得到prop对象的所有键的集合
            Set<String> set = prop.stringPropertyNames();

            //将键和值写入mapa中
            for(String key : set){
                mapa.put(key,prop.getProperty(key));
                // System.out.print(key+"-"+prop.getProperty(key)+"\n");

            }

            prop.clear();

            //加载
            prop.load(in1);

            Set<String> set1 = prop.stringPropertyNames();


            for(String key : set1){
                mapb.put(key,prop.getProperty(key));
                //System.out.print(key+"-"+prop.getProperty(key)+"\n");
            }



            //使用第2个集合map做比较

            for(String key : set1) {
                /*
                这个遍历操作能完成两种情况：
                 1.a文件存在，b文件存在，并且值不相等
                 2.a文件没有，b文件有
                */

                if (mapa.containsKey(key) && !mapa.get(key).equals(mapb.get(key))) {
                    String s = "-" + key + "=" + mapa.get(key) + ";" + "+" + key + "=" + mapb.get(key) + "\n";
                    write.append(s);
                    System.out.print(s);
                }else if(!mapa.containsKey(key)){
                    String s = "+" + key + "=" + mapb.get(key)+"\n";
                    write.append(s);
                    // fw.write(s);
                    System.out.print(s);
                }

            }

            /*
              这个遍历操作主要完成一种情况
               3.a文件有，b文件没有
             */
            for(String key : set){
                if(!mapb.containsKey(key)){
                    String s ="-" + key + "=" +mapa.get(key)+"\n";
                    write.append(s);
                    // fw.write(s);
                    System.out.print(s);
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in!=null){
                try{
                    in.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            if(in1!=null){
                try{
                    in1.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

            if(write!=null){
                try{
                    write.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }

        }


    }

}

