# jkxy

###任务2
1. Factory 模拟一个汽车工厂生产汽车
2. Search Files 音乐文件读取工具

 

##### 个人总结：
  我的问题：
  打印信息的返回类型是void，用String优于void。
  1. void 会影响封装 
  2. 用String，一眼就知道这个是打印信息，可读性强一点。

  在方法内丢出异常，而这个是不需要的。对异常的理解不够深，什么时候用throws，什么时候用try-throw-catch。需要再深入的学一下。

  实现工厂：
  三个角色，
  1.工厂角色负责生产产品
  2.抽象或接口类型的具体实例的父类。（如果具体的产品之间相关，用抽象；否则用接口）
  3. 具体的产品。

  File里有两个过滤方法，一个是FilenameFilter，另一个是FileFilter，FilenameFilter是根据文件名过滤，有可能会忽略掉匹配名字的目录。搜索MP3不需要目录，所以用FileFilter更佳。

  多看看Java的api或者源码的注解。

###任务3
1. StartAnotherAty
2. GuessNumber 实现一个猜数字游戏

个人总结：
可以把一些初始化的逻辑代码放到一个private的方法里调用，增加代码可读。


### 任务4
1. CheckAnswer 单选题与多选题结果输出
2. SimpleCalculator

##### 个人总结
  没有写注释的习惯。
  有时候自己写的方法，光看方法名不知道这个方法是干嘛用的，还得看看里面的代码才知道。这个肯定要写注释了。
  我不写注释大部分原因是因为不知道写什么好，但不去写也学不会怎么写好注释呀。写多了才有经验嘛，这个该注释方法是干嘛用的，那个该注释一些警告内容等等。
  亲爱的，下次记得写注释，给你买好吃的犒劳你。(~﹁~) 

### 任务5
1. 许下愿望，获取返回值和状态
2. 实现一个通讯录的添加、读取、删除功能，可以发短信、打电话
3. 程序进程间回调，跨应用通讯，同步增加数字

### 任务6
1. 定时提醒笔记本，整点提醒，开机自启

### 任务7
1. 通过浏览器页面打开本机应用
2. 编写一个图片浏览器
3. 编写一个应用要求特定权限才能启动
