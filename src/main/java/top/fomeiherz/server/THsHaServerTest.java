package top.fomeiherz.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import top.fomeiherz.config.ServerConfig;
import top.fomeiherz.service.HelloWorldService;
import top.fomeiherz.service.impl.HelloWorldServiceImpl;

/**
 * @description:  THsHaServer服务，默认最小线程数：5，最大线程数为int最大值。
 * @author: fomy
 * @create: 2020/5/21 13:42
 **/
public class THsHaServerTest {

    public static void main(String[] args) throws Exception {
        TNonblockingServerSocket tnbSocketTransport = new TNonblockingServerSocket(ServerConfig.SERVER_PORT);
        TProcessor tprocessor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());
        // 半同步半异步
        THsHaServer.Args thArgs = new THsHaServer.Args(tnbSocketTransport);
        thArgs.processor(tprocessor);
        thArgs.transportFactory(new TFramedTransport.Factory());
        thArgs.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new THsHaServer(thArgs);
        System.out.println("Running HsHa Server");
        server.serve();
    }

}
