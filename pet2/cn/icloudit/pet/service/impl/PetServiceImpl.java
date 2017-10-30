package cn.icloudit.pet.service.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import cn.icloudit.pet.dao.IAccountDAO;
import cn.icloudit.pet.dao.IPetDAO;
import cn.icloudit.pet.dao.IPetOwnerDAO;
import cn.icloudit.pet.dao.IPetStoreDAO;
import cn.icloudit.pet.entity.Pet;
import cn.icloudit.pet.entity.PetOwner;
import cn.icloudit.pet.entity.PetStore;
import cn.icloudit.pet.service.IPetService;
import cn.icloudit.pet.utils.DBHelper;

/**
 * 
 * @author ��Сɽ
 *
 */
public class PetServiceImpl implements IPetService {
	// *********��Ҫ��Ա****************
	/** Account��DAO */
	private IAccountDAO accountDao;
	/** Pet��DAO */
	private IPetDAO petDao;
	/** PetOwner��DAO */
	private IPetOwnerDAO petOwnerDao;
	/** PetStore��DAO */
	private IPetStoreDAO petStoreDao;

	// **********getter****************
	@Override
	public void setAccountDao(IAccountDAO accountDao) {
		this.accountDao = accountDao;
	}

	@Override
	public void setPetDao(IPetDAO petDao) {
		this.petDao = petDao;
	}

	@Override
	public void setPetOwnerDao(IPetOwnerDAO petOwnerDao) {
		this.petOwnerDao = petOwnerDao;
	}

	@Override
	public void setPetStoreDao(IPetStoreDAO petStoreDao) {
		this.petStoreDao = petStoreDao;
	}

	/** ���뷨 */
	private Scanner input = new Scanner(System.in);
	/** DBhHelper ���ݿ������ */
	private DBHelper dbHelper = new DBHelper();
	/** ��¼�� */
	private PetOwner petOwner;

	/**
	 * ��ʾ����㻶ӭ�˵�
	 */
	@Override
	public void init() {
		// ��ȡ���ݿ�����
		Connection con = dbHelper.getConnection();
		System.out.println("ϵͳ������...................");
		// ��ѯ���г���
		List<Pet> list = petDao.queryAll(con);
		System.out.println("�����mysql������.............");
		// ��ʾ���г���
		int i = 0;
		System.out.println("���\t\t��������\t\t��������\t\t��������");
		for (Pet p : list) {
			System.out.println(++i + "\t\t" + p.getName() + "\t\t" + p.getTypeName() + "\t\t" + p.getBirthday());
		}

		// ��ѯ��������
		List<PetOwner> owners = petOwnerDao.queryAll(con);
		System.out.println("-----------------------------------");
		System.out.println("�������˴�mysql������.............");
		// ��ʾ��������
		i = 0;
		System.out.println("���\t\t��������\t\t����Ԫ��");
		for (PetOwner po : owners) {
			System.out.println(++i + "\t\t" + po.getName() + "\t\t" + po.getMoney());
		}

		// ��ѯ�����̵�
		List<PetStore> pst = petStoreDao.queryAll(con);
		System.out.println("-----------------------------------");
		System.out.println("�����̵��mysql������.............");
		// ��ʾ�����̵�
		i = 0;
		System.out.println("���\t\t�̵�����\t\t�̵�Ԫ��");
		for (PetStore store : pst) {
			System.out.println(++i + "\t\t" + store.getName() + "\t\t" + store.getBalance());
		}
	}

	/** ���˵�¼ģ�� */
	@Override
	public PetOwner petOwnerLogin() {
		// �����û�������
		System.out.print("�������������������");
		String name = input.next();
		System.out.print("����������������룺");
		String password = input.next();
		if ("".equals(name) || "".equals(password)) {
			System.out.println("�û��������벻��Ϊ�գ�");
			return null;
		}

		// ���Ե�¼
		Connection con = dbHelper.getConnection();
		List<PetOwner> list = petOwnerDao.queryByTj(con, "name=? AND password=?", new Object[] { name, password });
		if (list != null && list.size() > 0) {
			// ��¼�ɹ������������˶�����ֵ��petOwner,�������еĲ�����Ϊ�ó�������
			petOwner = list.get(0);
		}

		// �����¼ʧ��������Ҳ��ִ��,����NullPoiterException
		System.out.println("��ӭ" + petOwner.getName() + "��¼��\n��" + petOwner.getMoney() + "\n");
		return petOwner;// ����¼���˷��ظ����÷���,��¼ʧ�ܵ�ʱ��᷵��һ��Null
	}

