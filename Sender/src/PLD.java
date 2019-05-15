public class PLD {
    /**
     * PLD模块是为了实现丢包的模拟而设计的
     * 在发送STP数据报前，会先让PLD进行判断
     */
    double pDrop;//丢包的概率
    int seedDrop;//丢包的随机数种子



    public PLD(double pDrop,int seedDrop){
        this.pDrop=pDrop;
        this.seedDrop=seedDrop;
    }
    /**
     * TODO:判断是否需要丢包，需要则返回True;不需要则返回False
     * @param
     * @return
     */
    public boolean makeLostdecisions(){
        return false;
    }

}
