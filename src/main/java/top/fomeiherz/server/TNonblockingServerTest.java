package top.fomeiherz.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import top.fomeiherz.service.HelloWorldService;
import top.fomeiherz.service.impl.HelloWorldServiceImpl;

/**
 * @description:
 * @author: fomy
 * @create: 2020/5/21 11:49
 **/
public class TNonblockingServerTest {

    public static void main(String[] args) throws Exception {
        TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(1111);

        TNonblockingServer.Args tnbArgs = new TNonblockingServer.Args(tnbSocketTransport);
        tnbArgs.processor(tprocessor);
        tnbArgs.transportFactory(new TFramedTransport.Factory());
        tnbArgs.protocolFactory(new TCompactProtocol.Factory());

        // 使用非阻塞式IO服务端和客户端需要指定TFramedTransport数据传输的方式
        TServer server = new TNonblockingServer(tnbArgs);
        System.out.println("Running Non-blocking Server");
        server.serve();
    }

}
