import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by WZES on 2017/2/17.
 */
public class TextGetIPAddress {
    public static void getLocationIP(){
        try{
            InetAddress addr = InetAddress.getLocalHost();
            String hostAddr = addr.getHostAddress();
            String hostName = addr.getHostName();
            System.out.println("本机IP地址：" + hostAddr);
            System.out.println("本机机器名：" + hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public static void getIPByName(String hostName){
        InetAddress addr;
        try {
            addr = InetAddress.getByName(hostName);
            String hostAddr = addr.getHostAddress();
            System.out.println("域名为：" + hostName + "的主机IP地址：" + hostAddr);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
    public static void getAllIPName(String hostName){
        InetAddress [] addrs;
        try {
            addrs = InetAddress.getAllByName(hostName);
            String[] ips = new String[addrs.length];
            System.out.println("域名为" + hostName + "的主机所有的IP地址为：");
            for(int i = 0; i < addrs.length; i++){
                ips[i] = addrs[i].getHostAddress();
                System.out.println(ips[i]);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    public static void getResourceOfHTML(String htmlFile){
        try {
            URL url = new URL(htmlFile);
            Reader reader = new InputStreamReader(new BufferedInputStream(
                    url.openStream()));
            int c;
            while((c = reader.read()) != -1){
                System.out.println((char)c);
            }
            System.out.println();
            reader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        getLocationIP();
        getIPByName("168.192.1.11");
        getAllIPName("168.192.1.11");
        getResourceOfHTML("http://www.soho.com");
    }
}
