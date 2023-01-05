package com.hatcher;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.hatcher.GetProperties.getConfig;

/**
 * Author: 花棉袄
 * Date: 2022年08月20日
 * Describe: 生成代码
 */
public class AutoGenerator implements Serializable {
    public static void Generator() throws Exception {

        Configuration config = getConfig();

        //数据库IP地址
        String databaseIp = config.getString("databaseIp");
        //数据库端口号
        String spot = config.getString("spot");
        //数据库名
        String databaseName = config.getString("databaseName");
        //数据库账号名
        String mysqlUser = config.getString("mysqlUser");
        //数据库密码
        String mysqlPassword = config.getString("mysqlPassword");
        //Mysql url配置 只需选择 String mysql = mysql5;或者 String mysql = mysql8; 其他无需修改
        String mysqlBase = "jdbc:mysql://" + databaseIp + ":" + spot + "/" + databaseName;
        String mysql5Base = "?characterEncoding=utf-8&useSSL=false";
        String mysql8Base = "?serverTimezone=GMT%2B8&characterEncoding=utf-8&useSSL=false";

        String mysql5 = mysqlBase + mysql5Base;
        String mysql8 = mysqlBase + mysql8Base;
        //选择mysql5或者mysql8    String mysql = mysql5;或者 String mysql = mysql8;
        String mysql = mysql8;

        FastAutoGenerator.create(mysql, mysqlUser, mysqlPassword)
                .globalConfig(builder -> {
                    builder.author(config.getString("author")) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir()//关闭资源管理器
                            .outputDir(config.getString("path")) // 指定输出目录
                            .commentDate(config.getString("commentDate"))//注释时间
                    ;
                })
                .packageConfig(builder -> {
                    builder.parent(config.getString("parent")) // 设置父包名
//                            .moduleName(config.getString("moduleName")) // 设置父包模块名
                            .entity(config.getString("entity")) //设置实体层
                            .service(config.getString("service")) //设置服务层
                            .serviceImpl(config.getString("serviceImpl")) //设置实现层
                            .mapper(config.getString("mapper")) //设置mapper层
                            .xml(config.getString("mapperXml")) //设置xml层
                            .controller(config.getString("controller")) //设置controller层
                            .pathInfo(Collections.singletonMap(OutputFile.xml, config.getString("pathMapperXml"))); // 设置mapperXml生成路径
                })

                .strategyConfig((scanner, builder) -> {
                    try {
                        builder.addInclude(check(config.getString("tableName"))) // 设置需要生成的表名
//                                .addTablePrefix(config.getString("tablePrefix"))
                                .serviceBuilder() //开启service策略配置
                                .formatServiceFileName("%sService") //取消Service前的I
                                .controllerBuilder() //开启controller策略配置
                                .enableRestStyle() //配置restful风格
                                .enableHyphenStyle() //url中驼峰转连字符
                                .entityBuilder() //开启实体类配置
                                .addTableFills(new Column(config.getString("create_time"), FieldFill.INSERT)) //配置添加自动填充字段
                                .addTableFills(new Column(config.getString("update_time"), FieldFill.INSERT_UPDATE)) //添加和更新配置自动填充字段
                                .enableLombok() //开启lombok
                                .controllerBuilder().enableHyphenStyle().enableRestStyle()

                        //.enableChainModel()//开启lombok链式操作@Accessors(chain = true)
                        ;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).templateConfig(builder -> builder
                        .disable(TemplateType.ENTITY)
                        .entity("/templates/entity.java")
                        .service("/templates/service.java")
                        .serviceImpl("/templates/serviceImpl.java")
                        .mapper("/templates/mapper.java")
                        .controller("/templates/controller.java")
                        .build())
                .execute();

    }

    //输入处理多个表的情况
    public static List<String> check(String allTableName) throws Exception {

        if (StringUtils.equalsIgnoreCase("true", allTableName)) {
            return getTables("all");
        } else {
            List<String> strings = new LinkedList<>();
            strings.add(getConfig().getString("tableName"));
            return strings;
        }

    }

    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
