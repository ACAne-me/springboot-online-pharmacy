package com.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.YonghuDao;
import com.entity.YonghuEntity;
import com.service.YonghuService;
import com.entity.vo.YonghuVO;
import com.entity.view.YonghuView;

@Service("yonghuService")
public class YonghuServiceImpl extends ServiceImpl<YonghuDao, YonghuEntity> implements YonghuService {
	// 为每个用户ID创建独立的锁，避免锁竞争
    private final Map<Long, Lock> balanceLocks = new ConcurrentHashMap<>();
    
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YonghuEntity> page = this.selectPage(
                new Query<YonghuEntity>(params).getPage(),
                new EntityWrapper<YonghuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YonghuEntity> wrapper) {
		  Page<YonghuView> page =new Query<YonghuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YonghuVO> selectListVO(Wrapper<YonghuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YonghuVO selectVO(Wrapper<YonghuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YonghuView> selectListView(Wrapper<YonghuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YonghuView selectView(Wrapper<YonghuEntity> wrapper) {
 		return baseMapper.selectView(wrapper);
	}

    /**
     * 扣减余额 - 需要事务支持
     */
    @Override
    @Transactional
    public boolean deductBalance(Long userId, Double amount) {
        // 获取或创建当前用户的锁
        Lock lock = balanceLocks.computeIfAbsent(userId, k -> new ReentrantLock());
        
        // 获取锁
        lock.lock();
        try {
            // 在锁的保护下进行余额检查和更新
            YonghuEntity user = this.selectById(userId);
            if (user == null) {
                throw new RuntimeException("用户不存在");
            }
            
            Double currentBalance = user.getMoney();
            if (currentBalance < amount) {
                throw new RuntimeException("余额不足");
            }
            
            // 扣减余额
            user.setMoney(currentBalance - amount);
            return this.updateById(user);
        } finally {
            // 确保在任何情况下都释放锁
            lock.unlock();
        }
    }
    
    /**
     * 增加余额 - 需要事务支持
     * 用于退款、充值等场景
     */
    @Override
    @Transactional
    public boolean addBalance(Long userId, Double amount) {
        // 获取或创建当前用户的锁
        Lock lock = balanceLocks.computeIfAbsent(userId, k -> new ReentrantLock());
        
        // 获取锁
        lock.lock();
        try {
            // 在锁的保护下进行余额更新
            YonghuEntity user = this.selectById(userId);
            if (user == null) {
                throw new RuntimeException("用户不存在");
            }
            
            // 验证金额是否有效
            if (amount == null || amount <= 0) {
                throw new RuntimeException("增加金额必须大于0");
            }
            
            // 增加余额
            Double currentBalance = user.getMoney();
            user.setMoney(currentBalance + amount);
            return this.updateById(user);
        } finally {
            // 确保在任何情况下都释放锁
            lock.unlock();
        }
    }


}
