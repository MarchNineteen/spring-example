# spring 定时任务设置
参考：http://gong1208.iteye.com/blog/1773177
### spring-task
- 配置文件方式

1.编写作业类
```
@Service
public class TaskServiceImpl implements TaskService {

    @Resource
    private UserDoMapper userDoMapper;

    public void addUser(){
        UserDo userDo = new UserDo();
        userDo.setUsername("user");
        userDo.setPassword("123456");
        userDo.setSex(0);
        userDo.setAge(12);
        userDo.setStatus(0);
        userDo.setPhone("12345678911");
        userDoMapper.insertSelective(userDo);
        System.out.println("success");
    }}
```
2.编写配置文件(springboot项目注解加载这个配置文件)
```
<context:component-scan base-package="com.wyb.timer.service" />

    <task:scheduled-tasks>
        <task:scheduled ref="taskServiceImpl" method="addUser" cron="0/1 * * * * ?"/>
    </task:scheduled-tasks>
```
说明：ref参数指定的即任务类，method指定的即需要运行的方法，cron及cronExpression表达式，具体写法这里不介绍了，详情见上篇文章附录。

- 注解形式

1.编写方法
```
@Scheduled(cron = "0/1 * * * * ?")
    public void addUser2(){
        UserDo userDo = new UserDo();
        userDo.setUsername("newUser");
        userDo.setPassword("123456");
        userDo.setSex(0);
        userDo.setAge(12);
        userDo.setStatus(0);
        userDo.setPhone("12345678911");
        userDoMapper.insertSelective(userDo);
        System.out.println("success");
    }
```
2.编写配置文件(springboot项目开启@EnableScheduling注解即可无需配置)
```
<task:scheduler id="qbScheduler" pool-size="10"/>
    <task:annotation-driven scheduler="qbScheduler" mode="proxy"/>
```
### Quartz
- 作业类继承自特定的基类：org.springframework.scheduling.quartz.QuartzJobBean
1.定义作业类
2.配置作业类JobDetailBean
3.配置作业调度的触发方式（触发器）
4.配置调度工厂 
- 作业类不继承自特定的基类

**两者区别在乎注册作业类的方式不同**
