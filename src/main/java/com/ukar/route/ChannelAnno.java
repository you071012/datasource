package com.ukar.route;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ChannelAnno {
    ChannelEnum value();
}
