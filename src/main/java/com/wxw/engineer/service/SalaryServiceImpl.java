package com.wxw.engineer.service;

import com.wxw.engineer.entity.Salary;
import com.wxw.engineer.mapper.SalaryMapper;
import com.wxw.engineer.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Service
public class SalaryServiceImpl {

    @Autowired
    private SalaryMapper salaryMapper;
    public void saveProvideSalary(Salary salary) throws Exception {


            Integer userID = Integer.parseInt(UserUtil.getUserId());
            salary.setUserId(userID);
            salaryMapper.saveSalary(salary);



    }
    public List<Map<String, Object>> queryDetail(String startDate, String endDate, String name, String userId)
    {
        List<Map<String, Object>> list = salaryMapper.queryDetail(startDate, endDate, name, userId);
//        list.stream().map(map -> Boolean.getBoolean((String)map.get("checked"))).collect(Collectors.toList());
        return list;
    }
}
