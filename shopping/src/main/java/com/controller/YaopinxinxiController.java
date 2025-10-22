package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import com.utils.ValidatorUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.entity.OrdersEntity;
import com.service.OrdersService;

import com.entity.YaopinxinxiEntity;
import com.entity.view.YaopinxinxiView;

import com.service.YaopinxinxiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

// Redis缓存相关导入（注释掉，仅作为示例）
// import org.springframework.cache.annotation.Cacheable;
// import org.springframework.cache.annotation.CacheEvict;
// import org.springframework.cache.annotation.CachePut;
// import org.springframework.data.redis.core.RedisTemplate;

/**
 * 药品信息
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-27 17:29:35
 */
@RestController
@RequestMapping("/yaopinxinxi")
public class YaopinxinxiController {
    @Autowired
    private YaopinxinxiService yaopinxinxiService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;
    
    // Redis缓存模板（注释掉，仅作为示例）
    // @Autowired
    // private RedisTemplate<String, Object> redisTemplate;


    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YaopinxinxiEntity yaopinxinxi,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
                if(pricestart!=null) ew.ge("price", pricestart);
                if(priceend!=null) ew.le("price", priceend);

		PageUtils page = yaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopinxinxi), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YaopinxinxiEntity yaopinxinxi, 
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
                if(pricestart!=null) ew.ge("price", pricestart);
                if(priceend!=null) ew.le("price", priceend);

		PageUtils page = yaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopinxinxi), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YaopinxinxiEntity yaopinxinxi){
       	EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yaopinxinxi, "yaopinxinxi")); 
        return R.ok().put("data", yaopinxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YaopinxinxiEntity yaopinxinxi){
        EntityWrapper< YaopinxinxiEntity> ew = new EntityWrapper< YaopinxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yaopinxinxi, "yaopinxinxi")); 
		YaopinxinxiView yaopinxinxiView =  yaopinxinxiService.selectView(ew);
		return R.ok("查询药品信息成功").put("data", yaopinxinxiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    // @Cacheable(value = "yaopinxinxi", key = "#id") // 缓存注解示例（注释掉）
    public R info(@PathVariable("id") Long id){
        // 缓存查询示例（注释掉）
        // String cacheKey = "yaopinxinxi:" + id;
        // YaopinxinxiEntity cachedYaopinxinxi = (YaopinxinxiEntity) redisTemplate.opsForValue().get(cacheKey);
        // if (cachedYaopinxinxi != null) {
        //     return R.ok().put("data", cachedYaopinxinxi);
        // }
        
        YaopinxinxiEntity yaopinxinxi = yaopinxinxiService.selectById(id);
	yaopinxinxi.setClicktime(new Date());
	yaopinxinxiService.updateById(yaopinxinxi);
        yaopinxinxi = yaopinxinxiService.selectView(new EntityWrapper<YaopinxinxiEntity>().eq("id", id));
        
        // 缓存设置示例（注释掉）
        // redisTemplate.opsForValue().set(cacheKey, yaopinxinxi, Duration.ofHours(2));
        
        return R.ok().put("data", yaopinxinxi);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YaopinxinxiEntity yaopinxinxi = yaopinxinxiService.selectById(id);
		yaopinxinxi.setClicktime(new Date());
		yaopinxinxiService.updateById(yaopinxinxi);
        yaopinxinxi = yaopinxinxiService.selectView(new EntityWrapper<YaopinxinxiEntity>().eq("id", id));
        return R.ok().put("data", yaopinxinxi);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YaopinxinxiEntity yaopinxinxi, HttpServletRequest request){
        if(yaopinxinxiService.selectCount(new EntityWrapper<YaopinxinxiEntity>().eq("yaopinmingcheng", yaopinxinxi.getYaopinmingcheng()))>0) {
            return R.error("药品名称已存在");
        }
    	//ValidatorUtils.validateEntity(yaopinxinxi);
        yaopinxinxiService.insert(yaopinxinxi);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YaopinxinxiEntity yaopinxinxi, HttpServletRequest request){
        if(yaopinxinxiService.selectCount(new EntityWrapper<YaopinxinxiEntity>().eq("yaopinmingcheng", yaopinxinxi.getYaopinmingcheng()))>0) {
            return R.error("药品名称已存在");
        }
    	//ValidatorUtils.validateEntity(yaopinxinxi);
        yaopinxinxiService.insert(yaopinxinxi);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    // @CacheEvict(value = "yaopinxinxi", key = "#yaopinxinxi.id") // 缓存清除注解示例（注释掉）
    public R update(@RequestBody YaopinxinxiEntity yaopinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yaopinxinxi);
        if(yaopinxinxiService.selectCount(new EntityWrapper<YaopinxinxiEntity>().ne("id", yaopinxinxi.getId()).eq("yaopinmingcheng", yaopinxinxi.getYaopinmingcheng()))>0) {
            return R.error("药品名称已存在");
        }
        yaopinxinxiService.updateById(yaopinxinxi);//全部更新
        
        // 缓存清除示例（注释掉）
        // String cacheKey = "yaopinxinxi:" + yaopinxinxi.getId();
        // redisTemplate.delete(cacheKey);
        
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yaopinxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
    /**
     * 提醒接口
     */
	@RequestMapping("/remind/{columnName}/{type}")
	public R remindCount(@PathVariable("columnName") String columnName, HttpServletRequest request, 
						 @PathVariable("type") String type,@RequestParam Map<String, Object> map) {
		map.put("column", columnName);
		map.put("type", type);
		
		if(type.equals("2")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar c = Calendar.getInstance();
			Date remindStartDate = null;
			Date remindEndDate = null;
			if(map.get("remindstart")!=null) {
				Integer remindStart = Integer.parseInt(map.get("remindstart").toString());
				c.setTime(new Date()); 
				c.add(Calendar.DAY_OF_MONTH,remindStart);
				remindStartDate = c.getTime();
				map.put("remindstart", sdf.format(remindStartDate));
			}
			if(map.get("remindend")!=null) {
				Integer remindEnd = Integer.parseInt(map.get("remindend").toString());
				c.setTime(new Date());
				c.add(Calendar.DAY_OF_MONTH,remindEnd);
				remindEndDate = c.getTime();
				map.put("remindend", sdf.format(remindEndDate));
			}
		}
		
		Wrapper<YaopinxinxiEntity> wrapper = new EntityWrapper<YaopinxinxiEntity>();
		if(map.get("remindstart")!=null) {
			wrapper.ge(columnName, map.get("remindstart"));
		}
		if(map.get("remindend")!=null) {
			wrapper.le(columnName, map.get("remindend"));
		}


		int count = yaopinxinxiService.selectCount(wrapper);
		return R.ok().put("count", count);
	}
	
	/**
     * 前端智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,YaopinxinxiEntity yaopinxinxi, HttpServletRequest request,String pre){
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");
		PageUtils page = yaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopinxinxi), params), params));
        return R.ok().put("data", page);
    }


        /**
     * 协同算法（按用户购买推荐）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,YaopinxinxiEntity yaopinxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String goodtypeColumn = "yaopinfenlei";
        List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>().eq("userid", userId).eq("tablename", "yaopinxinxi").orderBy("addtime", false));
        List<String> goodtypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<YaopinxinxiEntity> yaopinxinxiList = new ArrayList<YaopinxinxiEntity>();
	//去重
    	List<OrdersEntity> ordersDist = new ArrayList<OrdersEntity>();
    	for(OrdersEntity o1 : orders) {
    		boolean addFlag = true;
    		for(OrdersEntity o2 : ordersDist) {
    			if(o1.getGoodid()==o2.getGoodid() || o1.getGoodtype().equals(o2.getGoodtype())) {
    				addFlag = false;
    				break;
    			}
    		}
    		if(addFlag) ordersDist.add(o1);
    	}
        if(ordersDist!=null && ordersDist.size()>0) {
                for(OrdersEntity o : ordersDist) {
                        yaopinxinxiList.addAll(yaopinxinxiService.selectList(new EntityWrapper<YaopinxinxiEntity>().eq(goodtypeColumn, o.getGoodtype())));
                }
        }
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        PageUtils page = yaopinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopinxinxi), params), params));
        List<YaopinxinxiEntity> pageList = (List<YaopinxinxiEntity>)page.getList();
        if(yaopinxinxiList.size()<limit) {
                int toAddNum = (limit-yaopinxinxiList.size())<=pageList.size()?(limit-yaopinxinxiList.size()):pageList.size();
                for(YaopinxinxiEntity o1 : pageList) {
                    boolean addFlag = true;
                    for(YaopinxinxiEntity o2 : yaopinxinxiList) {
                        if(o1.getId().intValue()==o2.getId().intValue()) {
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag) {
                        yaopinxinxiList.add(o1);
                        if(--toAddNum==0) break;
                    }   
                }
        } else if(yaopinxinxiList.size()>limit) {
            yaopinxinxiList = yaopinxinxiList.subList(0, limit);
        }
        page.setList(yaopinxinxiList);
        return R.ok().put("data", page);
    }




    /**
     * （按值统计）
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}")
    public R value(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
        List<Map<String, Object>> result = yaopinxinxiService.selectValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计(多)）
     */
    @RequestMapping("/valueMul/{xColumnName}")
    public R valueMul(@PathVariable("xColumnName") String xColumnName,@RequestParam String yColumnNameMul, HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = yaopinxinxiService.selectValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * （按值统计）时间统计类型
     */
    @RequestMapping("/value/{xColumnName}/{yColumnName}/{timeStatType}")
    public R valueDay(@PathVariable("yColumnName") String yColumnName, @PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("yColumn", yColumnName);
        params.put("timeStatType", timeStatType);
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
        List<Map<String, Object>> result = yaopinxinxiService.selectTimeStatValue(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }

    /**
     * （按值统计）时间统计类型(多)
     */
    @RequestMapping("/valueMul/{xColumnName}/{timeStatType}")
    public R valueMulDay(@PathVariable("xColumnName") String xColumnName, @PathVariable("timeStatType") String timeStatType,@RequestParam String yColumnNameMul,HttpServletRequest request) {
        String[] yColumnNames = yColumnNameMul.split(",");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("xColumn", xColumnName);
        params.put("timeStatType", timeStatType);
        List<List<Map<String, Object>>> result2 = new ArrayList<List<Map<String,Object>>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
        for(int i=0;i<yColumnNames.length;i++) {
            params.put("yColumn", yColumnNames[i]);
            List<Map<String, Object>> result = yaopinxinxiService.selectTimeStatValue(params, ew);
            for(Map<String, Object> m : result) {
                for(String k : m.keySet()) {
                    if(m.get(k) instanceof Date) {
                        m.put(k, sdf.format((Date)m.get(k)));
                    }
                }
            }
            result2.add(result);
        }
        return R.ok().put("data", result2);
    }

    /**
     * 分组统计
     */
    @RequestMapping("/group/{columnName}")
    public R group(@PathVariable("columnName") String columnName,HttpServletRequest request) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("column", columnName);
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
        List<Map<String, Object>> result = yaopinxinxiService.selectGroup(params, ew);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for(Map<String, Object> m : result) {
            for(String k : m.keySet()) {
                if(m.get(k) instanceof Date) {
                    m.put(k, sdf.format((Date)m.get(k)));
                }
            }
        }
        return R.ok().put("data", result);
    }




    /**
     * 总数量
     */
    @RequestMapping("/count")
    public R count(@RequestParam Map<String, Object> params,YaopinxinxiEntity yaopinxinxi, HttpServletRequest request){
        EntityWrapper<YaopinxinxiEntity> ew = new EntityWrapper<YaopinxinxiEntity>();
        int count = yaopinxinxiService.selectCount(MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopinxinxi), params), params));
        return R.ok().put("data", count);
    }



}
