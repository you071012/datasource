package com.ukar.mock;

import com.sun.tools.internal.ws.wsdl.document.soap.SOAPUse;
import com.ukar.entity.User;
import com.ukar.mapper.UserMapper;
import com.ukar.service.UserService;
import com.ukar.service.impl.UserServiceImpl;
import org.hamcrest.Matcher;
import org.hamcrest.core.IsSame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

/**
 * @author jia.you
 * @date 2018/12/19
 */
public class MockUserServiceTest {

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserMapper userMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(userMapper.selectByPrimaryKey(1L)).thenReturn(new User(1L
                , "zhangsan", "123456"));

    }

    @Test
    public void testFindUser() {
        User user = userService.findById(1L);
        Assert.assertEquals(user.getName(), "zhangsan");
        Assert.assertThat(user.getName(), IsSame.sameInstance("zhangsan"));
        Assert.assertTrue(user.getName().equals("zhangsan"));
    }

    /**
     * when...thenReturn 和 doReturn...when区别
     */
    @Test
    public void test() {
        List list1 = Mockito.mock(ArrayList.class);
        List list2 = Mockito.mock(ArrayList.class);

        list1.add("foo1");

        Mockito.when(list1.get(0)).thenReturn("change foo1");
        System.out.println(list1.get(0));

        Mockito.doReturn("foo2").when(list2).get(0);
        System.out.println(list2.get(0));

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str;
        while (true) {
            str = scanner.next();
            str = str.replace("吗", "");
            str = str.replace("?", "!");
            str = str.replace("？", "！");
            System.out.println(str);
        }
    }

}
