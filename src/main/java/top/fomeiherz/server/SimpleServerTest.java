package top.fomeiherz.server;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import top.fomeiherz.service.HelloWorldService;
import top.fomeiherz.service.impl.HelloWorldServiceImpl;

import java.net.ServerSocket;

/**
 * @description:
 * @author: fomy
 * @create: 2020/5/21 11:02
 **/
public class SimpleServerTest {

    public static void main(String[] args) throws Exception {
        // 创建一个socket连接
        ServerSocket serverSocket = new ServerSocket(1111);
        // Thrift封装的Socket连接
        TServerSocket serverTransport = new TServerSocket(serverSocket);
        // Processor：负责从输入中获取请求信息，调用用户自己实现的服务，并将结果写入到输出中
        HelloWorldService.Processor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloWorldServiceImpl());

        // 使用二进制协议传输
        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
        // 封装请求参数
        TSimpleServer.Args tArgs = new TSimpleServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.protocolFactory(protocolFactory);

        // 简单的单线程服务模型 一般用于测试
        TServer tServer = new TSimpleServer(tArgs);
        System.out.println("Running Simple Server");
        // 启动服务
        tServer.serve();
    }

}
