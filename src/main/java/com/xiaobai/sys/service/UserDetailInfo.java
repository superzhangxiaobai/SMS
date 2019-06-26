package com.xiaobai.sys.service;

import java.util.*;

import com.alibaba.druid.util.StringUtils;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.BaseService;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.MenuInfo;
import com.xiaobai.sys.entity.RoleInfo;
import com.xiaobai.sys.entity.UserInfo;
import com.xiaobai.sys.entity.UserRole;
import com.xiaobai.sys.mapper.MenuMapper;
import com.xiaobai.sys.mapper.RoleMapper;
import com.xiaobai.sys.mapper.UserInfoMapper;
import com.xiaobai.sys.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/*
 * 自定义用户名密码校验实现，一定要@Service注解，然后在配置类中加载（重载configure）
 *
 * 		Mybatis通用接口模板用法	ztt
 * 		Example example = new Example(UserInfo.class);
		Example.Criteria c = example.createCriteria();
		c.andEqualTo("status",1);
		List<UserInfo> userInfos = mapper.selectByExample(example);
 */
@Service
public class UserDetailInfo extends BaseService<UserInfo> implements UserDetailsService{

	//数据库操作
	@Autowired
	private RoleMapper roleMapper;
	//数据库操作
	@Autowired
	private MenuMapper menuMapper;
	//数据库操作
	@Autowired
	private UserInfoMapper mapper;

	//数据库操作
	@Autowired
	private UserRoleMapper userRoleMapper;
	//必须重写，自己来实现登陆验证
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		System.out.println("账号 "+username+" 登陆中");

		UserInfo useinfo = mapper.getUserLoginInfo(username.toLowerCase());
		//String password = new BCryptPasswordEncoder().encode(dbUser.getPassword());
		if(useinfo==null)
		{
			System.out.println("账号 "+username+" 不存在");
			throw new UsernameNotFoundException(username + " 不存在");
		}
		System.out.println("获取到账号数据 "+ useinfo.toString());

		UserDetails	user = new User(useinfo.getLoginname(), useinfo.getPassword(), true, true, true, true,
				getAuthorities(useinfo.getRoles(),useinfo.getId()));

		return user;
	}

	/**
	 * 获得访问角色权限
	 */
	public Collection<GrantedAuthority> getAuthorities(String access,Long userid) {

		Collection<GrantedAuthority> authorities = new ArrayList<>();      

		//所有的用户默认拥有ROLE_USER权限	
//		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (access!=null) {
			/*// 如果参数access为0.则拥有ROLE_ADMIN权限
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));*/
			for(String roleid: access.split(",")){
				RoleInfo roleInfo=new RoleInfo();
				roleInfo.setId(Long.parseLong(roleid));
				roleInfo = roleMapper.selectOne(roleInfo);
				authorities.add(new SimpleGrantedAuthority(roleInfo.getRolename()));
				List<MenuInfo> menus = menuMapper.getListByUserid(userid);
				System.out.println("用户角色:"+roleInfo.getRolename());
				for(MenuInfo menu: menus){
					if(!StringUtils.isEmpty(menu.getUrl())){
						System.out.println("用户菜单:"+menu.getUrl());
						authorities.add(new SimpleGrantedAuthority("ROLE_"+menu.getUrl()));
					}
				}
			}
		}
		return authorities;
	}

	public Map<String,Object> getAll(SysParam param) {
		Map<String,Object> result=new HashMap<>();
		param.setIsEnable(1);
		List<UserInfo> list = mapper.getAll(param);
		result.put("data",list);
		//可加入分页, 总数等数据
		return result;
	}

    public Map<String,Object> addOrUpdate(UserInfo user) {
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username= mapper.getUserLoginInfo(auth.getUsername()).getUsername();
		Date now=new Date();
		Map<String,Object> result=new HashMap<>();
		if(user.getId()==null){
			user.setCreatetime(now);
			user.setCreator(username);
			//新用户密码默认000000
			String password = new BCryptPasswordEncoder().encode("000000");
			user.setPassword(password);
			int insert= mapper.insertSelective(user);//非空属性插入
			result.put("msg",insert>0?"操作成功":"操作失败");
			result.put("data",user);
		}else {
			int update= mapper.updateByPrimaryKeySelective(user);//根据主键进行非空属性更改
			result.put("msg",update>0?"操作成功":"操作失败");
			result.put("data",user);
		}
		//更新用户角色表
		SysParam param=new SysParam();
		param.setUserid(user.getId().toString());
		List<UserRole> roles = userRoleMapper.getAll(param);
		List<String> newRoles = Arrays.asList(user.getRoles().split(","));
		List<String> oldRoles=new ArrayList<>();
		for (UserRole role: roles){
			//新值中不包含旧值就删除
			if(!newRoles.contains(role.getRoleid().toString())){
				UserRole temp=new UserRole();
				temp.setId(role.getId());
				userRoleMapper.delete(temp);
			}
			oldRoles.add(role.getRoleid().toString());
		}
		for (String roleid: newRoles){
			//旧值中不包含新值就插入
			if(!oldRoles.contains(roleid)){
				UserRole temp=new UserRole();
				temp.setUserid(user.getId());
				temp.setRoleid(Long.parseLong(roleid));
				userRoleMapper.insertSelective(temp);
			}
		}
		return result;
    }

	@Override
	public BaseMapper<UserInfo> getMapper() {
		return this.mapper;
	}

	public Map<String,Object> updatePwd(SysParam param) {
		Map<String,Object> result=new HashMap<>();
		//获取当前登录用户
		UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserInfo thisuser = mapper.getUserLoginInfo(auth.getUsername());
		//比较之前的密码是否正确
		String aa = new BCryptPasswordEncoder().encode("000000");
		String password = new BCryptPasswordEncoder().encode(param.getPassword());
		if(!password.equals(thisuser.getPassword())){
			result.put("error","原密码输入不正确");
			return result;
		}
		//编译新密码
		String newword = new BCryptPasswordEncoder().encode(param.getNewpassword());
		//修改新密码
		thisuser.setPassword(newword);
		mapper.updateByPrimaryKeySelective(thisuser);
		result.put("msg","修改成功");
		return result;
	}
}
