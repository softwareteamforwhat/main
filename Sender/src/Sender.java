import java.util.ArrayList;

public class Sender {
    private String file;
    private String ip;
    private int port;
    private double pDrop;
    private int seedDrop;
    private int MSS;//MSS是指数据部分的最大字节数，即byte数，1byte=8bit
    private int MWS;
    private int initialTimeout;

    private PLD pld;
    private STP stp;
    private UDP udp;
    private SingleTimer singleTimer;

    private final String sender_logfile="sender_log.txt";

    public Sender(String file,String ip,int port,double pDrop,int seedDrop
    ,int MSS,int MWS,int initialTimeout){
        this.file=file;
        this.ip=ip;
        this.port=port;
        this.pDrop=pDrop;
        this.seedDrop=seedDrop;
        this.MSS=MSS;
        this.MWS=MWS;
        this.initialTimeout=initialTimeout;

        stp=new STP();
        pld=new PLD(pDrop,seedDrop);


    }

    /**
     * 设置不同最大分段大小
     */
    public void setMSS(int MSS){
        this.MSS=MSS;
    }
    /**
     * 设置最大窗口大小
     */
    public void setMWS(int MWS){
        this.MWS=MWS;
    }

    /**
     * 启动一次数据传输，包括建立连接，传送数据，断开连接
     */
    public void send(){
        boolean res=establishConnection();
        if(res){
            String data=readInputFile();

            //TODO:发送数据，发送完成就跳出循环
            ArrayList<WIN> wins=getWINList(data);
            for(WIN w:wins){
                ArrayList<String> listToTrans=w.STPDataGramList;
                while (listToTrans.size()!=0){
                    udp=new UDP();
                    for (String s:listToTrans){
                        if(pld.makeLostdecisions())continue;;
                        String STPdata=stp.pack(s);
                        udp.send(STPdata);
                    }
                    singleTimer=new SingleTimer();
                    ArrayList<String> AckMessage=new ArrayList<>();//保留在限制时间内收到的对已发送数据的确认
                    while (singleTimer.timeLimit()){
                        String message=udp.get();
                        if(stp.isACK(message))AckMessage.add(message);

                    }
                    listToTrans=getListToRetrans(listToTrans,AckMessage);
                }
            }

            endConnection();
        }else{
            System.out.println("连接建立失败");
        }


    }

    /**
     * TODO:建立连接
     * @return 返回建立连接的结果
     */
    private boolean establishConnection(){
        return true;
    }

    /**
     * TODO:断开连接
     * @return
     */
    private void endConnection(){
    }

    /**
     * TODO:读取需要传输的数据
     */
    private String readInputFile(){
        String res="";
        return res;
    }

    /**
     * TODO:将需要传输的数据按照固定窗口大小分割为一个个独立的WIN
     * 每一个WIN中包含了一组将要一起发送的STPDataGram
     * 一个窗口发送完毕并收到确认后进行下一个窗口的发送
     */
    private ArrayList<WIN> getWINList(String data){
        ArrayList<WIN> reslist=new ArrayList<>();
        return reslist;
    }

    /**
     * TODO:根据发出的数据报和收到的确认，得到需要重传的数据报
     */
    private ArrayList<String> getListToRetrans(ArrayList<String> listToTrans,ArrayList<String> ACKMessage){
        ArrayList<String> reslist=new ArrayList<>();
        return reslist;
    }


    public class WIN{
        int max;
        ArrayList<String> STPDataGramList;

        public WIN(int max,ArrayList<String> list){
            this.max=max;
            this.STPDataGramList=list;
        }


    }
}
