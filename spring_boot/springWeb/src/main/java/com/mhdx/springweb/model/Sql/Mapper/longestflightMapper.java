package com.mhdx.springweb.model.Sql.Mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface longestflightMapper {
    @org.apache.ibatis.annotations.Select("SELECT * FROM longestflight")
    java.util.List<com.mhdx.springweb.model.Sql.longestflight> FindAll();
}
