package com.seeyoo.visit.controller;

import com.seeyoo.visit.model.Terminal;
import com.seeyoo.visit.service.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Example;

@RestController
public class TerminalTestController {
    @Autowired
    private TerminalService terminalService;

    @GetMapping(value = "/terminals")
    public Object terminals(String mac){
        Example example = new Example(Terminal.class);
        long a = terminalService.tgroupIdByMac("3693B46CD4EC");
      /*  Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("id",tid);*/
        return a;
    }
}
