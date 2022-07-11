package com.mezjh.blog.play.test;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ZJH
 * @date 2022/3/22 15:11
 */
@RestController
@RequestMapping("/v1/item/")
public class RControlller {

    @PutMapping("/values/read")
    public List<AgvInfo> agvInfoLis(List<AgvInfo> agvInfos) {
        agvInfos.forEach(agv -> {

        });
        return null;
    }
}

