package io.jiangbyte.app.modules.system.menu.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.jiangbyte.framework.pojo.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Charlie Zhang
 * @since 2025-11-18
 */
@Data
@TableName("sys_menu")
@Schema(name = "SysMenu", description = "菜单表")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private String id;

    /**
     * 父级ID
     */
    @Schema(description = "父级ID")
    private String pid;

    /**
     * 菜单名称
     */
    @Schema(description = "菜单名称")
    private String name;

    /**
     * 菜单路径
     */
    @Schema(description = "菜单路径")
    private String path;

    /**
     * 组件路径
     */
    @Schema(description = "组件路径")
    private String componentPath;

    /**
     * 重定向路径
     */
    @Schema(description = "重定向路径")
    private String redirect;

    /**
     * 外部链接地址
     */
    @Schema(description = "外部链接地址")
    private String externalUrl;

    /**
     * 菜单类型：0-内部菜单 1-外链菜单 2-重定向菜单 3-iframe嵌入
     */
    @Schema(description = "菜单类型：0-内部菜单 1-外链菜单 2-重定向菜单 3-iframe嵌入")
    private Integer menuType;

    /**
     * 打开方式：0-当前窗口 1-新窗口打开
     */
    @Schema(description = "打开方式：0-当前窗口 1-新窗口打开")
    private Integer openTarget;

    /**
     * iframe属性
     */
    @Schema(description = "iframe属性")
    private String iframeAttrs;

    /**
     * 菜单标题
     */
    @Schema(description = "菜单标题")
    private String title;

    /**
     * 菜单图标
     */
    @Schema(description = "菜单图标")
    private String icon;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 缓存
     */
    @Schema(description = "缓存")
    private Boolean keepAlive;

    /**
     * 可见
     */
    @Schema(description = "可见")
    private Boolean visible;

    /**
     * 钉钉
     */
    @Schema(description = "钉钉")
    private Boolean pined;

    /**
     * 无标签页
     */
    @Schema(description = "无标签页")
    private Boolean withoutTab;

    /**
     * 头部参数
     */
    @Schema(description = "头部参数")
    private String parameters;

    /**
     * 路由参数
     */
    @Schema(description = "路由参数")
    private Object extraParams;

    @Schema(description = "子级")
    @TableField(exist = false)
    private List<SysMenu> children;
}
