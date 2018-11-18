package com.wh;

import java.math.BigDecimal;

public class CostStandardConfig {
    private static final String EXECUTIVE_LEVEL = "执行级";
    private static final String RELATION_LEVEL = "关联级";
    private static final String DEPARTMENT_LEVEL = "部门级";
    private static final String MANAGEMENT_LEVEL = "经营级";

    /**
     * 默认交通补贴金额
     */
    public static BigDecimal TRAFFIC_ALLOWANCE_ONE_DAY = new BigDecimal("60.00");

    /**
     * 默认实报金额
     */
    public static final BigDecimal DEFALUT_REAL_MONEY = BigDecimal.ZERO;

    /**
     * 根据人员级别计算生活补贴标准/天
     * 0---执行级
     * 1---关联级
     * 2---部门级
     * 3---经营级
     * @param userLevel
     * @return
     */
    public BigDecimal computedLifeAllowanceOneDay(String userLevel) {
        BigDecimal money = BigDecimal.ONE;
        switch (userLevel) {
            case EXECUTIVE_LEVEL:
                money = new BigDecimal("110.00");
                break;
            case RELATION_LEVEL:
                money = new BigDecimal("120.00");
                break;
            case DEPARTMENT_LEVEL:
                money = new BigDecimal("130.00");
                break;
            case MANAGEMENT_LEVEL:
                money = new BigDecimal("140.00");
                break;
            default:
                break;
        }
        return money;
    }
    public String wrapLevel(String costDep) {
        String level = " ";
        switch (costDep) {
            case EXECUTIVE_LEVEL:
                level = "执行级";
                break;
            case RELATION_LEVEL:
                level = "关联级";
                break;
            case DEPARTMENT_LEVEL:
                level = "部门级";
                break;
            case MANAGEMENT_LEVEL:
                level = "经营级";
                break;
            default:
                break;
        }
        return level;
    }

}