	/** ���������ģ�� */
	@Override
	public void buyKuCunPet() {
		// ��ȡ���ݿ�����
		Connection con = dbHelper.getConnection();
		// ��ȡȫ������������˵�dog��penguin
		System.out.println("��������������������------------");
		List<Pet> list = petDao.queryByKuCun(con);
		int i = 0;
		System.out.println("���\t\t��������\t\t��������\t\t��������\t\tԪ����");
		for (Pet p : list) {
			System.out.println(++i + "\t\t" + p.getName() + "\t\t" + p.getTypeName() + "\t\t" + p.getBirthday() + "\t\t"
					+ getPrice(p.getBirthday()));
		}

		System.out.print("������Ҫ����ĳ������:");
		input = new Scanner(System.in);
		int index = input.nextInt();
		// ��ȡѡ�еĳ��������id
		long id = list.get(index - 1).getId();
		// ��ȡѡ�еĳ����������̵�id
		long store_id = list.get(index - 1).getStoreId();
		// �������ļ۸�
		int price = getPrice(list.get(index - 1).getBirthday());

		// ���ױ������һ�����׼�¼
		accountDao.executeSQL(
				"INSERT INTO account(deal_type,pet_id,seller_id,buyer_id,price,deal_time) VALUES(?,?,?,?,?,?)",
				new Object[] { 1, id, store_id, petOwner.getId(), price,
						new java.sql.Date(System.currentTimeMillis()) });

		// ���������޸�Ԫ��������������ļ۸�
		petOwnerDao.executeSQL("UPDATE petowner SET money=? WHERE id=?",
				new Object[] { petOwner.getMoney() - price, petOwner.getId() });

		// �����޸�����idΪ�������ĳ�������id
		petDao.executeSQL("UPDATE pet SET owner_id=?,store_id=null WHERE id=?", new Object[] { petOwner.getId(), id });

		// ��ѯ������������̵�,�Եõ��̵�ԭ����Ԫ����
		PetStore petStore = petStoreDao.queryByTj(con, "id=?", new Object[] { store_id }).get(0);

		// �޸��̵��Ԫ������ȡ��ԭ����Ԫ�����������������Ǯ
		petStoreDao.executeSQL("UPDATE petstore SET balance=? WHERE id=?",
				new Object[] { petStore.getBalance() + price, store_id });

		System.out.println("���׳ɹ���");
	}

	/**
	 * ��ȡ����ļ۸�
	 * 
	 * @param birthday
	 *            ���������
	 * @return ����ļ۸�
	 */
	private int getPrice(Date birthday) {
		birthday.setYear(birthday.getYear() + 5);
		Date now = new Date();
		if (birthday.after(now)) {
			return 5;
		} else {
			return 3;
		}
	}

	/** �����³��﹦��ģ�� */
	@Override
	public void buyNewPet() {

		// ��ȡ���ݿ�����
		Connection con = dbHelper.getConnection();
		// ��ȡȫ����������������˵ķ�dog��penguin
		System.out.println("������������������������------------");
		List<Pet> list = petDao.queryByNew(con);
		int i = 0;
		System.out.println("���\t\t��������\t\t��������\t\t��������\t\tԪ����");
		for (Pet p : list) {
			System.out.println(++i + "\t\t" + p.getName() + "\t\t" + p.getTypeName() + "\t\t" + p.getBirthday() + "\t\t"
					+ getPrice(p.getBirthday()));
		}

		System.out.print("������Ҫ����ĳ������:");
		input = new Scanner(System.in);
		int index = input.nextInt();
		// ��ȡѡ�еĳ��������id
		long id = list.get(index - 1).getId();
		// ��ȡѡ�еĳ����������̵�id
		long store_id = list.get(index - 1).getStoreId();
		// �������ļ۸�
		int price = getPrice(list.get(index - 1).getBirthday());

		// ���ױ������һ�����׼�¼
		accountDao.executeSQL(
				"INSERT INTO account(deal_type,pet_id,seller_id,buyer_id,price,deal_time) VALUES(?,?,?,?,?,?)",
				new Object[] { 1, id, store_id, petOwner.getId(), price,
						new java.sql.Date(System.currentTimeMillis()) });

		// ���������޸�Ԫ��������������ļ۸�
		petOwnerDao.executeSQL("UPDATE petowner SET money=? WHERE id=?",
				new Object[] { petOwner.getMoney() - price, petOwner.getId() });

		// �����޸�����idΪ�������ĳ�������id
		petDao.executeSQL("UPDATE pet SET owner_id=?,store_id=null WHERE id=?", new Object[] { petOwner.getId(), id });

		// ��ѯ������������̵�,�Եõ��̵�ԭ����Ԫ����
		PetStore petStore = petStoreDao.queryByTj(con, "id=?", new Object[] { store_id }).get(0);

		// �޸��̵��Ԫ������ȡ��ԭ����Ԫ�����������������Ǯ
		petStoreDao.executeSQL("UPDATE petstore SET balance=? WHERE id=?",
				new Object[] { petStore.getBalance() + price, store_id });

		System.out.println("���׳ɹ���");
	}

