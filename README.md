# 重构项目（2018-2-8）

+ 使用 spring-ioc对各模块进行解耦
    + 思路1：实现spring的注解扫描，用@Bean进行配置
    + 思路2：使用xml配置bean（....）
    + 思路3：移植部分spring boot的功能（理想*3）
    
+ 使用 spring data jpa，代替mybatis复杂的xml文件(放弃了 mybatis强无敌 :joy:)


# 更新readme
+ 好了好了 spring boot 本来就有非web应用功能