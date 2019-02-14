package com.freedom.core.config;

/**
 * 常量类
 */
public class Constant {
    /* 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    /* token头 */
    public static final String TOKE_NNAME = "Authorization";
    /* Snowflake id生成策略 前两个参数小于31大于0 */
    public static final Long WORKER_ID = 1L;
    public static final Long DATACENTER_ID = 1L;
    /* 系统时钟使用 */
    public static final Boolean IS_USESYSTEM_CLOCK = false;


    public enum StatusType{
        ;

        private String value;

        StatusType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 目录
         */
        CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
