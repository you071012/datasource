package com.ukar.route;

import com.ukar.route.channel.BaseChannel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author jia.you
 * @date 2018/12/18
 */
@Component
public class RouteHandle {

    @Resource
    private ChannelFactory channelFactory;

    public void doHandle(ChannelEnum channelEnum) {
        BaseChannel channel = channelFactory.getChannel(channelEnum);
        if (channel == null) {
            System.out.println("channel is null.....................");
            return;
        }
        channel.doSomething();
    }
}
