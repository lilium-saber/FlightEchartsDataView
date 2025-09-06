package com.mhdx.springweb.model.Sql.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface maxdelayMapper {
    @Select("SELECT * FROM maxdelay")
    java.util.List<com.mhdx.springweb.model.Sql.maxdelay> FindAll();
}
