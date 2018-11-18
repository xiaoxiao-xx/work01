package com.wh.service.impl;

import com.google.gson.Gson;
import com.wh.CostStandardConfig;
import com.wh.dao.TravelUserTMapper;
import com.wh.pojo.vo.PageVO;
import com.wh.pojo.vo.TravelCostVO;
import com.wh.pojo.vo.TravelInfo;
import com.wh.service.TravelCostService;
import com.wh.util.WriteExcel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TravelCostServiceImpl implements TravelCostService {
    private static final Integer NUM_OF_ONE_PAGE = 10;
    private static final String TRANSPORTATION_TYPE_COMP = "1";
    private static final String TRANSPORTATION_TYPE_OTHER = "2";
    private static final String STAY_TYPE_COMP = "0";
    private static final String TITEL_START = "微核及中粒";
    private static final String TITEL_END = "差旅费明细表";
    private CostStandardConfig cs = new CostStandardConfig();
    private static final Gson GSON = new Gson();
    private static PageVO page = new PageVO();
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
        int offset = (Integer.parseInt(pn) - 1) * NUM_OF_ONE_PAGE;
        int size = NUM_OF_ONE_PAGE;
        Map<String, Object> map = new HashMap<>();
        map.put("time", time);
        map.put("offset", offset);
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
        int id = 0;
        for (int i = 0; i < listTravelCost.size(); i++) {
            TravelCostVO tcvo = listTravelCost.get(i);
            //序号设置
            id += 1;
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
            tcvo.setStayStandard(tcvo.getStayOneDay().multiply(stayDays(tcvo.getStayDays())));
            //住宿公司或其他订购费
            if (STAY_TYPE_COMP.equals(tcvo.getStayBookingType())) {
                tcvo.setStayComp(tcvo.getCostStay());
                tcvo.setStayOther(CostStandardConfig.DEFALUT_REAL_MONEY);
            } else {
                tcvo.setStayOther(tcvo.getCostStay());
                tcvo.setStayComp(CostStandardConfig.DEFALUT_REAL_MONEY);
            }
            //交通补贴标准/天
            tcvo.setTrafficAllowanceOneDay(CostStandardConfig.TRAFFIC_ALLOWANCE_ONE_DAY);
            //交通补贴标准
            tcvo.setTrafficAllowanceStandard(CostStandardConfig.TRAFFIC_ALLOWANCE_ONE_DAY.multiply(days).setScale(2, BigDecimal.ROUND_HALF_UP));
            //交通补贴实报
            tcvo.setTrafficAllowanceReal(CostStandardConfig.DEFALUT_REAL_MONEY);
            //生活补贴标准/天
            BigDecimal lifeAllowanceOneDay = cs.computedLifeAllowanceOneDay(tcvo.getUserLevel());
            tcvo.setLifeAllowanceOneDay(lifeAllowanceOneDay);
            //生活补贴标准
            tcvo.setLifeAllowanceStandard(lifeAllowanceOneDay.multiply(days).setScale(2, BigDecimal.ROUND_HALF_UP));
            //生活补贴实报
            tcvo.setLifeAllowanceReal(CostStandardConfig.DEFALUT_REAL_MONEY);

            tcvo.setSalaryAllowance(CostStandardConfig.DEFALUT_REAL_MONEY);
            tcvo.setSubTotal(CostStandardConfig.DEFALUT_REAL_MONEY);
        }
        return listTravelCost;
    }

    private BigDecimal stayDays(String stayDays) {
        if (stayDays == null || "".equals(stayDays)) {
            return BigDecimal.ZERO;
        }
        return new BigDecimal(stayDays);
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
        BigDecimal money = BigDecimal.ZERO;
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
        BigDecimal money = BigDecimal.ZERO;
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

    /**
     * 非空数据导出到excel
     * @param request
     */
    @Override
    public void exportInfo(HttpServletRequest request, HttpServletResponse response) {
        String time = request.getParameter("keyword");
        String title = TITEL_START + time + TITEL_END;
        List<TravelCostVO> list = tut.listTravelInfoOneMonth(time);
        if (list != null && list.size() > 0) {
            List<TravelCostVO> listExport = wrapTravelCostVO(list);
            List<TravelInfo> travelInfos = travelCostToInfo(listExport);
            String path = request.getSession().getServletContext().getRealPath("/file");
            String filePath = path + "\\"+"travel.xlsx";
            try {
                WriteExcel excel = new WriteExcel(filePath);
                excel.setFirtRow(title);
                excel.setLast(true);
                excel.writeExcel(travelInfos, 3);
                downLoadFile(time,filePath, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void downLoadFile(String title, String strUrl, HttpServletResponse response) throws IOException {
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(strUrl)));
        // 假如以中文名下载的话
        // 转码，免得文件名中文乱码
        String filename = title+"差旅费明细表.xlsx";
        filename = URLEncoder.encode(filename, "UTF-8");
        // 设置文件下载头
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);
        // 1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
        response.setContentType("multipart/form-data");
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
        byte[] buffer = new byte[1024];
        int len;
        while ((len = bis.read(buffer)) != -1) {
            out.write(buffer,0,len);
        }
        bis.close();
        out.close();
    }

    /**
     * 为excel对应字段赋值
     *
     * @param listExport
     * @return
     */
    private List<TravelInfo> travelCostToInfo(List<TravelCostVO> listExport) {
        if (listExport != null && listExport.size() > 0) {
            List<TravelInfo> travelInfos = new ArrayList<>(listExport.size());
            for (int i = 0; i < listExport.size(); i++) {
                TravelCostVO t = listExport.get(i);
                TravelInfo b = new TravelInfo();
                b.setId(t.getId());
                b.setUserName(t.getUserName());
                b.setDepartment(t.getDepartment());
                b.setCostDep(t.getCostDep());
                b.setUserLevel(t.getUserLevel());
                b.setCause(t.getCause() == null ? "" : t.getCause());
                b.setTrip(t.getTrip());
                b.setGmtGo(t.getGmtGo());
                b.setGoTimePoint(t.getGoTimePoint());
                b.setGmtBack(t.getGmtBack());
                b.setBackTimePoint(t.getBackTimePoint());
                b.setDays(t.getDays());
                b.setTrasportationComp(t.getTrasportationComp());
                b.setTrasportationOther(t.getTrasportationOther());
                b.setStayOneDay(t.getStayOneDay());
                b.setStayStandard(t.getStayStandard());
                b.setStayComp(t.getStayComp());
                b.setStayOther(t.getStayOther());
                b.setTrafficAllowanceOneDay(t.getTrafficAllowanceOneDay());
                b.setTrafficAllowanceStandard(t.getTrafficAllowanceStandard());
                b.setTrafficAllowanceReal(t.getTrafficAllowanceReal());
                b.setSalaryAllowance(t.getSalaryAllowance());
                b.setLifeAllowanceOneDay(t.getLifeAllowanceOneDay());
                b.setLifeAllowanceStandard(t.getLifeAllowanceStandard());
                b.setLifeAllowanceReal(t.getLifeAllowanceReal());
                b.setSubTotal(t.getSubTotal());
                b.setMarks(t.getMarks() == null ? "" : t.getMarks());
                travelInfos.add(b);
            }
            return travelInfos;
        }
        return null;
    }
}
