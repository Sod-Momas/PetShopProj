package cn.icloudit.pet.service;

import cn.icloudit.pet.dao.IAccountDAO;
import cn.icloudit.pet.dao.IPetDAO;
import cn.icloudit.pet.dao.IPetOwnerDAO;
import cn.icloudit.pet.dao.IPetStoreDAO;
import cn.icloudit.pet.entity.PetOwner;

public interface IPetService {
	/** 显示服务层欢迎菜单 */
	public void init();

	/** 主人登录模块 */
	public PetOwner petOwnerLogin();

	/** 购买库存宠物模块 */
	public void buyKuCunPet();

	/** 培育新宠物功能模块 */
	public void buyNewPet();

	/** 出卖宠物功能模块 */
	public void salePet();

	public void setAccountDao(IAccountDAO accountDao);

	public void setPetDao(IPetDAO petDao);

	public void setPetOwnerDao(IPetOwnerDAO petOwnerDao);

	public void setPetStoreDao(IPetStoreDAO petStoreDao);

}
