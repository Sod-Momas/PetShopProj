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
	 * �������
	 * 
	 * @param args
	 *            ������������(��)
	 */
	public static void main(String[] args) {
		// ��ʾ��
		StartPet startPet = new StartPet();
		// ��������ṩ���ӿ�
		IPetService petService = new PetServiceImpl();
		startPet.setPm(petService);
		// ���������ṩ���ӿ�
		IAccountDAO accountDao = new AccountDAOImpl();
		IPetDAO petDao = new PetDAOImpl();
		IPetStoreDAO petStoreDao = new PetStoreDAOImpl();
		IPetOwnerDAO petOwnerDao = new PetOwnerDAOImpl();
		// Ϊ�����ṩ���������ݽӿ�
		petService.setAccountDao(accountDao);
		petService.setPetDao(petDao);
		petService.setPetOwnerDao(petOwnerDao);
		petService.setPetStoreDao(petStoreDao);
		// ������ʾ��ҳ����ʾ
		startPet.init();
		startPet.loginMenu();

	}

}
