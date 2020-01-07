package com.ukar.controller;

import com.ukar.entity.User;
import com.ukar.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jyou on 2018/9/11.
 *
 * @author jyou
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private UserService userService;

    @RequestMapping("/index")
    public Map<String, String> index() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "ukar");
        map.put("age", "18");
        return map;
    }

    @RequestMapping("/selectAllUser")
    public List<User> selectAllUser() {
        return userService.selectAllUser();
    }

    @RequestMapping("/selectAllUserFromSlave")
    public List<User> selectAllUserFromSlave() {
        return userService.selectAllUserFromSlave();
    }

    @RequestMapping("/testTime")
    public Long testTime(@RequestParam(value = "num", defaultValue = "1",
            required = false) String num) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < Integer.valueOf(num); i++) {
            userService.findById(1L);
        }
        long end = System.currentTimeMillis();
        return end - start;
    }
}
