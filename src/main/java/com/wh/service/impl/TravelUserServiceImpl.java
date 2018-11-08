package com.wh.service.impl;

import com.wh.dao.TravelUserMapper;
import com.wh.pojo.Information;
import com.wh.pojo.TravelInfoT;
import com.wh.pojo.TravelUserT;
import com.wh.pojo.UserT;
import com.wh.service.TravelUserService;
import com.wh.util.PageUtil;
import com.wh.vo.Page;
import com.wh.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TravelUserServiceImpl implements TravelUserService {
	@Resource
	private TravelUserMapper travelUserMapper;

	@Resource
	private PageUtil pageUtil;

	/**
	 * 新增出差人员
	 * @param travelInfoT
	 * @param listTravelUserT
	 * @param listUserT
	 */
	@Override
	@Transactional
	public void addTravelUsers(TravelInfoT travelInfoT, List<TravelUserT> listTravelUserT, List<UserT> listUserT) {
		travelUserMapper.addTravelInfoT(travelInfoT);
		travelUserMapper.addTravelUsers(listTravelUserT);
		for(int i=0;i<listUserT.size();i++){
			if(travelUserMapper.selectUser(listUserT.get(i))!=null){
				travelUserMapper.updateState(listUserT.get(i));
			}else{
				travelUserMapper.addUser(listUserT.get(i));
			}
		}
	}

	/**
	 * 编辑返回人员信息
	 * @param travelUserList
	 * @param userList
	 */
	@Override
	@Transactional
	public void editBackTravel(List<TravelUserT> travelUserList, List<UserT> userList) {
		for(int i=0;i<travelUserList.size();i++){
			travelUserMapper.updateTravelUser(travelUserList.get(i));
		}
		for(int i=0;i<userList.size();i++){
			travelUserMapper.updateUser(userList.get(i));
		}
	}

	/**
	 * 查询人员出差信息
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public Result findUsersByPage(Page page) throws Exception {
		Result result = new Result();
		if("all".equals(page.getRoleType())){
			//查询所有角色
			page.setPageSize(pageUtil.getPageSize());
			int totalCount = this.travelUserMapper.getCount(page);
			page.setTotalCount(totalCount);

			int totalPage = page.getTotalPage();

			// 处理前一页
			if (page.getCurrentPage() == 1) {
				page.setPreviousPage(1);
			} else {
				page.setPreviousPage(page.getCurrentPage() - 1);
			}
			// 处理后一页
			if (page.getCurrentPage() == totalPage) {
				page.setNextPage(totalPage);
			} else {
				page.setNextPage(page.getCurrentPage() + 1);
			}
			// 获取超链接的个数
			System.out.println("CurrentPage="+page.getCurrentPage()+" PageSize = "+ page.getPageSize()+" totalCount = "+ totalCount+ " totalPage = "+ totalPage);
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));
			System.out.println(page.getaNum());
			List<Information> users = this.travelUserMapper.getUsers(page);
			List<Information> a = new ArrayList<>();
			List<String> b = new ArrayList<>();
			for(int i=0;i<users.size();i++){
				if(!b.contains(users.get(i).getTravelNum())){
					b.add(users.get(i).getTravelNum());
					a.add(users.get(i));
				}else{
					for(int j=0;j<a.size();j++){
						if(users.get(i).getTravelNum().equals(a.get(j).getTravelNum())){
							a.get(j).setUserName(a.get(j).getUserName()+",</br>"+users.get(i).getUserName());
							a.get(j).setUserNum(a.get(j).getUserNum()+",</br>"+users.get(i).getUserNum());
						}
					}
				}
			}
			page.setData(a);
			System.err.println(page.getData());
			if (page.getData() != null) {
				result.setStatus(0);
				result.setData(page);
			} else {
				result.setStatus(1);
				result.setMessage("没有用户信息");
			}
		}else if("go".equals(page.getRoleType())){
			//查询指定角色
			page.setPageSize(pageUtil.getPageSize());
			int totalCount = this.travelUserMapper.getCountByGo(page);
			page.setTotalCount(totalCount);
			int totalPage = page.getTotalPage();
			// 处理前一页
			if (page.getCurrentPage() == 1) {
				page.setPreviousPage(1);
			} else {
				page.setPreviousPage(page.getCurrentPage() - 1);
			}
			// 处理后一页
			if (page.getCurrentPage() == totalPage) {
				page.setNextPage(totalPage);
			} else {
				page.setNextPage(page.getCurrentPage() + 1);
			}
			// 获取超链接的个数
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));
			List<Information> users = this.travelUserMapper.getUsersByGo(page);
			List<Information> a = new ArrayList<>();
			List<String> b = new ArrayList<>();
			for(int i=0;i<users.size();i++){
				if(!b.contains(users.get(i).getTravelNum())&&users.get(i).getGmtBack()==null){
					b.add(users.get(i).getTravelNum());
					a.add(users.get(i));
				}else if(b.contains(users.get(i).getTravelNum())&&users.get(i).getGmtBack()==null){
					for(int j=0;j<a.size();j++){
						if(users.get(i).getTravelNum().equals(a.get(j).getTravelNum())){
							a.get(j).setUserName(a.get(j).getUserName()+",</br>"+users.get(i).getUserName());
							a.get(j).setUserNum(a.get(j).getUserNum()+",</br>"+users.get(i).getUserNum());
						}
					}
				}
			}
			page.setData(a);
			if (page.getData() != null) {
				result.setStatus(0);
				result.setData(page);
			} else {
				result.setStatus(1);
				result.setMessage("没有用户信息");
			}
		}else if("back".equals(page.getRoleType())){
			//查询指定角色
			page.setPageSize(pageUtil.getPageSize());
			int totalCount = this.travelUserMapper.getCountByBack(page);
			page.setTotalCount(totalCount);
			int totalPage = page.getTotalPage();
			// 处理前一页
			if (page.getCurrentPage() == 1) {
				page.setPreviousPage(1);
			} else {
				page.setPreviousPage(page.getCurrentPage() - 1);
			}
			// 处理后一页
			if (page.getCurrentPage() == totalPage) {
				page.setNextPage(totalPage);
			} else {
				page.setNextPage(page.getCurrentPage() + 1);
			}
			// 获取超链接的个数
			page.setaNum(pageUtil.getFenYe_a_Num(page.getCurrentPage(), page.getPageSize(), totalCount, totalPage));
			List<Information> users = this.travelUserMapper.getUsersByBack(page);
			List<Information> a = new ArrayList<>();
			List<String> b = new ArrayList<>();
			for(int i=0;i<users.size();i++){
				if(!b.contains(users.get(i).getTravelNum())&&users.get(i).getGmtBack()!=null){
					b.add(users.get(i).getTravelNum());
					users.get(i).setBackTimes(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(users.get(i).getGmtBack()));
					a.add(users.get(i));
				}else if(b.contains(users.get(i).getTravelNum())&&users.get(i).getGmtBack()!=null){
					for(int j=0;j<a.size();j++){
						if(users.get(i).getTravelNum().equals(a.get(j).getTravelNum())){
							a.get(j).setUserName(a.get(j).getUserName()+",</br>"+users.get(i).getUserName());
							a.get(j).setUserNum(a.get(j).getUserNum()+",</br>"+users.get(i).getUserNum());
							a.get(j).setBackTimes(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(a.get(j).getGmtBack())+","+new SimpleDateFormat("yyyy-MM-dd HH:mm").format(users.get(i).getGmtBack()));
						}
					}
				}

			}
			page.setData(a);

			if (page.getData() != null) {
				result.setStatus(0);
				result.setData(page);
			} else {
				result.setStatus(1);
				result.setMessage("没有用户信息");
			}
		}
		return result;
	}

}
