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


import com.dao.YaopinxinxiDao;
import com.entity.YaopinxinxiEntity;
import com.service.YaopinxinxiService;
import com.entity.vo.YaopinxinxiVO;
import com.entity.view.YaopinxinxiView;

@Service("yaopinxinxiService")
public class YaopinxinxiServiceImpl extends ServiceImpl<YaopinxinxiDao, YaopinxinxiEntity> implements YaopinxinxiService {
	// 为每个药品ID创建独立的锁，避免锁竞争
    private final Map<Long, Lock> stockLocks = new ConcurrentHashMap<>();
    
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YaopinxinxiEntity> page = this.selectPage(
                new Query<YaopinxinxiEntity>(params).getPage(),
                new EntityWrapper<YaopinxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YaopinxinxiEntity> wrapper) {
		  Page<YaopinxinxiView> page =new Query<YaopinxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YaopinxinxiVO> selectListVO(Wrapper<YaopinxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YaopinxinxiVO selectVO(Wrapper<YaopinxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YaopinxinxiView> selectListView(Wrapper<YaopinxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YaopinxinxiView selectView(Wrapper<YaopinxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}

    @Override
    public List<Map<String, Object>> selectValue(Map<String, Object> params, Wrapper<YaopinxinxiEntity> wrapper) {
        return baseMapper.selectValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params, Wrapper<YaopinxinxiEntity> wrapper) {
        return baseMapper.selectTimeStatValue(params, wrapper);
    }

    @Override
    public List<Map<String, Object>> selectGroup(Map<String, Object> params, Wrapper<YaopinxinxiEntity> wrapper) {
        return baseMapper.selectGroup(params, wrapper);
    }

    /**
     * 扣减库存 - 需要事务支持
     */
    @Override
    @Transactional
    public boolean reduceStock(Long yaopinId, Integer quantity) {
        // 获取或创建当前药品的锁
        Lock lock = stockLocks.computeIfAbsent(yaopinId, k -> new ReentrantLock());
        
        // 获取锁
        lock.lock();
        try {
            // 在锁的保护下进行库存检查和更新
            YaopinxinxiEntity yaopin = this.selectById(yaopinId);
            if (yaopin == null) {
                throw new RuntimeException("药品不存在");
            }
            
            Integer currentStock = yaopin.getAlllimittimes();
            if (currentStock < quantity) {
                throw new RuntimeException("库存不足");
            }
            
            // 扣减库存
            yaopin.setAlllimittimes(currentStock - quantity);
            return this.updateById(yaopin);
        } finally {
            // 确保在任何情况下都释放锁
            lock.unlock();
        }
    }

    /**
     * 增加库存 - 需要事务支持
     * 用于退款、取消订单等场景
     */
    @Override
    @Transactional
    public boolean increaseStock(Long yaopinId, Integer quantity) {
        // 获取或创建当前药品的锁
        Lock lock = stockLocks.computeIfAbsent(yaopinId, k -> new ReentrantLock());
        
        // 获取锁
        lock.lock();
        try {
            // 在锁的保护下进行库存更新
            YaopinxinxiEntity yaopin = this.selectById(yaopinId);
            if (yaopin == null) {
                throw new RuntimeException("药品不存在");
            }
            
            // 增加库存
            Integer currentStock = yaopin.getAlllimittimes();
            yaopin.setAlllimittimes(currentStock + quantity);
            return this.updateById(yaopin);
        } finally {
            // 确保在任何情况下都释放锁
            lock.unlock();
        }
    }



}
