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

import com.entity.YaopinfenleiEntity;
import com.entity.view.YaopinfenleiView;

import com.service.YaopinfenleiService;
import com.service.TokenService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 药品分类
 * 后端接口
 * @author 
 * @email 
 * @date 2024-02-27 17:29:35
 */
@RestController
@RequestMapping("/yaopinfenlei")
public class YaopinfenleiController {
    @Autowired
    private YaopinfenleiService yaopinfenleiService;




    



    /**
     * 后端列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YaopinfenleiEntity yaopinfenlei,
		HttpServletRequest request){
        EntityWrapper<YaopinfenleiEntity> ew = new EntityWrapper<YaopinfenleiEntity>();

		PageUtils page = yaopinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopinfenlei), params), params));

        return R.ok().put("data", page);
    }
    
    /**
     * 前端列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,YaopinfenleiEntity yaopinfenlei, 
		HttpServletRequest request){
        EntityWrapper<YaopinfenleiEntity> ew = new EntityWrapper<YaopinfenleiEntity>();

		PageUtils page = yaopinfenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yaopinfenlei), params), params));
        return R.ok().put("data", page);
    }



	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YaopinfenleiEntity yaopinfenlei){
       	EntityWrapper<YaopinfenleiEntity> ew = new EntityWrapper<YaopinfenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yaopinfenlei, "yaopinfenlei")); 
        return R.ok().put("data", yaopinfenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YaopinfenleiEntity yaopinfenlei){
        EntityWrapper< YaopinfenleiEntity> ew = new EntityWrapper< YaopinfenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yaopinfenlei, "yaopinfenlei")); 
		YaopinfenleiView yaopinfenleiView =  yaopinfenleiService.selectView(ew);
		return R.ok("查询药品分类成功").put("data", yaopinfenleiView);
    }
	
    /**
     * 后端详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YaopinfenleiEntity yaopinfenlei = yaopinfenleiService.selectById(id);
        return R.ok().put("data", yaopinfenlei);
    }

    /**
     * 前端详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YaopinfenleiEntity yaopinfenlei = yaopinfenleiService.selectById(id);
        return R.ok().put("data", yaopinfenlei);
    }
    



    /**
     * 后端保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YaopinfenleiEntity yaopinfenlei, HttpServletRequest request){
        if(yaopinfenleiService.selectCount(new EntityWrapper<YaopinfenleiEntity>().eq("yaopinfenlei", yaopinfenlei.getYaopinfenlei()))>0) {
            return R.error("药品分类已存在");
        }
    	//ValidatorUtils.validateEntity(yaopinfenlei);
        yaopinfenleiService.insert(yaopinfenlei);
        return R.ok();
    }
    
    /**
     * 前端保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YaopinfenleiEntity yaopinfenlei, HttpServletRequest request){
        if(yaopinfenleiService.selectCount(new EntityWrapper<YaopinfenleiEntity>().eq("yaopinfenlei", yaopinfenlei.getYaopinfenlei()))>0) {
            return R.error("药品分类已存在");
        }
    	//ValidatorUtils.validateEntity(yaopinfenlei);
        yaopinfenleiService.insert(yaopinfenlei);
        return R.ok();
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YaopinfenleiEntity yaopinfenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yaopinfenlei);
        if(yaopinfenleiService.selectCount(new EntityWrapper<YaopinfenleiEntity>().ne("id", yaopinfenlei.getId()).eq("yaopinfenlei", yaopinfenlei.getYaopinfenlei()))>0) {
            return R.error("药品分类已存在");
        }
        yaopinfenleiService.updateById(yaopinfenlei);//全部更新
        return R.ok();
    }



    

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yaopinfenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }
    
	










}