	/** �������﹦��ģ�� */
	@Override
	public void salePet() {
		System.out.println("�ҵĳ����б�");
		Connection con = dbHelper.getConnection();
		// ��ѯ�ҵĳ����б�,����petOwner,�ö����ڵ�¼�ɹ�ʱ��ֵ��
		List<Pet> list = petDao.queryByOwner(con, petOwner.getId());
		int i = 0;
		System.out.println("���\t\t��������\t\t��������\t\t��������\t\tԪ����");
		for (Pet p : list) {
			// �������Ŵ�1��ʼ,����ѡ���ʱ��Ҫ-1
			System.out.println(++i + "\t\t" + p.getName() + "\t\t" + p.getTypeName() + "\t\t" + p.getBirthday() + "\t\t"
					+ getPrice(p.getBirthday()));
		}

		System.out.print("��ѡ��Ҫ���۵ĳ�����ţ�");
		input = new Scanner(System.in);
		// ��ȡ�û�ѡ�еĳ������
		Pet p = list.get(input.nextInt() - 1);
		System.out.println("��Ҫ�����ĳ�����Ϣ���£�");
		System.out.println("�������֣�" + p.getName() + "\n�������" + p.getTypeName());
		// ȷ������
		System.out.print("��ȷ���Ƿ��������(y/n):");
		String choice = input.next();
		if ("y".equalsIgnoreCase(choice)) {
			// ��ʾ�̵�
			System.out.print("��ѡ�������ĸ��̵꣺");
			List<PetStore> pst = petStoreDao.queryAll(con);
			System.out.println("-----------------------------------");
			i = 0;
			System.out.println("���\t\t�̵�����\t\t�̵�Ԫ��");
			for (PetStore store : pst) {
				System.out.println(++i + "\t\t" + store.getName() + "\t\t" + store.getBalance());
			}
			System.out.println("-----------------------------------");

			// ��ȡ�û�ѡ����̵꣬��ȡ��������id
			PetStore petStore = pst.get(input.nextInt() - 1);
			long store_id = petStore.getId();
			// �������ļ۸�
			int price = getPrice(p.getBirthday());

			// ���ױ������һ�����׼�¼
			accountDao.executeSQL(
					"INSERT INTO account(deal_type,pet_id,seller_id,buyer_id,price,deal_time) VALUES(?,?,?,?,?,?)",
					new Object[] { 2, p.getId(), petOwner.getId(), store_id, price,
							new java.sql.Date(System.currentTimeMillis()) });

			// ���������޸�Ԫ��������������ļ۸�
			petOwnerDao.executeSQL("UPDATE petowner SET money=? WHERE id=?",
					new Object[] { petOwner.getMoney() + price, petOwner.getId() });

			// �޸ĳ����̵�idΪ�������ĳ����̵�id������id����Ϊnull
			petDao.executeSQL("UPDATE pet SET owner_id=null,store_id=? WHERE id=?",
					new Object[] { store_id, p.getId() });

			// �޸��̵��Ԫ������ȡ��ԭ����Ԫ�����������������Ǯ
			petStoreDao.executeSQL("UPDATE petstore SET balance=? WHERE id=?",
					new Object[] { petStore.getBalance() - price, store_id });

			System.out.println("���׳ɹ���");
		}
	}
}
