package cn.infogiga.ibatis.dao;

import java.util.List;

import javax.annotation.Resource;

import cn.infogiga.ibatis.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;  
import org.springframework.stereotype.Component;
@Component
public class IbatisDao{
	@Autowired
	SqlMapClientTemplate sqlMapClientTemplate;
	
	public User findUserByIdWithCascade(Integer userID){
		return (User)sqlMapClientTemplate.queryForObject("findUserByIdWithCascade",userID);  
	}
	
	public User findUserById(Integer userID){
		return (User)sqlMapClientTemplate.queryForObject("findUserById",userID);  
	}
	
	public User findUserByExample(User user){
		return (User)sqlMapClientTemplate.queryForObject("findUserByExample", user);
	}
	
	public List<User> findUserByExample2(User user){
		return (List<User>)sqlMapClientTemplate.queryForList("findUserByExample2", user);
	}

	public SqlMapClientTemplate getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
}
