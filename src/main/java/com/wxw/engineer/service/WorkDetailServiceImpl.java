package com.wxw.engineer.service;

import com.wxw.engineer.entity.WorkDetail;
import com.wxw.engineer.entity.Workers;
import com.wxw.engineer.mapper.SalaryMapper;
import com.wxw.engineer.mapper.WorkDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WorkDetailServiceImpl
{


    @Autowired
    private WorkDetailMapper workDetailMapper;
    @Autowired
    private SalaryMapper salaryMapper;

    public List<Map<String, Object>> findByDateAndUserId(String createDate, String userId,String workLocation)
    {
        List<Map<String, Object>> list = workDetailMapper.findByDateAndUserId(createDate, userId,workLocation);
//        list.stream().map(map -> Boolean.getBoolean((String)map.get("checked"))).collect(Collectors.toList());
        return list;
    }

    public List<Map<String, Object>> queryTotalWorkDay(String startDate, String endDate, String name, String userId)
    {
        List<Map<String, Object>> list = workDetailMapper.queryTotalWorkDay(startDate, endDate, name, userId);
//        list.stream().map(map -> Boolean.getBoolean((String)map.get("checked"))).collect(Collectors.toList());
        return list;
    }
    public List<Map<String, Object>> queryTotalSalary(String startDate, String endDate, String name, String userId)
    {
        List<Map<String, Object>> list = workDetailMapper.queryTotalWorkDay(startDate, endDate, name, userId);
        List<Map<String, Object>> listHas=  salaryMapper.queryTotalSalary(startDate,endDate,name,userId);
         for (Map map:list){
             Long workId= (Long) map.get("worker_id");
             BigDecimal salary=new BigDecimal(String.valueOf(map.get("salary")));
             BigDecimal salaryHas=new BigDecimal(0);
             boolean flag=false;
             for (Map map2:listHas){
                 if (workId.equals(map2.get("worker_id"))){
                     flag=true;
                     salaryHas=new BigDecimal( String.valueOf(map2.get("salary")));
                     break;
                 }
             }
             map.put("salaryHas",salaryHas);
             map.put("salaryMod",salary.subtract(salaryHas));
         }
        return list;
    }
    public List<Map<String, Object>> queryDetailWorkDay(String startDate, String endDate, String name, String userId)
    {
        List<Map<String, Object>> list = workDetailMapper.queryDetailWorkDay(startDate, endDate, name, userId);
//        list.stream().map(map -> Boolean.getBoolean((String)map.get("checked"))).collect(Collectors.toList());
        return list;
    }

    public List<Map<String, Object>> findModByDateAndUserId(String createDate, String userId,String workLocation)
    {
        List<Map<String, Object>> list = workDetailMapper.findModByDateAndUserId(createDate, userId, workLocation);

//        list.stream().map(map -> Boolean.getBoolean((String)map.get("checked"))).collect(Collectors.toList());
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Workers saveWorkDetail(List<Workers> workers, String days, String workDate, String userId,String workLocation)
    {
        List<WorkDetail> list = new ArrayList<>();
        for (int i = 0; i < workers.size(); i++)
        {
            Workers worker = workers.get(i);
            BigDecimal salary = worker.getSalary();
            long workerId = worker.getId();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");//
            WorkDetail workDetail = new WorkDetail();
            workDetail.setDays(new BigDecimal(days));
            workDetail.setWorkerId(workerId);
            workDetail.setSalary(salary);
            workDetail.setUserId(userId);
            workDetail.setStatus("1");
            workDetail.setWorkLocation(workLocation);

            workDetail.setWorkDate(workDate);

            list.add(workDetail);
        }

        workDetailMapper.batchInsert(list);

        return new Workers();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWorkDetail(String id)
    {
        workDetailMapper.deleteWorker(Long.valueOf(id));


    }
}
