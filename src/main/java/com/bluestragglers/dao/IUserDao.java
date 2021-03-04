package com.bluestragglers.dao;

import com.bluestragglers.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@CacheNamespace(blocking = true)
public interface IUserDao {

    /**
     * 查询所有用户
     * @return
     */
    @Select("SELECT * FROM user")
    @Results(id = "userMap", value = {
            @Result(id = true, column = "id", property = "userId"),
            @Result(column = "username", property = "userName"),
            @Result(column = "address", property = "userAddress"),
            @Result(column = "sex", property = "userSex"),
            @Result(column = "birthday", property = "userBirthday"),
            @Result(property = "accounts", column = "id",
                    many = @Many(select = "com.bluestragglers.dao.IAccountDao.findAccountByUid", fetchType = FetchType.LAZY)),
    })
    List<User> findAll();

    /**
     * 根据 ID 查询用户
     * @param userId
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    @ResultMap(value = {"userMap"})
    User findById(Integer userId);

    /**
     * 根据用户名称模糊查询
     * @param username
     * @return
     */
    @Select("SELECT * FROM user WHERE username like #{username}")
    @ResultMap(value = {"userMap"})
    List<User> findUserByName(String username);
}