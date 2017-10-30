package cn.icloudit.pet.ui;

import cn.icloudit.pet.dao.IAccountDAO;
import cn.icloudit.pet.dao.IPetDAO;
import cn.icloudit.pet.dao.IPetOwnerDAO;
import cn.icloudit.pet.dao.IPetStoreDAO;
import cn.icloudit.pet.dao.impl.AccountDAOImpl;
import cn.icloudit.pet.dao.impl.PetDAOImpl;
import cn.icloudit.pet.dao.impl.PetOwnerDAOImpl;
import cn.icloudit.pet.dao.impl.PetStoreDAOImpl;
import cn.icloudit.pet.service.IPetService;
import cn.icloudit.pet.service.impl.PetServiceImpl;

public class Test {
	/**
	 * 程序入口
	 * 
	 * @param args
	 *            程序启动参数(空)
	 */
	public static void main(String[] args) {
		// 表示层
		StartPet startPet = new StartPet();
		// 接入服务提供方接口
		IPetService petService = new PetServiceImpl();
		startPet.setPm(petService);
		// 接入数据提供方接口
		IAccountDAO accountDao = new AccountDAOImpl();
		IPetDAO petDao = new PetDAOImpl();
		IPetStoreDAO petStoreDao = new PetStoreDAOImpl();
		IPetOwnerDAO petOwnerDao = new PetOwnerDAOImpl();
		// 为服务提供方接入数据接口
		petService.setAccountDao(accountDao);
		petService.setPetDao(petDao);
		petService.setPetOwnerDao(petOwnerDao);
		petService.setPetStoreDao(petStoreDao);
		// 启动表示层页面显示
		startPet.init();
		startPet.loginMenu();

	}

}
