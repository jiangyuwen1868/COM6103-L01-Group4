package com.ruoyi.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ruoyi.common.core.controller.BaseController;

/**
 * 首页控制器
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/")
public class HomeController extends BaseController
{
    /**
     * 首页
     */
    @GetMapping("/home")
    public String home()
    {
        return "home";
    }
}
