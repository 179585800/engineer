package com.wxw.engineer.service;

import com.wxw.engineer.entity.Workers;
import com.wxw.engineer.mapper.UserMapper;
import com.wxw.engineer.mapper.WorkerMapper;
import com.wxw.engineer.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class WorkServiceImpl
{
    @Autowired
    private WorkerMapper workerMapper;
    public List<Workers> findAllByUserId(String userId)
    {

        return workerMapper.findAllByUserId(userId);
    }

    public Workers findAllById(String id)
    {
        Workers workers = workerMapper.findById(Long.valueOf(id));
        return workers;
    }

    public Workers findAllByName(String name, String userId)
    {
        List<Workers> list = workerMapper.findByNameAndUserId(name, userId);
        if (list.size() > 0)
        {
            return (Workers) list.get(0);
        }
        else
        {
            return null;
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Workers saveWorker(Workers workers)
    {
        String userId = UserUtil.getUserId();
        if (workers.getId() != null)
        {
            workerMapper.updateWorker(workers);
        }
        else
        {
            workers.setUserId(userId);
            workerMapper.save(workers);
        }
        return new Workers();
    }
}
