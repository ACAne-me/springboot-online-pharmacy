package com.entity;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

/**
* 类说明 : 
*/
@TableName("config")
public class ConfigEntity implements Serializable{
private static final long serialVersionUID = 1L;
	
	@TableId(type = IdType.AUTO)
	private Long id;
	
	/**
	 * key
	 */
	@NotBlank(message = "配置名称不能为空")
	private String name;
	
	/**
	 * value
	 */
	@NotBlank(message = "配置值不能为空")
	private String value;

    /**
     * url
     */
    private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
	
}
