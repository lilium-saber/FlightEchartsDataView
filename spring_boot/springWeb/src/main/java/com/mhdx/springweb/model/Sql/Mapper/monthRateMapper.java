package com.mhdx.springweb.model.Sql.Mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface monthRateMapper {
    @Select("SELECT * FROM monthRate")
    java.util.List<com.mhdx.springweb.model.Sql.monthRate> FindAll();
}
