package com.simshine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.simshine.mode.UserModel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @ClassName UserMapper
 * @Description TODO
 * @Author hh
 * @Date 2019/9/16 0016 11:25
 * @Version 1.0
 **/
@Mapper
public interface UserMapper extends BaseMapper<UserModel> {

}
