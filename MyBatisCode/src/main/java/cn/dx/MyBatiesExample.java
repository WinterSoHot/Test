package cn.dx;

import cn.dx.mapper.AccountMapper;
import cn.dx.mapper.FieldMapMapper;
import cn.dx.model.Account;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author gudongxian
 * @version 0.1
 * <p>
 * 2021/2/24
 */
public class MyBatiesExample {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DataSource dataSource = new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return getConnection("root", "root");
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return DriverManager.getConnection("jdbc:mysql://localhost:3306/kg?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false", username, password);
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                return null;
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;
            }

            @Override
            public PrintWriter getLogWriter() throws SQLException {
                return null;
            }

            @Override
            public void setLogWriter(PrintWriter out) throws SQLException {

            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {

            }

            @Override
            public int getLoginTimeout() throws SQLException {
                return 0;
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                return null;
            }
        };
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(AccountMapper.class);
        configuration.addMapper(FieldMapMapper.class);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            AccountMapper accountMapper = session.getMapper(AccountMapper.class);
            List<Account> accounts = accountMapper.selectAll();
            accounts.forEach(System.out::println);
            FieldMapMapper fieldMapMapper = session.getMapper(FieldMapMapper.class);
            fieldMapMapper.selectAll().forEach(fieldMap -> System.out.println(fieldMap.getField()));
        } finally {
            System.out.println("执行结束");
        }
    }
}
