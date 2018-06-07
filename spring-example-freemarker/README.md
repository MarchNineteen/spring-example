# springboot mybatis整合freemarker
 [springboot 热部署](https://www.cnblogs.com/aqsunkai/p/6690574.html)

 模板文件刷新，个人不喜欢热部署方式因为那样会修改java文件一直restart，所以推荐ctrl+F9
 这种方式进行build,另一种模板修改刷新的方法修改idea为自动编译(build project automatically )
 
 模班刷新[参考](https://blog.csdn.net/diaomeng11/article/details/73826564/)
  
 springboot默认静态文件目录为static，模板文件目录为templates
 不需要webapp文件夹，打包模式也不用必须为war
 
 springmvc 可以自动以mould接收，date类型需要 @DateTimeFormat(pattern = "yyyy-MM-dd")
 要使用这个注解需添加joda-time依赖
 