package com.mhdx.springweb.model.Sql.Mapper;

import com.mhdx.springweb.model.Sql.maxdepdelay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaxdepdelayMapper {
    @Select("SELECT * FROM maxdepdelay")
    List<maxdepdelay> FindAll();
}
