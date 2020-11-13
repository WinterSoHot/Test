package cn.dx.io.code;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.*;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gudongxian
 * @version 0.1
 * @date 2020/11/5
 */
public class App {
    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;

        try {
            Context context = new Context(ModelType.CONDITIONAL);
            context.setId("simple");
            context.setTargetRuntime("MyBatis3Simple");

            /*添加属性*/
            context.addProperty("javaFileEncoding", "UTF-8");

            /*注释生成器配置*/
            CommentGeneratorConfiguration commentGeneratorConfig = new CommentGeneratorConfiguration();
            commentGeneratorConfig.addProperty("suppressAllComments", "true");
            context.setCommentGeneratorConfiguration(commentGeneratorConfig);

            /*JDBC连接信息配置*/
            JDBCConnectionConfiguration jdbcConnectionConfig = new JDBCConnectionConfiguration();
            jdbcConnectionConfig.setDriverClass("com.mysql.cj.jdbc.Driver");
            //注意代码配置中JDBC连接字符串中的参数分隔符不需要再像xml配置文件中那样使用转义符
            jdbcConnectionConfig.setConnectionURL("jdbc:mysql://localhost:3306/kg?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false");
            jdbcConnectionConfig.setUserId("root");
            jdbcConnectionConfig.setPassword("root");
            jdbcConnectionConfig.addProperty("nullCatalogMeansCurrent", "true");//MySQL无法识别table标签中schema类的配置，所以在URL上指明目标数据库，并追加nullCatalogMeansCurrent属性为true
            jdbcConnectionConfig.addProperty("remarksReporting", "true");//针对oracle数据库无法读取表和字段备注
            jdbcConnectionConfig.addProperty("useInformationSchema", "true");//针对mysql数据库无法读取表和字段备注
            context.setJdbcConnectionConfiguration(jdbcConnectionConfig);

            /*Model生成器配置*/
            JavaModelGeneratorConfiguration javaModelGeneratorConfig = new JavaModelGeneratorConfiguration();
            javaModelGeneratorConfig.setTargetProject("MyBatisCode/output");//目标项目(源码主路径)
            javaModelGeneratorConfig.setTargetPackage("cn.dx.model");//目标包(Model类文件存放包)
            context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfig);

            /*SqlMapper生成器配置(*Mapper.xml类文件)，要javaClient生成器类型配合*/
            SqlMapGeneratorConfiguration sqlMapGeneratorConfig = new SqlMapGeneratorConfiguration();
            sqlMapGeneratorConfig.setTargetProject("MyBatisCode/output");//目标项目(源码主路径)
            sqlMapGeneratorConfig.setTargetPackage("cn.dx.mapper");//目标包(*Mapper.xml类文件存放包)
            context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfig);

            /*JavaClient生成器配置(*Mapper.java类文件)*/
            JavaClientGeneratorConfiguration javaClientGeneratorConfig = new JavaClientGeneratorConfiguration();
            javaClientGeneratorConfig.setConfigurationType("ANNOTATEDMAPPER");//JavaClient生成器类型(主要有ANNOTATEDMAPPER、MIXEDMAPPER、XMLMAPPER，要Context的TargetRuntime配合)
            javaClientGeneratorConfig.setTargetProject("MyBatisCode/output");//目标项目(源码主路径)
            javaClientGeneratorConfig.setTargetPackage("cn.dx.mapper");//目标包(*Mapper.java类文件存放包)
            context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfig);

            /*表生成配置*/
            TableConfiguration tableConfig = new TableConfiguration(context);
            tableConfig.setTableName("%");
            GeneratedKey generatedKey = new GeneratedKey("id", "JDBC", true, null);//设置主键列和生成方式
            tableConfig.setGeneratedKey(generatedKey);
            context.addTableConfiguration(tableConfig);

            Configuration config = new Configuration();
            config.addContext(context);

            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(String.join("\n", warnings));
        }
    }
}
