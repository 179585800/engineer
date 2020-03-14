package com.wxw.engineer.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wxw.engineer.entity.Workers;
import com.wxw.engineer.service.WorkDetailServiceImpl;
import com.wxw.engineer.util.RestResponse;
import com.wxw.engineer.util.RestStatus;
import com.wxw.engineer.util.UserUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("workDetail")
public class WorkDetailController
{

    @Autowired
    private WorkDetailServiceImpl workDetailService;

    @GetMapping("queryByDate")
    public RestResponse<List<Map<String, Object>>> findAllByDateAndUserId(String createDate)
    {
        String userId = UserUtil.getUserId();
        List<Map<String, Object>> list = workDetailService.findByDateAndUserId(createDate, userId);
        return new RestResponse(RestStatus.OK, list, "");
    }

    @GetMapping("queryModByDate")
    public RestResponse<List<Map<String, Object>>> findModByDateAndUserIdList(String createDate)
    {
        String userId = UserUtil.getUserId();
        List<Map<String, Object>> list = workDetailService.findModByDateAndUserId(createDate, userId);
        return new RestResponse(RestStatus.OK, list, "");
    }

    @GetMapping("queryTotalWorkDay")
    public RestResponse<List<Map<String, Object>>> queryTotalWorkDay(String startDate, String endDate, String name)
    {
        String userId = UserUtil.getUserId();
        List<Map<String, Object>> list = workDetailService.queryTotalWorkDay(startDate, endDate, name, userId);
        return new RestResponse(RestStatus.OK, list, "");
    }

    @GetMapping("queryDetailWorkDay")
    public RestResponse<List<Map<String, Object>>> queryDetailWorkDay(String startDate, String endDate, String name)
    {
        String userId = UserUtil.getUserId();
        List<Map<String, Object>> list = workDetailService.queryDetailWorkDay(startDate, endDate, name, userId);
        return new RestResponse(RestStatus.OK, list, "");
    }

    @PostMapping("add")
    public RestResponse<String> add(HttpServletRequest request)
    {
//        System.out.println(persons);
        try
        {
            String prarms = IOUtils.toString(request.getReader());
            JSONObject json = JSONObject.parseObject(prarms);
            String userId = UserUtil.getUserId();
//            String userId = String.valueOf(json.get("userId"));

            List<Workers> workers = json.getJSONArray("persons").toJavaList(Workers.class);
            String days = String.valueOf(json.get("days"));
            String workDate = String.valueOf(json.get("workDate"));
            System.out.println(prarms);
            workDetailService.saveWorkDetail(workers, days, workDate, userId);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        return new RestResponse(RestStatus.OK, "", "");
    }

    @GetMapping("delete")
    public RestResponse<String> delete(String id)
    {
//        System.out.println(persons);


        workDetailService.deleteWorkDetail(id);


        return new RestResponse(RestStatus.OK, "", "");
    }
}
