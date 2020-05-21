package top.fomeiherz.service;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.transport.TIOStreamTransport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @description:
 * @author: fomy
 * @create: 2020/5/21 14:20
 **/
public class PairTest {

    public static void main(String[] args) throws Exception {
        //writeData();
        readData();
    }

    private static void writeData() throws IOException, TException {
        Pair pair = new Pair();
        pair.setKey("我是一个key").setValue("我是一个value");
        // 把数据以二进制方式写入到文件
        // 项目根目录有一个pair.txt文件
        FileOutputStream fos = new FileOutputStream(new File("pair.txt"));
        pair.write(new TBinaryProtocol(new TIOStreamTransport(fos)));
        fos.close();
    }

    private static void readData() throws TException, IOException {
        Pair pair = new Pair();
        FileInputStream fis = new FileInputStream(new File("pair.txt"));
        pair.read(new TBinaryProtocol(new TIOStreamTransport(fis)));
        System.out.println("key => " + pair.getKey());
        System.out.println("value => " + pair.getValue());
        fis.close();
    }

}
