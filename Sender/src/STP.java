public class STP {
/**
 * STP类描述了STP协议的具体内容，即说明了一个STP数据报的格式
 * 一个STP数据报包括32位的序列号(seq),32位的确认号(ack,确认号仅当数据报类型为ACK时有效)，4位的标记位，最大长度不超过MSS的数据部分
 * STP类提供了将数据封装为一个STP数据报的方法和将一个STP数据报解压为数据的方法
 * STP类也提供了判断一个STP数据报的基本类型的方法，包括
 * SYN:建立连接数据报，标记位为1000
 * FIN:断开连接数据报，标记位为0100
 * Data:传输数据的数据报，标记位为0001
 * ACK：确认Data的数据报，标记位为0010
 * SYNACK:确认SYN的数据报，标记位为1010
 * FINACK:确认FIN的数据报，标记位为0110
 *
 */

/**
 * @param data:需要打包的数据
 * @return String
 *
 */
public String pack(String data){
    String STPDataGram="";
    return STPDataGram;
}


/**
 * @param DataGram:数据报类型必须为Data
 * @return String
 */
public String unpack(String DataGram){
    String data="";
    return data;
}

/**
 * @param STPDataGram;
 */
public boolean isACK(String STPDataGram){
    return true;
}
}
