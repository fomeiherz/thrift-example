package top.fomeiherz.client;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import top.fomeiherz.service.HelloWorldService;

/**
 * @description:
 * @author: fomy
 * @create: 2020/5/21 11:13
 **/
public class SimpleClientTest {

    public static void main(String[] args) {
        TTransport transport = null;
        try {
            transport = new TSocket("127.0.0.1",1111, 10);
            // 建立连接
            transport.open();

            // 使用二进制协议传输
            TProtocol protocol = new TBinaryProtocol(transport);
            // 创建一个客户端
            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
            // 调用远程服务
            String result = client.say("SimpleClient");
            System.out.println("Result =: " + result);
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }

}
