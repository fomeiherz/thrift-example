package top.fomeiherz.service.impl;

import org.apache.thrift.TException;
import top.fomeiherz.service.HelloWorldService;

/**
 * @description:
 * @author: fomy
 * @create: 2020/5/21 11:00
 **/
public class HelloWorldServiceImpl implements HelloWorldService.Iface {
    @Override
    public String say(String username) throws TException {
        return "hello! " + username;
    }
}
