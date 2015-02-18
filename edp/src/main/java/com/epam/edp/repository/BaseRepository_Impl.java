package com.epam.edp.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.epam.edp.domain.BaseEntity;
import com.epam.edp.domain.Id;
import com.epam.edp.domain.NumericId;
import com.epam.edp.domain.StringId;


public abstract class BaseRepository_Impl<T extends BaseEntity<?>> implements BaseRepository<T> {
  
  private final String objectType;
  
  private final JdbcTemplate jdbcTemplate;
  private final RowMapper<T> rowMapper;
  
  protected BaseRepository_Impl(DataSource dataSource, String objectType) {
    Assert.notNull(dataSource, "The 'dataSource' argument must not be null.");
    Assert.notNull(objectType, "The 'objectType' argument must not be null.");
    this.objectType = objectType;
    this.jdbcTemplate = new JdbcTemplate(dataSource);
    this.rowMapper = getRowMapper();
  }
  
  public abstract RowMapper<T> getRowMapper();
  
  @Override
  public String getType() {
    return this.objectType;
  }
  
  @Override
  public T getById(Id id) {
    if (null == id) {
      return null;
    }
    StringBuilder sql = new StringBuilder(getDefaultObjectFindSql());
    sql.append(getDefaultWhereClauseSql());
    return jdbcTemplate.queryForObject(sql.toString(), rowMapper, (id.isNumeric()
        ? Long.valueOf(id.getNumericValue())
        : id.getValue()));
  }
  
  @Override
  public T getById(String id) {
    if (StringUtils.isEmpty(id)) {
      return null;
    }
    StringBuilder sql = new StringBuilder(getDefaultObjectFindSql());
    sql.append(getDefaultWhereClauseSql());
    return jdbcTemplate.queryForObject(sql.toString(), rowMapper, id);
  }
  
  @Override
  public T getById(Object id) {
    if (StringUtils.isEmpty(id)) {
      return null;
    }
    return getById(id.toString());
  }
  
  @Override
  public Id parseId(long value) {
    return new NumericId(objectType, value);
  }
  
  @Override
  public Id parseId(Object value) {
    if (null == value) {
      return null;
    }
    return parseId(value.toString());
  }
  
  @Override
  public Id parseId(String value) {
    if (StringUtils.isEmpty(value)) {
      return null;
    }
    
    value = value.trim();
    
    long id = NumberUtils.toLong(value, 0L);
    if (0L == id) {
      return new StringId(objectType, value);
    } else {
      return new NumericId(objectType, id);
    }
  }
  
  @Override
  public String getDefaultObjectFindSql() {
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT ");
    List<String> fieldList = getDefaultSelectFields();
    Iterator<String> itr = fieldList.iterator();
    while (itr.hasNext()) {
      String field = itr.next();
      sb.append(field);
      if (itr.hasNext()) {
        sb.append(",");
      }
    }
    sb.append(getDefaultFromClauseSql());
    return sb.toString();
  }
  
  @Override
  public String getDeleteSql(T object) {
    StringBuilder sb = new StringBuilder();
    sb.append("DELETE FROM ");
    sb.append(getTableName());
    sb.append(" WHERE ");
    sb.append(getIdFieldName());
    sb.append("=?");
    return sb.toString();
  }
  
  @Override
  public String getUpdateSql(T object) {
    return null;
  }
  
  @Override
  public String getInsertSql(T object) {
    String sql = "UPDATE book SET title=?, comment=?, date_release=?, author_id=? WHERE id=?";
    jdbcTemplate.update(sql, getPreparedStatementSetter(object));
    return sql;
  }
  
  protected List<T> getAllEntities() {
    String sql = getDefaultObjectFindSql();
    return jdbcTemplate.query(sql.toString(), rowMapper);
  }
  
  protected List<String> getDefaultSelectFields() {
    List<String> fields = new ArrayList<String>();
    fields.add("Id");
    return fields;
  }
  
  protected String getDefaultFromClauseSql() {
    StringBuilder sb = new StringBuilder();
    sb.append(" FROM ");
    sb.append(getTableName());
    return sb.toString();
  }
  
  protected String getDefaultWhereClauseSql() {
    StringBuilder sb = new StringBuilder();
    sb.append(" WHERE ");
    sb.append(getIdFieldName());
    sb.append("=?");
    return sb.toString();
  }
  
  protected String getIdFieldName() {
    return "Id";
  }
  
  protected abstract String getTableName();
  
  public abstract PreparedStatementSetter getPreparedStatementSetter(final T object);
  
}
