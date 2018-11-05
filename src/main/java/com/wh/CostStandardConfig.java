package com.wh;

import java.math.BigDecimal;

public class CostStandardConfig {
    private static final String EXCUTIVE_LEVEL = "0";
    private static final String RELATION_LEVEL = "1";
    private static final String DEPARTMENT_LEVEL = "2";
    private static final String MANAGEMENT_LEVEL = "3";

    public static final BigDecimal TRAFFIC_ALLOWANCE_ONE_DAY = new BigDecimal(60.00);
    public static final BigDecimal DEFALUT_REAL_MONEY = new BigDecimal(0);

    /**
     * 根据人员级别计算生活补贴标准/天
     * 0---执行级
     * 1---关联级
     *
     * @param userLevel
     * @return
     */
    public BigDecimal computedLifeAllowanceOneDay(String userLevel) {
        BigDecimal money = new BigDecimal(0);
        switch (userLevel) {
            case EXCUTIVE_LEVEL:
                money = BigDecimal.valueOf(110);
                break;
            case RELATION_LEVEL:
                money = BigDecimal.valueOf(120);
                break;
            case DEPARTMENT_LEVEL:
                money = BigDecimal.valueOf(130);
                break;
            case MANAGEMENT_LEVEL:
                money = BigDecimal.valueOf(140);
                break;
            default:
                break;
        }
        return money;
    }
    public String wrapLevel(String costDep) {
        String level = " ";
        switch (costDep) {
            case EXCUTIVE_LEVEL:
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
