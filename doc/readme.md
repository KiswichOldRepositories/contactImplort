
-1.注意事项
    一、脚本以更新的方式运行，不会主动删除数据库不在excel表上的内容，若有需要，请手动删除或email联系。
    二、人员以email作为唯一的识别方式，请确保email填写正确，否则可能覆盖掉其他人员信息。
    三、修改了excel表上的部门信息并不会使数据库里的部门信息更新，而会新建一个部门。

0.excel表格的要求
    一、分类行中不要出现空的单元格，应该将他和相应类的单元格合并。
    二、"部门"永远放在分类行的首格，即 "部门 姓名.. email 部门 姓名..email"类似的形式排列。

    
[建议]1.对表格做一些处理(去掉空的单元格)
    在使用程序前，先选中最左边的空列，然后ctrl + shift + →，即可选中右边所有的空列，右键删除列。
选中下方最上面的空行，ctrl + shift + ↓，即可选中下边所有的空列，右键删除。

2.调用jar包的参数有形式。[端口默认为3306，账号密码默认为showclear，符合默认条件的可以省去]

   -d+数据库连接 -o+数据库端口 -u数据库账号 -p数据库密码 -l通讯录文件位置
   eg: java -jar contactImport.jar -dlocalhost -o3306 -ushowclear -pshowclear -lE://myexcel.xslx
        
3.外部配置
    args: --template / template
    会在jar包的同目录生成一个默认的配置文件，这个配置文件用来注入表头参数，特殊情况下可以直接修改生成的配置文件，
 并再次运行脚本，而不用再次打包。（此配置为spring boot的配置文件，额外的参数可以从这里注入）
    note:在配置application.yml时，请用UTF-8编码打开文件并修改
    
    eg: java -jar contactImport.jar --template