package top.fomeiherz.client;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import top.fomeiherz.config.ServerConfig;
import top.fomeiherz.service.HelloWorldService;

/**
 * @description:
 * @author: fomy
 * @create: 2020/5/21 13:46
 **/
public class THsHaClientTest {

    public static void main(String[] args) throws Exception {
        TTransport transport = new TFramedTransport(new TSocket(ServerConfig.SERVER_IP, ServerConfig.SERVER_PORT, ServerConfig.TIMEOUT));
        // 协议要和服务端一致
        TProtocol protocol = new TBinaryProtocol(transport);
        HelloWorldService.Client client = new HelloWorldService.Client(protocol);
        transport.open();

        String result = client.say("HsHaClient");
        System.out.println("Result =: " + result);
        transport.close();
    }

}
