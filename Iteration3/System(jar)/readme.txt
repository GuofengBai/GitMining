运行前提及步骤：
1.JDK1.8及以上
2.MySQL，版本越新越好，推荐5.7.12.0版本，若无可到下方地址下载并安装，确保端口为3306（默认值）
3.（推荐）MySQL workbench，可视化操作
4.新增用户root，密码root，通过如下语句创建：
CREATE USER 'root'@'localhost' IDENTIFIED BY 'root';
GRANT ALL ON *.* TO 'root'@'localhost' WITH GRANT OPTION；
如果已有用户root，但密码不为root，可以通过如下语句更改：
SET PASSWORD FOR 'root'@'localhost' = PASSWORD('root');
5.下载GitMining数据库文件（.sql文件），地址见最后
6.解压压缩包，新建名为gitmining的scheme，将数据库文件导入该scheme（推荐使用MySQL workbench导入）
7.直接运行GitMining.jar文件。




注：程序运行所需数据已包含在数据库文件中，可以不必联网直接运行。但是不排除由于数据量太大，
    下载时有所遗漏的情况，还是建议在有网络的情况下运行，以便在数据库中查不到相关数据时可以上网查到。


数据库文件及MySQL下载地址：http://pan.baidu.com/s/1c27aOqW