package com.lg.bsp;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.lg.bsp.common.CookieUtil;
import com.lg.bsp.common.MyDateUtil;
import com.lg.bsp.common.MyHttpUtil;
import com.lg.bsp.dao.UserMapper;
import com.lg.bsp.model.User;
import com.lg.bsp.service.UserService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class BspApplicationTests {

    @Autowired
    private UserService userService;
    @Test
    void test1() {
        User one = userService.findOne(1);
        System.out.println(one);
    }
    @Test
    void test2(){
        DateTime date = DateUtil.date();
        System.out.println("begin of week:"+MyDateUtil.getStartTime(1));
        System.out.println("==============");
        System.out.println("end of week:"+MyDateUtil.getEndTime(1));
    }
    @Test
    void test3(){
        System.out.println("count day:"+MyDateUtil.countDay());
        System.out.println("count week"+MyDateUtil.countWeek());
    }
    @Test
    void test4() throws IOException {
        InputStream in = new URL( "http://www.baidu.com" ).openStream();
        try {
            System.out.println(IOUtils.toString(in));
            if (IOUtils.toString(in) == null){
                System.out.println("end");
            }
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
    @Test
    void test5(){
        String url = "http://172.16.52.64:20173/wisdomboard///api/dormitoryBuilding/norm?source=web";
        Map params = new HashMap<String,String>();
        params.put("source","web");
        JSONObject apiJson = MyHttpUtil.getApiJson(url, params);
        System.out.println(apiJson.getString("msg"));
    }
    @Test
    void test6(){
        String url = "http://172.16.52.64:20173/wisdomboard/api/activity";
        Map params = new HashMap<String,String>();
        params.put("source","web");
        params.put("token","2031FB73-5DE4-421E-8371-87BE36C7EEB8");
        params.put("announcementId","888");
        params.put("title","title activity");
        params.put("description","one funny activity");
        params.put("content","123456");
        params.put("type","0");
        params.put("coverUrl","web");
        params.put("coverType","0");
        String string = MyHttpUtil.httpPost(url, params);
        System.out.println(string);
    }
    @Test
    void test7(){
        String url = "http://172.16.52.64:20173/wisdomboard/api/activity";
        JSONObject params = new JSONObject();
        params.put("source","web");
        params.put("token","2031FB73-5DE4-421E-8371-87BE36C7EEB8");
        params.put("announcementId","888");
        params.put("title","title activity");
        params.put("description","one funny activity");
        params.put("content","123456");
        params.put("type","0");
        params.put("coverUrl","web");
        params.put("coverType","0");
        String s = JSONObject.toJSON(params).toString();
        String string = MyHttpUtil.sendPost(url, s);
        System.out.println(string);
    }

}
