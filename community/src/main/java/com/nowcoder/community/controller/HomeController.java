package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private UserService userService;

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public String getIndexPage(Model model , Page page){
        //方法调用之前，SpringMVC会自动实例化Model和Page，并将Page注入Model
        //所以，在thymeleaf中可以直接访问Page对象中的数据

        page.setRows(discussPostService.findDiscussPostRows(0));  //设置总数据行数
        page.setPath("/index"); // 设置访问路径
        //从服务器查出从offset开始的limit条数据发送给前端显示
        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if(list != null){
            for (DiscussPost discussPost : list) {
                Map<String , Object> mp = new HashMap<>();
                //每一条帖子 和 对应的作者(用户) 放进discussPosts
                mp.put("post" , discussPost);
                User user = userService.findUserById(discussPost.getUserId());
                mp.put("user",user);
                discussPosts.add(mp);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        // model.addAttribute("page",page); 可以省略
        return "/index";
    }
}
