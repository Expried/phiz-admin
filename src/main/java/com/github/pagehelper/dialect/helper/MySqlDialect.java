package com.github.pagehelper.dialect.helper;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.Page;
import com.github.pagehelper.dialect.AbstractHelperDialect;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Limit;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;

public class MySqlDialect extends AbstractHelperDialect {

    @SuppressWarnings("rawtypes")
	@Override
    public String getPageSql(String sql, Page page, CacheKey pageKey) {
    	
    	try {
			Select select = (Select) CCJSqlParserUtil.parse(sql);
			PlainSelect body = (PlainSelect) select.getSelectBody();
			Limit limit = new Limit();
			limit.setOffset((page.getPageNum() - 1) * page.getPageSize());
			limit.setRowCount(page.getPageSize());
			if (body.getJoins() != null) {
				String alias = "";
				if (body.getFromItem().getAlias()!= null) {
					alias = body.getFromItem().getAlias().getName();
				}
				String bodySql = body.getFromItem().toString();
				int index = bodySql.indexOf("(");
				if (index != -1 && index == 0) {
					bodySql = bodySql.substring(index + 1, bodySql.lastIndexOf(")"));
				}
				pageKey.update(page.getPageSize());
				Select limitSelect = (Select) CCJSqlParserUtil.parse(bodySql);
				PlainSelect _body = (PlainSelect) limitSelect.getSelectBody();
				
				_body.setLimit(limit);
				StringBuffer limitSql = new StringBuffer("SELECT * FROM ( ");
				limitSql.append(_body.toString());
				limitSql.append(" ) ");
				limitSql.append(alias);
				limitSelect = (Select) CCJSqlParserUtil.parse(limitSql.toString());
				_body = (PlainSelect) limitSelect.getSelectBody();
				body.setFromItem(_body.getFromItem());
			}
			if (page.getStartRow() != 0) {
				pageKey.update(page.getStartRow());
			}
			if (body.getJoins() == null) {
				body.setLimit(limit);
			}
			return body.toString();
		} catch (JSQLParserException e) {
			e.printStackTrace();
		}
		return null;
    }

	@Override
	public String getCountSql(MappedStatement ms, BoundSql boundSql, Object parameterObject, RowBounds rowBounds,CacheKey countKey) {
		try {
			String sql = super.getCountSql(ms, boundSql, parameterObject, rowBounds, countKey);
			Statement stm = CCJSqlParserUtil.parse(sql);
			Select select = (Select) stm;
			SelectBody selectBody = select.getSelectBody();
			PlainSelect body = (PlainSelect) selectBody;
			StringBuffer _count = new StringBuffer("SELECT COUNT(0) FROM ");
			_count.append(body.getFromItem().toString());
			if (body.getWhere()!= null) {
				_count.append(" WHERE ");
				_count.append(body.getWhere().toString());
			}
			return _count.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
