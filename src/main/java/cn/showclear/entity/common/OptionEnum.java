package cn.showclear.entity.common;

/**
 * 脚本所要做出的操作
 * <ul>
 * <li>帮助</li>
 * <li>查表并储存（主要功能）</li>
 * <li>生成样板配置文件</li>
 * <p>这个配置文件用来保存表头的字段含义，默认的配置是由jar包内的配置文件完成</p>
 * </ul>
 *
 * 在普通的操作时，为了速度，不需要启动spring boot
 */
public enum OptionEnum {
    help,
    transform,
    templeXML,

}
