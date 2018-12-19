package com.ukar.route;

import com.ukar.route.channel.BaseChannel;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author jia.you
 * @date 2018/12/18
 */
@Component
public class ChannelFactory {

    private Map<ChannelEnum, BaseChannel> map = new HashMap<>();

    @Resource
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init(){
        Map<String, Object> beans =
                applicationContext.getBeansWithAnnotation(ChannelAnno.class);
        if(beans == null){
           return;
        }

        for(Map.Entry<String, Object> entry : beans.entrySet()){
            Object obj = entry.getValue();
            if(obj instanceof BaseChannel){
                ChannelAnno annotation = obj.getClass().getAnnotation(ChannelAnno.class);
                map.put(annotation.value(), (BaseChannel) obj);
            }
        }
    }

    public BaseChannel getChannel(ChannelEnum channel){
        return map.get(channel);
    }
}
