package com.wxw.engineer.controller;

import com.wxw.engineer.config.service.User;
import com.wxw.engineer.entity.WorkLocation;
import com.wxw.engineer.entity.Workers;
import com.wxw.engineer.mapper.WorkerLocationMapper;
import com.wxw.engineer.service.UserServiceImpl;
import com.wxw.engineer.service.WorkLocationServiceImpl;
import com.wxw.engineer.util.RestResponse;
import com.wxw.engineer.util.RestStatus;
import com.wxw.engineer.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workLocationController")
public class WorkLocationController
{

    @Autowired
    private WorkLocationServiceImpl workLocationService;



    @GetMapping("/getAll")
    public RestResponse<List<WorkLocation>> getAll(HttpServletRequest httpServletRequest)
    {
        String userId = UserUtil.getUserId();
        List<WorkLocation> list = workLocationService.findAllByUserId(userId);

        return new RestResponse(RestStatus.OK, list, "");
    }
    @GetMapping("/getById")
    public RestResponse<WorkLocation> getById(String id)
    {
//        String userId = UserUtil.getUserId();
        WorkLocation workLocation = workLocationService.findById(id);

        return new RestResponse(RestStatus.OK, workLocation, "");
    }
    @PostMapping("/save")
    public RestResponse<String> saveWorker(HttpServletRequest httpServletRequest, @RequestBody WorkLocation workLocation)
    {

//        JSONObject work= JSONObject.parseObject( httpServletRequest.getParameter("workers"));
//       System.out.println(work);
        workLocationService.saveWorker(workLocation);
        return new RestResponse(RestStatus.OK, "", "");
    }
//    @PostMapping("/reg")
//
//    public RestResponse<String> reg(@RequestBody Map<String, String> user) throws Exception
//    {
//        userService.reg(user.get("code"), user.get("account"), user.get("password"), user.get("uesername"));
//        return new RestResponse(RestStatus.OK, null, "");
//    }
}
