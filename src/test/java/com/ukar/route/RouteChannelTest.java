package com.ukar.route;

import com.ukar.DatasourceApplicationTests;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author jia.you
 * @date 2018/12/18
 */
@SpringBootTest(classes = DatasourceApplicationTests.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class RouteChannelTest {
    @Resource
    private RouteHandle routeHandle;

    @Test
    public void test(){
        routeHandle.doHandle(ChannelEnum.C);
    }
}
