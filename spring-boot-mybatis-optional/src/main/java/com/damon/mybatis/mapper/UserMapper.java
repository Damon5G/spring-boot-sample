package com.damon.mybatis.mapper;

import com.damon.mybatis.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    Optional<User> selectById(Long id);
}
