package cn.icloudit.pet.service;

import cn.icloudit.pet.dao.IAccountDAO;
import cn.icloudit.pet.dao.IPetDAO;
import cn.icloudit.pet.dao.IPetOwnerDAO;
import cn.icloudit.pet.dao.IPetStoreDAO;
import cn.icloudit.pet.entity.PetOwner;

public interface IPetService {
	/** ��ʾ����㻶ӭ�˵� */
	public void init();

	/** ���˵�¼ģ�� */
	public PetOwner petOwnerLogin();

	/** ���������ģ�� */
	public void buyKuCunPet();

	/** �����³��﹦��ģ�� */
	public void buyNewPet();

	/** �������﹦��ģ�� */
	public void salePet();

	public void setAccountDao(IAccountDAO accountDao);

	public void setPetDao(IPetDAO petDao);

	public void setPetOwnerDao(IPetOwnerDAO petOwnerDao);

	public void setPetStoreDao(IPetStoreDAO petStoreDao);

}
