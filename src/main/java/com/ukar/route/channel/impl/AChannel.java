package com.ukar.route.channel.impl;

import com.ukar.route.ChannelAnno;
import com.ukar.route.ChannelEnum;
import com.ukar.route.channel.BaseChannel;
import org.springframework.stereotype.Component;

/**
 * @author jia.you
 * @date 2018/12/18
 */
@ChannelAnno(value = ChannelEnum.A)
@Component
public class AChannel implements BaseChannel {
    @Override
    public void doSomething() {
        System.out.println("This is A execute...................");
    }
}
