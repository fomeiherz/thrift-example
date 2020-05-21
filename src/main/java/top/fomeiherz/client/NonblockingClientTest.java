package top.fomeiherz.client;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import top.fomeiherz.service.HelloWorldService;

/**
 * @description:
 * @author: fomy
 * @create: 2020/5/21 11:13
 **/
public class NonblockingClientTest {

    public static void main(String[] args) throws Exception {
        TTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 1111, 10));
        // 协议要和服务端一致
        // TCompactProtocol：高效率的、密集的二进制编码格式进行数据传输
        TProtocol protocol = new TCompactProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);
        transport.open();

        String result = client.say("NonBlockingClient");
        System.out.println("Result =: " + result);
        transport.close();
    }

}
