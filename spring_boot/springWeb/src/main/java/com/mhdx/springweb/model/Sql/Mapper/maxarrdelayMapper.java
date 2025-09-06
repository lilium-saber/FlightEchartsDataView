package com.mhdx.springweb.model.Sql.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface maxarrdelayMapper {
    @Select("SELECT * FROM maxarrdelay")
    java.util.List<com.mhdx.springweb.model.Sql.maxarrdelay> FindAll();
}
