## 该包下的内容为jpa的save方法比较新老实体的标准

You can customize the EntityInformation abstraction used in the SimpleJpaRepository
 implementation by creating a subclass of JpaRepositoryFactory and overriding the 
 getEntityInformation(…) method accordingly. You then have to register the custom 
 implementation of JpaRepositoryFactory as a Spring bean. 
 Note that this should be rarely necessary. See the JavaDoc for details.
 
 ---
 jpa实现 insert or update好像比较麻烦 ，这里就find后自己判断下得了