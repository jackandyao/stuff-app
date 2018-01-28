package com.qbao.aisr.app.web.controller.demo;

import com.qbao.aisr.app.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shuaizhihu on 2017/2/22.
 */
@Controller
@RequestMapping("/demo")
public class DemoController extends BaseController {

    @RequestMapping("/search")
    @ResponseBody
    public Map<String,Object> search(@RequestParam(value = "taskId",required = true) long taskId) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("taskid",taskId);
        return map;
    }
}
