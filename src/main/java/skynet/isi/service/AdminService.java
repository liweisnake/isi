package skynet.isi.service;

import java.util.List;

import skynet.isi.dao.Page;
import skynet.isi.model.Promotion;
import skynet.isi.resultmodel.CreditHistoryViewModel;
import skynet.isi.resultmodel.PromotionInfo;
import skynet.isi.resultmodel.UserCreditViewModel;

public interface AdminService {

	public Promotion getPromotion(int banner);

	public List<PromotionInfo> getPromotionList();

	public void updatePromotion(PromotionInfo promotionInfo) throws Exception;
	
	public void updateCredit(String loginName, int totalCredit);

	public Page<CreditHistoryViewModel> getCreditHistoryViewModel(
			PagingModel pagingModel, String loginName);

	public UserCreditViewModel getUserCreditViewModel(String loginName);

}
