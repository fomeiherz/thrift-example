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
 * @create: 2020/5/21 14:00
 **/
public class TThreadedSelectorClientTest {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread("Thread " + i) {
                @Override
                public void run() {
                    // 设置传输通道 对于非阻塞服务 需要使用TFramedTransport(用于将数据分块发送)
                    for (int j = 0; j < 10; j++) {
                        TTransport transport = null;
                        try {
                            transport = new TFramedTransport(new TSocket(ServerConfig.SERVER_IP, ServerConfig.SERVER_PORT, ServerConfig.TIMEOUT));
                            TProtocol protocol = new TBinaryProtocol(transport);
                            HelloWorldService.Client client = new HelloWorldService.Client(protocol);
                            transport.open();
                            String result = client.say("ThreadedSelector Client");
                            System.out.println("Result =: " + result);
                            transport.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            // 关闭传输通道
                            transport.close();
                        }
                    }
                }
            }.start();
        }
    }

}
