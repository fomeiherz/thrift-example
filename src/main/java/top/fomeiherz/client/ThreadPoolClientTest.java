package top.fomeiherz.client;

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
public class ThreadPoolClientTest {

    public static void main(String[] args) throws Exception {
        TTransport transport = new TSocket("127.0.0.1", 1111, 10);
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);

        transport.open();
        String result = client.say("ThreadPoolClient");
        System.out.println("Result =: " + result);
        transport.close();
    }

}
