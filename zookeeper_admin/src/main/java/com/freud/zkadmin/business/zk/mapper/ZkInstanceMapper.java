/**
 * Description:[For Wolfly] <br>
 * Date: 2017年6月8日 <br>
 * Copyright (c) 2017 Wolfly <br>
 * 
 * @author Freud
 * @version V1.0
 */

package com.freud.zkadmin.business.zk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

import com.freud.zkadmin.business.zk.bean.ZkInstanceBean;

/**
 * @Description: <br>
 * @date 2017年6月8日 上午9:58:11 <br>
 * 
 * @author Freud
 */
public interface ZkInstanceMapper {

	@Select("SELECT ID AS id, NAME AS name, IP AS ip, PORT AS port, `USE` AS `use`, OPERATE_DATE AS operateDate FROM ZK_INSTANCE")
	List<ZkInstanceBean> getAll(RowBounds rb);

	@Select("SELECT ID AS id, NAME AS name, IP AS ip, PORT AS port, `USE` AS `use`, OPERATE_DATE AS operateDate FROM ZK_INSTANCE WHERE ID = #{id}")
	ZkInstanceBean get(@Param("id") int id);

	@Insert("INSERT INTO ZK_INSTANCE(NAME, IP, PORT, `USE`, OPERATE_DATE) VALUES(#{name}, #{ip}, #{port}, #{use}, #{operateDate})")
	void insert(ZkInstanceBean bean);

	@Update("UPDATE ZK_INSTANCE SET NAME=#{name}, IP=#{ip}, PORT=#{port}, `USE`=#{use}, OPERATE_DATE=#{operateDate} WHERE ID = #{id}")
	void update(ZkInstanceBean bean);

	@Delete("DELETE FROM ZK_INSTANCE")
	void deleteAll();

	@Delete("DELETE FROM ZK_INSTANCE WHERE ID = #{id}")
	void delete(@Param("id") int id);

}
