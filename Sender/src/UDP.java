import  java.net.*;
import  java.io.*;
public class UDP {
    private String sendStr = "SendString";
    private String netAddress = "127.0.0.1";
    private  int PORT_NUM = 5066;

    private DatagramSocket datagramSocket;
    private DatagramPacket datagramPacket;

    /**
     * TODO:用UDP发送数据
     *
     * @param sendStr:要发送的数据，这里应该是一个STP数据包
     * @return
     */
    public void send(String sendStr){

    }

    /**
     * TODO:从UDP获取传过来的解压后的数据，不再是一个UDP数据包
     */

    public String get(){
        return "";
    }

    /*
    构造函数明显要重写，这里保留作为参考怎样实现send和get
     */
    public UDP(){
        try {

            /*** 发送数据***/
            // 初始化datagramSocket,注意与前面Server端实现的差别
            datagramSocket = new DatagramSocket();
            // 使用DatagramPacket(byte buf[], int length, InetAddress address, int port)函数组装发送UDP数据报
            byte[] buf = sendStr.getBytes();
            InetAddress address = InetAddress.getByName(netAddress);
            datagramPacket = new DatagramPacket(buf, buf.length, address, PORT_NUM);
            // 发送数据
            datagramSocket.send(datagramPacket);

            /*** 接收数据***/
            byte[] receBuf = new byte[1024];
            DatagramPacket recePacket = new DatagramPacket(receBuf, receBuf.length);
            datagramSocket.receive(recePacket);

            String receStr = new String(recePacket.getData(), 0 , recePacket.getLength());
            System.out.println("Client Rece Ack:" + receStr);
            System.out.println(recePacket.getPort());


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            if(datagramSocket != null){
                datagramSocket.close();
            }
        }
    }
}
