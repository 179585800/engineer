package com.wxw.engineer.service;

import com.wxw.engineer.dao.WorkRepository;
import com.wxw.engineer.entity.Workers;
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
    private WorkRepository workRepository;

    public List<Workers> findAllByUserId(String userId)
    {

        return workRepository.findAllByUserIdOrderByStatusDesc(userId);
    }

    public Workers findAllById(String id)
    {
        Optional optional = workRepository.findById(Long.valueOf(id));
        if (optional.isPresent())
        {
            return (Workers) optional.get();
        }
        else
        {
            return null;
        }
    }

    public Workers findAllByName(String name, String userId)
    {
        List<Workers> list = workRepository.findByNameAndUserId(name, userId);
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
            workRepository.updateWorker(workers.getId(), workers.getName(), workers.getCategory(), workers.getSalary(), workers.getStatus());
        }
        else
        {
            workers.setUserId(userId);
            workRepository.save(workers);
        }
        return new Workers();
    }
}
