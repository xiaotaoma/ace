docker docker0配置
/etc/docker/daemon.json
{
        "bip":"192.168.100.1/24",   //配置docker网卡ip
        "registry-mirrors": [
         "https://registry.docker-cn.com"   //选择镜像，加速下载
        ]
}
==========================================================
mysql 下载安装
docker pull docker.io/mysql
创建容器并启动
docker run -p 3307:3306 --name mysql_3307 -v /home/docker/mysql/mysql_3307/data:/var/lib/mysql -v /home/docker/mysql/mysql_3307/conf:/etc/mysql/conf.d -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=123456 -d 8d99edb9fd40
-p 将容器端口3306映射到主机3307
-v 将容器/var/lib/mysql 映射到主机/home/docker/mysql/mysql_3307/data    用户保存数据
   将容器/etc/mysql/conf.d 映射到主机/home/docker/mysql/mysql_3307/conf 用于启动配置
-e 容器启动参数，MYSQL_ROOT_HOST=% root用户访问ip，MYSQL_ROOT_PASSWORD=123456   指定root用户密码
-d 镜像id
--name 容器名称
==========================================================
rocketmq 下载安装
git clone https://github.com/apache/rocketmq.git
编译
mvn -Prelease-all -DskipTests clean install -U
运行文件位于distribution/target/apache-rocketmq
配置
修改bin/runbroker.sh 修改broker启动参数 -server -Xms512m -Xmx512m -Xmn256m
修改bin/runserver.sh 修改namesrv启动参数 -server -Xms512m -Xmx512m -Xmn256m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=320m

启动、关闭
nameserver    sh bin/mqnamesrv &
              sh bin/mqshutdown namesrv
broker        sh bin/mqbroker -n localhost:9876 &
              sh bin/mqshutdown broker

    集群配置

==========================================================
rocketmq-console管理台
git clone https://github.com/apache/rocketmq-externals.git
rocket-console
编译
mvn clean package -Dmaven.test.skip=true
启动
java -jar rocketmq-console-ng-1.0.0.jar --server.port=5555 --rocketmq.config.namesrvAddr=192.168.0.33:9876 > console.log &
登陆http://localhost:5555



