package net.ijiangtao.tech.transaction.mybatis.mapper;

import net.ijiangtao.tech.transaction.mybatis.entity.UserEntity;

import java.util.List;

public interface UserMapper {
	
	List<UserEntity> getAll();
	
	UserEntity getOne(Long id);

	Long insert(UserEntity user);

	void update(UserEntity user);

	void delete(Long id);

}