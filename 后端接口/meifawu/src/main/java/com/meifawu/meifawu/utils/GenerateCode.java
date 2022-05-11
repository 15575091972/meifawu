package com.meifawu.meifawu.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class GenerateCode {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/meifawu?serverTimezone=UTC", "root", "root")
                .globalConfig(builder -> {
                    builder.author("刘诚可") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("E://CodeDataMeiFaWu//"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.meifawu.meifawu") // 设置父包名
//                            .moduleName("publicity") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "E://CodeDataMeiFaWu//")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("assess") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
