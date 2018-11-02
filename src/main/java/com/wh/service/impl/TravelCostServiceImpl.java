package com.wh.service.impl;

import com.google.gson.Gson;
import com.wh.dao.TravelUserTMapper;
import com.wh.pojo.vo.PageVO;
import com.wh.pojo.vo.TravelCostVO;
import com.wh.service.TravelCostService;
import com.wh.util.WriteExcel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TravelCostServiceImpl implements TravelCostService {
    private static final Integer NUM_OF_ONE_PAGE = 10;
    private static final String TRANSPORTATION_TYPE_COMP = "0";
    private static final String TRANSPORTATION_TYPE_OTHER = "1";
    private static final String STAY_TYPE_COMP = "0";
    private static final BigDecimal TRAFFIC_ALLOWANCE_ONE_DAY = new BigDecimal(60.00);

    private static final Gson GSON = new Gson();
    private static PageVO page = new PageVO();
    private WriteExcel writeExcel = new WriteExcel();
    @Resource
    private TravelUserTMapper tut;

    @Override
    public String getPage(HttpServletRequest request) {
        String time = request.getParameter("keyword");
        int totalRecords = tut.countTravelInfo(time);
        int total = totalRecords / NUM_OF_ONE_PAGE;
        total = totalRecords % NUM_OF_ONE_PAGE == 0 ? total : total + 1;
        page = new PageVO(total + "", totalRecords + "", NUM_OF_ONE_PAGE + "");
        return GSON.toJson(page);
    }

    @Override
    public String listTravel_cost(HttpServletRequest request) {
        String time = request.getParameter("keyword");
        String pn = request.getParameter("pn");
        int offst = (Integer.parseInt(pn) - 1) * NUM_OF_ONE_PAGE;
        int size = NUM_OF_ONE_PAGE;
        Map<String, Object> map = new HashMap<>();
        map.put("time", time);
        map.put("offst", offst);
        map.put("size", size);
        List<TravelCostVO> listTravelCost = tut.listTravelCost(map);
        if (listTravelCost != null) {
            listTravelCost = wrapTravelCostVO(listTravelCost);
        }
        return GSON.toJson(listTravelCost);
    }

    /**
     * 补全差旅明细
     *
     * @param listTravelCost
     * @return
     */
    private List<TravelCostVO> wrapTravelCostVO(List<TravelCostVO> listTravelCost) {
        int id = 1;
        for (int i = 0; i < listTravelCost.size(); i++) {
            TravelCostVO tcvo = listTravelCost.get(i);
            //序号设置
            id += i;
            tcvo.setId(String.valueOf(id));
            //设置费用部门
            tcvo.setCostDep(tcvo.getUserLevel());
            //计算天数
            BigDecimal days = computedDays(tcvo.getGmtGo(), tcvo.getGmtBack(), tcvo.getGoTimePoint(), tcvo.getBackTimePoint());
            tcvo.setDays(days);
            //交通公司订购金额
            BigDecimal trasportationComp = computedTransportComp(tcvo.getCostGo(), tcvo.getCostBack(), tcvo.getBookingTypeGo(), tcvo.getBookingTypeBack());
            tcvo.setTrasportationComp(trasportationComp);
            //交通其他订购金额
            BigDecimal trasportationOther = computedTransportOther(tcvo.getCostGo(), tcvo.getCostBack(), tcvo.getBookingTypeGo(), tcvo.getBookingTypeBack());
            tcvo.setTrasportationOther(trasportationOther);
            //住宿标准
            tcvo.setStayStandard(tcvo.getStayOneDay().multiply(new BigDecimal(tcvo.getStayDays())));
            //住宿公司或其他订购费
            if (STAY_TYPE_COMP.equals(tcvo.getStayBookingType())) {
                tcvo.setStayComp(tcvo.getCostStay());
                tcvo.setStayOther(BigDecimal.valueOf(0));
            } else {
                tcvo.setStayOther(tcvo.getCostStay());
                tcvo.setStayComp(BigDecimal.valueOf(0));
            }
            //交通补贴标准/天
            tcvo.setTrafficAllowanceOneDay(TRAFFIC_ALLOWANCE_ONE_DAY);
            //交通补贴标准
            tcvo.setTrafficAllowanceStandard(TRAFFIC_ALLOWANCE_ONE_DAY.multiply(days));
            //交通补贴实报
            tcvo.setTrafficAllowanceReal(BigDecimal.valueOf(0));
            //生活补贴标准/天
            BigDecimal lifeAllowanceOneDay = computedLifeAllowanceOneDay(tcvo.getUserLevel());
            tcvo.setLifeAllowanceOneDay(lifeAllowanceOneDay);
            //生活补贴标准
            tcvo.setLifeAllowanceStandard(lifeAllowanceOneDay.multiply(days));
            //生活补贴实报
            tcvo.setLifeAllowanceReal(BigDecimal.valueOf(0));

            tcvo.setSalaryAllowance(BigDecimal.valueOf(0));
            tcvo.setSubTotal(BigDecimal.valueOf(0));
        }
        return listTravelCost;
    }

    /**
     * 根据人员级别计算生活补贴标准/天
     * 0---执行级
     * 1---关联级
     *
     * @param userLevel
     * @return
     */
    private BigDecimal computedLifeAllowanceOneDay(String userLevel) {
        BigDecimal money = new BigDecimal(0);
        switch (userLevel) {
            case "0":
                money = BigDecimal.valueOf(110);
                break;
            case "1":
                money = BigDecimal.valueOf(120);
                break;
            default:
                break;
        }
        return money;
    }

    /**
     * 计算公司订购交通费
     *
     * @param costGo
     * @param costBack
     * @param bookingTypeGo
     * @param bookingTypeBack
     * @return
     */
    private BigDecimal computedTransportComp(BigDecimal costGo, BigDecimal costBack, String bookingTypeGo, String bookingTypeBack) {
        BigDecimal money = new BigDecimal(0);
        if (TRANSPORTATION_TYPE_COMP.equals(bookingTypeGo)) {
            money = money.add(costGo);
        }
        if (TRANSPORTATION_TYPE_COMP.equals(bookingTypeBack)) {
            money = money.add(costBack);
        }
        return money;
    }

    /**
     * 计算其他订购交通费
     *
     * @param costGo
     * @param costBack
     * @param bookingTypeGo
     * @param bookingTypeBack
     * @return
     */
    private BigDecimal computedTransportOther(BigDecimal costGo, BigDecimal costBack, String bookingTypeGo, String bookingTypeBack) {
        BigDecimal money = new BigDecimal(0);
        if (TRANSPORTATION_TYPE_OTHER.equals(bookingTypeGo)) {
            money = money.add(costGo);
        }
        if (TRANSPORTATION_TYPE_OTHER.equals(bookingTypeBack)) {
            money = money.add(costBack);
        }
        return money;
    }

    /**
     * 差旅天数计算
     *
     * @param gmtGo
     * @param gmtBack
     * @param goTimePoint
     * @param backTimePoint
     * @return
     */
    private BigDecimal computedDays(String gmtGo, String gmtBack, String goTimePoint, String backTimePoint) {
        BigDecimal days = new BigDecimal(0);
        int day = computedDay(gmtGo, gmtBack);
        BigDecimal floatDay = timePointComputedDay(goTimePoint, backTimePoint);
        return floatDay.add(BigDecimal.valueOf(day));
    }

    /**
     * 日期差计算天数
     *
     * @param gmtgo
     * @param gmtback
     * @return
     */
    private int computedDay(String gmtgo, String gmtback) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date go = format.parse(gmtgo);
            Date back = format.parse(gmtback);
            long time = back.getTime() - go.getTime();
            return (int) (time / 1000 / 24 / 3600);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 时间点计算浮动天数
     *
     * @param go
     * @param back
     * @return
     */
    private BigDecimal timePointComputedDay(String go, String back) {
        BigDecimal floatDay = new BigDecimal(0);
        BigDecimal halfDay = new BigDecimal(0.5);
        BigDecimal oneDay = new BigDecimal(1);

        go = go.substring(0, 2);
        back = back.substring(0, 2);

        if (12 < Integer.parseInt(go) && Integer.parseInt(go) < 18) {
            floatDay = floatDay.subtract(halfDay);
        } else if (Integer.parseInt(go) > 18) {
            floatDay = floatDay.subtract(oneDay);
        }

        if (12 > Integer.parseInt(back)) {
            floatDay = floatDay.add(halfDay);
        } else {
            floatDay = floatDay.add(oneDay);
        }
        return floatDay;
    }

    @Override
    public String exportInfo(HttpServletRequest request) {
        String time = request.getParameter("keyword");
        List<TravelCostVO> list = tut.listTravelInfoOneMonth(time);
        if (list != null && list.size() > 0) {
            List<TravelCostVO> listExport = wrapTravelCostVO(list);
            //生成excel文档、保存到服务器、返回路径
            //writeExcel.writeExcel();
            new WriteExcel().writeExcel(listExport,"E:/xxx.xls");
        }
        return "no";
    }
}
