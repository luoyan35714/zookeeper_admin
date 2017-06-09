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

import com.freud.zkadmin.business.zk.bean.ZkAuth;

/**
 * @Description: <br>
 * @date 2017年6月8日 上午9:58:11 <br>
 * 
 * @author Freud
 */
public interface ZkAuthMapper {

	@Select("SELECT ID AS id, INSTANCE_ID AS instanceId, AUTH AS auth, PASS AS pass FROM ZK_AUTH WHERE INSTANCE_ID = #{instanceId}")
	List<ZkAuth> getAll(@Param("instanceId") int instanceId);

	@Select("SELECT ID AS id, INSTANCE_ID AS instanceId, AUTH AS auth, PASS AS pass FROM ZK_AUTH WHERE ID = #{id}")
	ZkAuth get(@Param("id") int id);

	@Insert("INSERT INTO ZK_AUTH(INSTANCE_ID,AUTH,PASS) VALUES(#{instanceId},#{auth},#{pass})")
	void insert(ZkAuth bean);

	@Update("UPDATE ZK_AUTH SET INSTANCE_ID=#{instanceId}, AUTH=#{auth}, PASS=#{pass} WHERE ID = #{id}")
	void update(ZkAuth bean);

	@Delete("DELETE FROM ZK_AUTH WHERE INSTANCE_ID = #{instanceId}")
	void deleteByInstanceId(@Param("instanceId") int instanceId);

	@Delete("DELETE FROM ZK_AUTH WHERE ID = #{id}")
	void delete(@Param("id") int id);

	@Delete("DELETE FROM ZK_AUTH")
	void deleteAll();

}
