# zookeeper_admin [![Build Status](https://travis-ci.org/luoyan35714/zookeeper_admin.svg?branch=master)](https://travis-ci.org/luoyan35714/zookeeper_admin)

#简介

Zookeeper_admin是一款基于Java EE的Zookeeper后台管理系统。实现了对Zookeeper实例的统一管理。

前台使用了`Bootstrap`，基于`gentelella`主题实现。后台使用了`Spring MVC`，`Mybatis`，`Curator`等技术。Jar包管理通过`Maven`来实现。数据库选用了`Mysql`。


#下载安装

##下载

通过如下命令从github下载本项目代码。

{% highlight bash %}
git clone https://github.com/luoyan35714/zookeeper_admin.git
{% endhighlight %}

##导入IDE

由于项目是通过Maven进行管理，所以在导入Eclipse的时候，选择Maven项目

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/01.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/01.png)

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/02.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/02.png)

其中文件夹目录作用解释如下：

+ `src/main/java` : 项目后台Java代码
+ `src/main/resources` : 项目配置文件
+ `src/test/java` : 测试代码，其中主要是针对Zookeeper的各种操作Demo
+ `src/main/webapp` : 项目的前台代码
+ `doc` : 项目SQL文件存放目录

##初始化数据库

执行`doc/zk_2017-06-09.sql`在数据库中创建数据库`zk_admin`并初始化相关的表结构。

##启动

添加项目到Tomcat下，并启动Tomcat。

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/03.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/03.png)

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/04.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/04.png)


#新建实例

启动相关的zookeeper，点击左侧`添加实例`，正确填写`Name`,`IP`,`Port`相关信息，并保存。

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/05.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/05.png)


#实例管理

点击左侧`实例列表`会出现所有录入的Zookeeper实例，可以点击`详情`，`更新`或者`删除`执行相关操作。

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/06.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/06.png)


#实例详情

点击左侧`Zookeeper实例`下的相关Zookeeper实例，右侧会出现Zookeeper的详细信息。

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/07.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/07.png)

##基本操作

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/08.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/08.png)

+ `Expand Node` : 选中节点树中的某个节点，点击Exapnse Node会展开下一级的节点
+ `Collapse Node` : 选中节点树中的某个节点，点击Collapse Node会收起下一级的节点
+ `Toggle Node` : 选中节点树中的某个节点，点击Toggle Node会在展开和收起下一级节点之间切换
+ `Expand All` : 点击Expand All会展开所有节点
+ `Collapse All` : 点击Collapse All会收起所有节点

##查看操作

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/09.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/09.png)

+ `左键选中节点` : 在节点树上左键点击节点，会在右侧出现节点详细信息
+ `Detail` : 选中某节点之后，点击Detail，会在右侧出现节点详细信息

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/10.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/10.png)

##修改操作

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/11.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/11.png)

+ `Add Child Node` : 选中某节点，添加其下层子节点
+ `Add Subling Node` : 选中某节点，添加其兄弟节点
+ `Edit Node` : 选中某节点，修改节点内容，但不能修改节点名称
+ `Delete Node` : 选中某节点，删除，可以选中父级节点，递归删除。

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/12.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/12.png)

##权限管理

点击节点树右上角的`设置`，选择`设置权限信息`。
![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/13.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/13.png)

进入权限列表，点击`ADD`, 添加相应的权限。

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/14.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/14.png)

权限列表如下，还可以点击`UPDATE`,`DELET`进行对应的操作。

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/15.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/15.png)

##查看ACL

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/16.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/16.png)

+ `ACL` : 选中节点，点击`ACL`,获取ACL相关信息。

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/17.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/17.png)

##设置ACL

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/18.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/18.png)

+ `SET ACL` : 选中节点，点击`SET ACL`,设置相关ACL信息，其中ACL的组成为`scheme:auth:perms`组成，如果想设置多个ACL可以用`;`分隔开。举例如`world:anyone:cdrwa`或`digest:admin:admin:cdrwa;digest:admin:test:cdrwa`,具体ACL相关信息可以参见[Zookeeper学习笔记之(五) - zookeeper ACL](http://www.hifreud.com/2017/01/08/zookeeper-05-acl/)

![http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/19.png](http://www.hifreud.com/images/blog/zookeeper/17-zookeeper-admin/19.png)

<br />
<br />
