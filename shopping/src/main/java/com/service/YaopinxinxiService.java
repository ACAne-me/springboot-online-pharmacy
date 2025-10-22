package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YaopinxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YaopinxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YaopinxinxiView;


/**
 * 药品信息
 *
 * @author 
 * @email 
 * @date 2024-02-27 17:29:35
 */
public interface YaopinxinxiService extends IService<YaopinxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YaopinxinxiVO> selectListVO(Wrapper<YaopinxinxiEntity> wrapper);
   	
   	YaopinxinxiVO selectVO(@Param("ew") Wrapper<YaopinxinxiEntity> wrapper);
   	
   	List<YaopinxinxiView> selectListView(Wrapper<YaopinxinxiEntity> wrapper);
   	
   	YaopinxinxiView selectView(@Param("ew") Wrapper<YaopinxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YaopinxinxiEntity> wrapper);

   	

    List<Map<String, Object>> selectValue(Map<String, Object> params,Wrapper<YaopinxinxiEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(Map<String, Object> params,Wrapper<YaopinxinxiEntity> wrapper);

    List<Map<String, Object>> selectGroup(Map<String, Object> params,Wrapper<YaopinxinxiEntity> wrapper);

    /**
     * 扣减库存 - 需要事务支持
     */
    boolean reduceStock(Long yaopinId, Integer quantity);
    
    /**
     * 增加库存 - 需要事务支持
     * 用于退款、取消订单等场景
     */
    boolean increaseStock(Long yaopinId, Integer quantity);


}

