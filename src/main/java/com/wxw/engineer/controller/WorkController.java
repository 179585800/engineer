package com.wxw.engineer.controller;

import com.wxw.engineer.entity.Workers;
import com.wxw.engineer.service.WorkServiceImpl;
import com.wxw.engineer.util.RestResponse;
import com.wxw.engineer.util.RestStatus;
import com.wxw.engineer.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("workers")
public class WorkController
{

    @Autowired
    private WorkServiceImpl workService;

    @GetMapping("findAll")
    public RestResponse<List<Workers>> findAllByStatusaAndUserId(HttpServletRequest httpServletRequest)
    {
        String userId = UserUtil.getUserId();
        List<Workers> list = workService.findAllByUserId(userId);
        return new RestResponse(RestStatus.OK, list, "");
    }

    @GetMapping("findById")
    public RestResponse<Workers> findById(String id)
    {
        Workers workers = new Workers();
        if (StringUtils.isNotBlank(id))
        {
            workers = workService.findAllById(id);
        }

        return new RestResponse(RestStatus.OK, workers, "");
    }

    @GetMapping("findByName")
    public RestResponse<Workers> findByName(String name)
    {
        Workers workers = new Workers();
        if (StringUtils.isNotBlank(name))
        {
            workers = workService.findAllByName(name, UserUtil.getUserId());
        }

        return new RestResponse(RestStatus.OK, workers, "");
    }

    @PostMapping("save")
    public RestResponse<Workers> saveWorker(HttpServletRequest httpServletRequest, @RequestBody Workers workers)
    {

//        JSONObject work= JSONObject.parseObject( httpServletRequest.getParameter("workers"));
//       System.out.println(work);
        workers = workService.saveWorker(workers);
        return new RestResponse(RestStatus.OK, "", "");
    }
}
