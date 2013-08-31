package skynet.isi.service;

import skynet.isi.common.Enums.RESULT;
import skynet.isi.dao.Page;
import skynet.isi.model.User;

public interface AccountManager {

	/***
	 * 获得用户基本信息
	 * 
	 * @param loginName
	 * @return
	 */
	public User getUserBaseInfo(String loginName);

	/***
	 * 修改用户个人信息
	 * 
	 * @param user
	 * @return
	 */
	public RESULT modifyUserBaseInfo(String loginName, User user);

	/***
	 * 修改密码
	 * 
	 * @param info
	 * @return
	 */
	public RESULT modifyUserPassword(String loginName, String oldPassword, String newPassword);

	/***
	 * 获得用户积分
	 * 
	 * @param loginName
	 * @return
	 */
	public Integer getUserCredit(String loginName);

	public Page<User> findUser(PagingModel pagingModel, String userName);

	public boolean forgetPassword(String loginName);
}
