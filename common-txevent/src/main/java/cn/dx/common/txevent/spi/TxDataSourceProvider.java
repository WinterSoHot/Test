package cn.dx.common.txevent.spi;

import org.mybatis.spring.SqlSessionTemplate;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/7/11
 */
public interface TxDataSourceProvider {
    SqlSessionTemplate getSessionTemplate(Long userId);

    SqlSessionTemplate getSessionTemplateByTableIndex(int tableIndex);
}
