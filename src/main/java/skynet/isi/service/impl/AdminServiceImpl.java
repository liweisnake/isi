package skynet.isi.service.impl;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import skynet.isi.dao.CreditHistoryDao;
import skynet.isi.dao.Page;
import skynet.isi.dao.PromotionDao;
import skynet.isi.dao.UserDao;
import skynet.isi.model.CreditHistory;
import skynet.isi.model.Promotion;
import skynet.isi.model.User;
import skynet.isi.resultmodel.CreditHistoryViewModel;
import skynet.isi.resultmodel.PromotionInfo;
import skynet.isi.resultmodel.UserCreditViewModel;
import skynet.isi.service.AccountService;
import skynet.isi.service.AdminService;
import skynet.isi.service.CommonService;
import skynet.isi.service.PagingModel;
import skynet.isi.service.ProductSearchService;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

	protected static final Logger log = LogManager
			.getLogger(AdminServiceImpl.class.getName());

	@Autowired
	private ProductSearchService productSearchService;

	@Autowired
	private CreditHistoryDao creditHistoryDao;
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private PromotionDao promotionDao;
	
	@Autowired
	private UserDao userDao;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public List<PromotionInfo> getPromotionList() {
		List<Promotion> promotions = promotionDao.getAll();
		List<PromotionInfo> promotionInfos = new ArrayList<PromotionInfo>();
		for (Promotion promotion : promotions) {
			PromotionInfo promotionInfo = new PromotionInfo();
			promotionInfo.setBanner(promotion.getBanner());
			promotionInfo.setDescription(promotion.getDescription());
			promotionInfo.setEndTime(format(promotion.getEndTime()));
			promotionInfo.setLink(promotion.getLink());
			promotionInfo.setPicUrl(promotion.getPicUrl());
			promotionInfo.setStartTime(format(promotion.getStartTime()));
			promotionInfo.setTitlePicUrl(promotion.getTitlePicUrl());

			promotionInfos.add(promotionInfo);
		}
		return promotionInfos;
	}

	private String format(Date date) {
		if (date == null)
			return "";
		else
			return sdf.format(date);
	}

	public Promotion getPromotion(int banner) {
		return promotionDao.findBy("banner", banner).get(0);
	}

	public void updatePromotion(PromotionInfo promotionInfo) throws Exception {
		Promotion promotion = promotionDao.findBy("banner",
				promotionInfo.getBanner()).get(0);
		promotion.setDescription(promotionInfo.getDescription());
		promotion.setEndTime(parse(promotionInfo.getEndTime()));
		promotion.setStartTime(parse(promotionInfo.getStartTime()));
		promotion.setLink(promotionInfo.getLink());
		promotion.setTitlePicUrl(URLfilter(promotionInfo.getTitlePicUrl()));
		promotion.setPicUrl(URLfilter(promotionInfo.getPicUrl()));
		promotionDao.save(promotion);
	}

	private String URLfilter(String url) {
		return url.replaceAll("\\\\", "/");
	}

	private Date parse(String date) throws Exception {
		if (StringUtils.isEmpty(date))
			return new Date();
		else
			return sdf.parse(date);
	}
	
	public void updateCredit(String loginName, int totalCredit){
		User user = accountService.getUserByLoginName(loginName);
		user.setCredit(totalCredit);
		
		userDao.save(user);
	}

	public UserCreditViewModel getUserCreditViewModel(String loginName) {
		if (StringUtils.isEmpty(loginName)) {
			return new UserCreditViewModel();
		}
		UserCreditViewModel ucvm = new UserCreditViewModel();
		try {
			loginName = loginName.trim();
			String hql = "select {0} from CreditHistory ch, User u where ch.user.id = u.id and u.loginName = ?";
			hql = MessageFormat
					.format(hql,
							"new skynet.isi.resultmodel.UserCreditViewModel(u.id, u.loginName, u.email, sum(ch.usedCredit), u.credit, max(ch.createDate))");
			List<UserCreditViewModel> result = creditHistoryDao.find(hql,
					loginName);
			if (result.size() > 0)
				ucvm = result.get(0);
			List<CreditHistory> list = creditHistoryDao.findBy("createDate",
					ucvm.getLastUseDate());
			if (list.size() > 0)
				ucvm.setLastLeftCredit(list.get(0).getLeftCredit());
		} catch (Exception e) {
			log.error(e);
		}
		return ucvm;
	}

	public Page<CreditHistoryViewModel> getCreditHistoryViewModel(
			PagingModel pagingModel, String loginName) {
		commonService.checkPagingModel(pagingModel);
		Page<CreditHistoryViewModel> page = Page.getInstance(
				pagingModel.getStart(), pagingModel.getLimit());
		if (StringUtils.isEmpty(loginName)) {
			return page;
		}
		try {
			String hql = "select {0} from CreditHistory ch, User u where ch.user.id = u.id and u.loginName = ? order by ch.createDate desc";
			hql = MessageFormat
					.format(hql,
							"new skynet.isi.resultmodel.CreditHistoryViewModel(u.loginName, u.email, ch.createDate, ch.usedCredit, ch.leftCredit)");
			List<CreditHistoryViewModel> result = creditHistoryDao.find(hql,
					loginName);
			page.setResult(result);
		} catch (Exception e) {
			log.error(e);
		}
		return page;
	}

}
