
0.excel表格的要求
    一、分类行中不要出现空的单元格，应该将他和相应类的单元格合并。
    二、"部门"永远放在分类行的首格，即 "部门 姓名.. email 部门 姓名..email"类似的形式排列。
    三、人员将以email作为唯一的识别方式，请确保eamil填写正确，否则可能覆盖掉其他人员信息。
    四、修改了部门信息并不会使数据库里的部门信息更新，而会新建一个部门。
    
1.在使用程序前，先选中最左边的空列，然后ctrl + shift + →，即可选中右边所有的空列，右键删除列。
选中下方最上面的空行，ctrl + shift + ↓，即可选中下边所有的空列，右键删除。

2.调用jar包的参数有三种形式。

    一、-d+数据库连接 -o+数据库端口 -u数据库账号 -p数据库密码 -l通讯录文件位置
        eg: java -jar contactImport.jar -dlocalhost -o3306 -uroot -proot -lE://myexcel.xslx
        
    二、按顺序写入 数据库连接 数据库端口 数据库账号 数据库密码 通讯录文件位置
        eg: java -jar contactImport.jar localhost 3306 root root myexcel.xslx
        
    三（测试）、随意写入 数据库连接 数据库端口 数据库账号 数据库密码 通讯录文件位置(顺序随意，但在极为特殊的情况下可能出错)
        eg: java -jar localhost myexcel.xslx 3306 root root contactImport.jar
