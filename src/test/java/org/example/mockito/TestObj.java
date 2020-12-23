package org.example.mockito;


/**
 *
 * @author zhaodongchao@yeelight.com
 * @date 2020/12/23 2:12 下午
 */
public class TestObj {
    public String testMethod(String param){
        int a = 1/0;
        return param;
    }
}
