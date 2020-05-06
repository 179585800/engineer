package com.wxw.engineer.service;

import com.wxw.engineer.entity.WorkLocation;
import com.wxw.engineer.entity.Workers;
import com.wxw.engineer.mapper.WorkerLocationMapper;
import com.wxw.engineer.mapper.WorkerMapper;
import com.wxw.engineer.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WorkLocationServiceImpl
{

    @Autowired
    private WorkerLocationMapper workerLocationMapper;
    public List<WorkLocation> findAllByUserId(String userId)
    {

        return workerLocationMapper.findAllByUserId(userId);
    }
    public WorkLocation findById(String id)
    {

        return workerLocationMapper.findById(id);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveWorker(WorkLocation workLocation)
    {
        String userId = UserUtil.getUserId();
        if (workLocation.getId() != null)
        {
            workerLocationMapper.updateWorkLocation(workLocation);
        }
        else
        {
           List<WorkLocation> list= workerLocationMapper.findByName(workLocation.getName());
           if(list.size()>0){
               throw  new RuntimeException("此工地已经存在！");
        }
            workLocation.setUserId(userId);
            workerLocationMapper.save(workLocation);
        }

    }
}
