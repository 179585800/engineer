package com.wxw.engineer.controller;

import com.wxw.engineer.entity.Salary;
import com.wxw.engineer.service.SalaryServiceImpl;
import com.wxw.engineer.util.RestResponse;
import com.wxw.engineer.util.RestStatus;
import com.wxw.engineer.util.UserUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/salaryController")
public class SalaryController {

    @Autowired
    private SalaryServiceImpl salaryService;

    @PostMapping("/saveProvideSalary")
    public RestResponse<String> saveProvideSalary(HttpServletRequest request, @RequestBody Salary salary) throws Exception {

        salaryService.saveProvideSalary(salary);
        return new RestResponse(RestStatus.OK, null, "");
    }
    @GetMapping("queryDetail")
    public RestResponse<List<Map<String, Object>>> queryDetail(String startDate, String endDate, String name)
    {
        String userId = UserUtil.getUserId();
        List<Map<String, Object>> list = salaryService.queryDetail(startDate, endDate, name, userId);
        return new RestResponse(RestStatus.OK, list, "");
    }
}
