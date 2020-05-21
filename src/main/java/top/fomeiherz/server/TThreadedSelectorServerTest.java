package top.fomeiherz.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import top.fomeiherz.config.ServerConfig;
import top.fomeiherz.service.HelloWorldService;
import top.fomeiherz.service.impl.HelloWorldServiceImpl;

/**
 * @description:
 * @author: fomy
 * @create: 2020/5/21 13:57
 **/
public class TThreadedSelectorServerTest {

    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(ServerConfig.SERVER_PORT);
        TProcessor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        // 多线程半同步半异步
        TThreadedSelectorServer.Args ttssArgs = new TThreadedSelectorServer.Args(serverSocket);
        ttssArgs.processor(processor);
        ttssArgs.protocolFactory(new TBinaryProtocol.Factory());
        // 使用非阻塞式IO时 服务端和客户端都需要指定数据传输方式为TFramedTransport
        ttssArgs.transportFactory(new TFramedTransport.Factory());

        // 多线程半同步半异步的服务模型
        TThreadedSelectorServer server = new TThreadedSelectorServer(ttssArgs);
        System.out.println("Running ThreadedSelector Server");
        server.serve();
    }

}
