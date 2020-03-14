package com.wxw.engineer.service;

import com.wxw.engineer.dao.WorkDetailRepository;
import com.wxw.engineer.entity.WorkDetail;
import com.wxw.engineer.entity.Workers;
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
    private WorkDetailRepository workDetailRepository;

    public List<Map<String, Object>> findByDateAndUserId(String createDate, String userId)
    {
        List<Map<String, Object>> list = workDetailRepository.findByDateAndUserId(createDate, userId);
//        list.stream().map(map -> Boolean.getBoolean((String)map.get("checked"))).collect(Collectors.toList());
        return list;
    }

    public List<Map<String, Object>> queryTotalWorkDay(String startDate, String endDate, String name, String userId)
    {
        List<Map<String, Object>> list = workDetailRepository.queryTotalWorkDay(startDate, endDate, name, userId);
//        list.stream().map(map -> Boolean.getBoolean((String)map.get("checked"))).collect(Collectors.toList());
        return list;
    }

    public List<Map<String, Object>> queryDetailWorkDay(String startDate, String endDate, String name, String userId)
    {
        List<Map<String, Object>> list = workDetailRepository.queryDetailWorkDay(startDate, endDate, name, userId);
//        list.stream().map(map -> Boolean.getBoolean((String)map.get("checked"))).collect(Collectors.toList());
        return list;
    }

    public List<Map<String, Object>> findModByDateAndUserId(String createDate, String userId)
    {
        List<Map<String, Object>> list = workDetailRepository.findModByDateAndUserId(createDate, userId);

//        list.stream().map(map -> Boolean.getBoolean((String)map.get("checked"))).collect(Collectors.toList());
        return list;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Workers saveWorkDetail(List<Workers> workers, String days, String workDate, String userId)
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
            try
            {
                workDetail.setWorkDate(sf.parse(workDate));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            list.add(workDetail);
        }

        workDetailRepository.saveAll(list);

        return new Workers();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteWorkDetail(String id)
    {
        workDetailRepository.deleteWorker(Long.valueOf(id));


    }
}
