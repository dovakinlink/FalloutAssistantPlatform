package com.platform.system.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * ClassName：DictInfo
 * Description：字典信息
 * 
 * @author: 刘焕超
 * @date: 2015年5月25日 下午7:29:31
 *        note:
 *
 */
@Entity
@Table(name = "t_platform_dict_item")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DictItemInfo implements Serializable {

	private static final long serialVersionUID = -1084614943198153584L;

	/**
	 * ID
	 */
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(name = "c_id", length = 32)
	private String itemId;

	/**
	 * 字典大类ID
	 */
	@Column(name = "c_dict_id", length = 32)
	private String dictId;

	/**
	 * 字典名称
	 */
	@Column(name = "c_name", length = 128)
	private String name;

	/**
	 * 字典项排序
	 */
	@Column(name = "c_seq", length = 32)
	private Integer seq;

	/**
	 * 创建时间
	 */
	@Column(name = "d_create_time")
	private Date createTime;

	/**
	 * 预留字段1
	 */
	@Column(name = "c_reserve1", length = 32)
	private Integer reserve1;

	/**
	 * 预留字段2
	 */
	@Column(name = "c_reserve2", length = 128)
	private String reserve2;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getReserve1() {
		return reserve1;
	}

	public void setReserve1(Integer reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

}
