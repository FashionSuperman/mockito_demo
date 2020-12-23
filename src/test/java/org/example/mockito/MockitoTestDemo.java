package org.example.mockito;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.InOrder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 *
 * @author zhaodongchao@yeelight.com
 * @date 2020/12/23 11:05 上午
 */
public class MockitoTestDemo {

    @Test
    public void testMock(){
        List list = mock(List.class);
        list.add("aaa");
        String firstStr = (String) list.get(0);
        String secondStr = (String) list.get(1);
        Assert.assertNull(firstStr);
        Assert.assertNull(secondStr);
    }

    @Test
    public void testSpy(){
        List listOri = new ArrayList();
        List list = spy(listOri);
        list.add("aaa");
        String firstStr = (String) list.get(0);
        Assert.assertEquals("aaa",firstStr);
    }

    @Test
    public void testSpyCopy(){
        List listOri = new ArrayList();
        listOri.add("originA");
        List list = spy(listOri);
        list.add("aaa");

        Assert.assertEquals(1,listOri.size());
        Assert.assertEquals(2,list.size());
    }

    @Test
    public void testStub(){
        List list = mock(List.class);
        when(list.get(0)).thenReturn("aaa");

        String firstStr = (String) list.get(0);
        Assert.assertEquals("aaa",firstStr);

        doReturn("bbb").when(list).get(1);
        Assert.assertEquals("bbb",list.get(1));

        doThrow(new RuntimeException()).when(list).clear();

        list.clear();
    }

    @Test
    public void testSpyStub(){
        List listOri = new ArrayList();
        List list = spy(listOri);
        list.add("aaa");

        when(list.remove(0)).thenReturn("aaa");
        String ele0 = (String) list.remove(0);
        Assert.assertEquals(0,list.size());

//        doReturn("aaa").when(list).remove(0);
//        String ele0 = (String) list.remove(0);
//        Assert.assertEquals(1,list.size());
    }

    @Test
    public void testSpyStubException(){
        TestObj testObj = new TestObj();
        testObj = spy(testObj);
//        when(testObj.testMethod(anyString())).thenReturn("a");
        doReturn("mockA").when(testObj).testMethod("a");
        Assert.assertEquals("mockA",testObj.testMethod("a"));
    }

    @Test
    public void testVerify(){
        List list = mock(List.class);
        when(list.get(0)).thenReturn("a");
        list.get(0);

        verify(list,times(1)).get(0);
    }

    @Test
    public void testSpyVerify(){
        List list = spy(new ArrayList());
        list.add("a");
        verify(list,times(1)).add("a");
    }

    @Test
    public void testVerifyInOrder(){
        List one = mock(List.class);
        List two = mock(List.class);

        when(one.add(anyString())).thenReturn(true);
        when(two.add(anyString())).thenReturn(true);

        one.add("one");
        two.add("two");

        InOrder inOrder = inOrder(one, two);

        verify(two).add("two");
        verify(one).add("one");

        inOrder.verify(one).add("one");
        inOrder.verify(two).add("two");
    }

    @Test
    public void testVerifyTimes(){
        List list = mock(List.class);
        when(list.add(anyString())).thenReturn(true);

        list.add("a");
        list.add("a");

        verify(list,times(2)).add("a");
        verify(list,atLeast(1)).add("a");
    }
}
