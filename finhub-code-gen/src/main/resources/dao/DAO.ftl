package ${conf.daoPackageName}.${table.lowerCamelName};

import com.finhub.framework.mybatis.dao.BaseDAO;

import ${conf.daoPackageName}.${table.lowerCamelName}.po.${table.className}PO;

/**
 * ${table.comment} DAO
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
public interface ${table.className}DAO extends BaseDAO<${table.className}PO> {

}
