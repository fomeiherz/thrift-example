package top.fomeiherz.server;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import top.fomeiherz.service.HelloWorldService;
import top.fomeiherz.service.impl.HelloWorldServiceImpl;

import java.net.ServerSocket;

/**
 * @description:
 * @author: fomy
 * @create: 2020/5/21 11:44
 **/
public class ThreadPoolServerTest {

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
        TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverTransport);
        tArgs.processor(processor);
        tArgs.protocolFactory(protocolFactory);

        // 线程池服务模型 使用标准的阻塞式IO 预先创建一组线程处理请求
        // One Thread Per Connection
        TServer tServer = new TThreadPoolServer(tArgs);
        System.out.println("Running ThreadPool Server");
        // 启动服务
        tServer.serve();
    }

}
