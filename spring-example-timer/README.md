# spring 定时任务设置
> [参考](http://gong1208.iteye.com/blog/1773177)：http://gong1208.iteye.com/blog/1773177
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

Spring Quartz 和 Spring Task执行时间对比： 
1. Quartz设置同步模式时：一个任务的两次执行的时间间隔是：“执行时间”和“trigger的设定间隔”的最大值 
2. Task默认同步模式：一个任务的两次执行的时间间隔是：“执行时间”+“trigger的设定间隔”，即一个任务完成执行后，才开始trigger计时

Spring Quartz 特点： 
1. 默认多线程异步执行 
2. 一个任务在上一次调度未完成执行，下一次调度时间到时，会另起一个线程开始新的调度。在业务繁忙时，一个任务或许会有多个线程在执行，导致数据处理异常。 
3. 单任务同步：配置属性，可以使一个任务的一次调度在未完成时，而不会开启下一次调度 
4. 多个任务同时运行，任务之间没有直接的影响，多任务执行的快慢取决于CPU的性能 
5. SchedulerFactoryBean不能使用注解来配置？还是我没找到注解的方法？

Spring Task特点： 
1. 默认单线程同步执行 
2. 一个任务执行完上一次之后，才会执行下一次调度 
3. 多任务之间按顺序执行，一个任务执行完成之后才会执行另一个任务 
4. 多任务并行执行需要设置线程池 
5. 全程可以通过注解配置
