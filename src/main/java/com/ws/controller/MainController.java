package com.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author wangsaisoon
 * @title
 * @time 2018/3/30 0030 下午 3:25
 */
@Controller
@RequestMapping("seller")
public class MainController {

    @GetMapping("/main")
    public ModelAndView main() {
        return new ModelAndView("common/main");
    }
}
