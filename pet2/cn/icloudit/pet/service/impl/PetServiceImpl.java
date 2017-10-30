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
 * @author 夏小山
 *
 */
public class PetServiceImpl implements IPetService {
	// *********必要成员****************
	/** Account的DAO */
	private IAccountDAO accountDao;
	/** Pet的DAO */
	private IPetDAO petDao;
	/** PetOwner的DAO */
	private IPetOwnerDAO petOwnerDao;
	/** PetStore的DAO */
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

	/** 输入法 */
	private Scanner input = new Scanner(System.in);
	/** DBhHelper 数据库帮助类 */
	private DBHelper dbHelper = new DBHelper();
	/** 登录者 */
	private PetOwner petOwner;

	/**
	 * 显示服务层欢迎菜单
	 */
	@Override
	public void init() {
		// 获取数据库连接
		Connection con = dbHelper.getConnection();
		System.out.println("系统启动中...................");
		// 查询所有宠物
		List<Pet> list = petDao.queryAll(con);
		System.out.println("宠物从mysql中醒来.............");
		// 显示所有宠物
		int i = 0;
		System.out.println("序号\t\t宠物名称\t\t宠物种类\t\t宠物生日");
		for (Pet p : list) {
			System.out.println(++i + "\t\t" + p.getName() + "\t\t" + p.getTypeName() + "\t\t" + p.getBirthday());
		}

		// 查询所有主人
		List<PetOwner> owners = petOwnerDao.queryAll(con);
		System.out.println("-----------------------------------");
		System.out.println("宠物主人从mysql中醒来.............");
		// 显示所有主人
		i = 0;
		System.out.println("序号\t\t主人名称\t\t主人元宝");
		for (PetOwner po : owners) {
			System.out.println(++i + "\t\t" + po.getName() + "\t\t" + po.getMoney());
		}

		// 查询所有商店
		List<PetStore> pst = petStoreDao.queryAll(con);
		System.out.println("-----------------------------------");
		System.out.println("宠物商店从mysql中醒来.............");
		// 显示所有商店
		i = 0;
		System.out.println("序号\t\t商店名称\t\t商店元宝");
		for (PetStore store : pst) {
			System.out.println(++i + "\t\t" + store.getName() + "\t\t" + store.getBalance());
		}
	}

	/** 主人登录模块 */
	@Override
	public PetOwner petOwnerLogin() {
		// 输入用户名密码
		System.out.print("请输入宠物主人姓名：");
		String name = input.next();
		System.out.print("请输入宠物主人密码：");
		String password = input.next();
		if ("".equals(name) || "".equals(password)) {
			System.out.println("用户名与密码不能为空！");
			return null;
		}

		// 尝试登录
		Connection con = dbHelper.getConnection();
		List<PetOwner> list = petOwnerDao.queryByTj(con, "name=? AND password=?", new Object[] { name, password });
		if (list != null && list.size() > 0) {
			// 登录成功，将宠物主人对象设值给petOwner,后面所有的操作均为该宠物主人
			petOwner = list.get(0);
		}

		// 如果登录失败了这里也会执行,会有NullPoiterException
		System.out.println("欢迎" + petOwner.getName() + "登录！\n余额：" + petOwner.getMoney() + "\n");
		return petOwner;// 将登录的人返回给调用方法,登录失败的时候会返回一个Null
	}

	/** 购买库存宠物模块 */
	@Override
	public void buyKuCunPet() {
		// 获取数据库连接
		Connection con = dbHelper.getConnection();
		// 获取全部库存宠物：无主人的dog与penguin
		System.out.println("－－－－－－－库存宠物------------");
		List<Pet> list = petDao.queryByKuCun(con);
		int i = 0;
		System.out.println("序号\t\t宠物名称\t\t宠物种类\t\t宠物生日\t\t元宝数");
		for (Pet p : list) {
			System.out.println(++i + "\t\t" + p.getName() + "\t\t" + p.getTypeName() + "\t\t" + p.getBirthday() + "\t\t"
					+ getPrice(p.getBirthday()));
		}

		System.out.print("请输入要购买的宠物序号:");
		input = new Scanner(System.in);
		int index = input.nextInt();
		// 获取选中的宠物的主键id
		long id = list.get(index - 1).getId();
		// 获取选中的宠物所属的商店id
		long store_id = list.get(index - 1).getStoreId();
		// 计算宠物的价格
		int price = getPrice(list.get(index - 1).getBirthday());

		// 交易表中添加一条交易记录
		accountDao.executeSQL(
				"INSERT INTO account(deal_type,pet_id,seller_id,buyer_id,price,deal_time) VALUES(?,?,?,?,?,?)",
				new Object[] { 1, id, store_id, petOwner.getId(), price,
						new java.sql.Date(System.currentTimeMillis()) });

		// 宠物主人修改元宝数，减掉宠物的价格
		petOwnerDao.executeSQL("UPDATE petowner SET money=? WHERE id=?",
				new Object[] { petOwner.getMoney() - price, petOwner.getId() });

		// 宠物修改主人id为购买它的宠物主人id
		petDao.executeSQL("UPDATE pet SET owner_id=?,store_id=null WHERE id=?", new Object[] { petOwner.getId(), id });

		// 查询出卖掉宠物的商店,以得到商店原来的元宝数
		PetStore petStore = petStoreDao.queryByTj(con, "id=?", new Object[] { store_id }).get(0);

		// 修改商店的元宝数，取出原来的元宝数加上卖掉宠物的钱
		petStoreDao.executeSQL("UPDATE petstore SET balance=? WHERE id=?",
				new Object[] { petStore.getBalance() + price, store_id });

		System.out.println("交易成功！");
	}

	/**
	 * 获取宠物的价格
	 * 
	 * @param birthday
	 *            宠物的生日
	 * @return 宠物的价格
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

	/** 培育新宠物功能模块 */
	@Override
	public void buyNewPet() {

		// 获取数据库连接
		Connection con = dbHelper.getConnection();
		// 获取全部新培育宠物：无主人的非dog与penguin
		System.out.println("－－－－－－－新培育宠物------------");
		List<Pet> list = petDao.queryByNew(con);
		int i = 0;
		System.out.println("序号\t\t宠物名称\t\t宠物种类\t\t宠物生日\t\t元宝数");
		for (Pet p : list) {
			System.out.println(++i + "\t\t" + p.getName() + "\t\t" + p.getTypeName() + "\t\t" + p.getBirthday() + "\t\t"
					+ getPrice(p.getBirthday()));
		}

		System.out.print("请输入要购买的宠物序号:");
		input = new Scanner(System.in);
		int index = input.nextInt();
		// 获取选中的宠物的主键id
		long id = list.get(index - 1).getId();
		// 获取选中的宠物所属的商店id
		long store_id = list.get(index - 1).getStoreId();
		// 计算宠物的价格
		int price = getPrice(list.get(index - 1).getBirthday());

		// 交易表中添加一条交易记录
		accountDao.executeSQL(
				"INSERT INTO account(deal_type,pet_id,seller_id,buyer_id,price,deal_time) VALUES(?,?,?,?,?,?)",
				new Object[] { 1, id, store_id, petOwner.getId(), price,
						new java.sql.Date(System.currentTimeMillis()) });

		// 宠物主人修改元宝数，减掉宠物的价格
		petOwnerDao.executeSQL("UPDATE petowner SET money=? WHERE id=?",
				new Object[] { petOwner.getMoney() - price, petOwner.getId() });

		// 宠物修改主人id为购买它的宠物主人id
		petDao.executeSQL("UPDATE pet SET owner_id=?,store_id=null WHERE id=?", new Object[] { petOwner.getId(), id });

		// 查询出卖掉宠物的商店,以得到商店原来的元宝数
		PetStore petStore = petStoreDao.queryByTj(con, "id=?", new Object[] { store_id }).get(0);

		// 修改商店的元宝数，取出原来的元宝数加上卖掉宠物的钱
		petStoreDao.executeSQL("UPDATE petstore SET balance=? WHERE id=?",
				new Object[] { petStore.getBalance() + price, store_id });

		System.out.println("交易成功！");
	}

	/** 出卖宠物功能模块 */
	@Override
	public void salePet() {
		System.out.println("我的宠物列表");
		Connection con = dbHelper.getConnection();
		// 查询我的宠物列表,我是petOwner,该对象在登录成功时赋值。
		List<Pet> list = petDao.queryByOwner(con, petOwner.getId());
		int i = 0;
		System.out.println("序号\t\t宠物名称\t\t宠物种类\t\t宠物生日\t\t元宝数");
		for (Pet p : list) {
			// 这里的序号从1开始,后面选择的时候要-1
			System.out.println(++i + "\t\t" + p.getName() + "\t\t" + p.getTypeName() + "\t\t" + p.getBirthday() + "\t\t"
					+ getPrice(p.getBirthday()));
		}

		System.out.print("请选择要出售的宠物序号：");
		input = new Scanner(System.in);
		// 获取用户选中的宠物对象
		Pet p = list.get(input.nextInt() - 1);
		System.out.println("您要卖出的宠物信息如下：");
		System.out.println("宠物名字：" + p.getName() + "\n宠物类别：" + p.getTypeName());
		// 确认卖出
		System.out.print("请确认是否真的卖出(y/n):");
		String choice = input.next();
		if ("y".equalsIgnoreCase(choice)) {
			// 显示商店
			System.out.print("请选择卖给哪个商店：");
			List<PetStore> pst = petStoreDao.queryAll(con);
			System.out.println("-----------------------------------");
			i = 0;
			System.out.println("序号\t\t商店名称\t\t商店元宝");
			for (PetStore store : pst) {
				System.out.println(++i + "\t\t" + store.getName() + "\t\t" + store.getBalance());
			}
			System.out.println("-----------------------------------");

			// 获取用户选择的商店，并取出其主键id
			PetStore petStore = pst.get(input.nextInt() - 1);
			long store_id = petStore.getId();
			// 计算宠物的价格
			int price = getPrice(p.getBirthday());

			// 交易表中添加一条交易记录
			accountDao.executeSQL(
					"INSERT INTO account(deal_type,pet_id,seller_id,buyer_id,price,deal_time) VALUES(?,?,?,?,?,?)",
					new Object[] { 2, p.getId(), petOwner.getId(), store_id, price,
							new java.sql.Date(System.currentTimeMillis()) });

			// 宠物主人修改元宝数，减掉宠物的价格
			petOwnerDao.executeSQL("UPDATE petowner SET money=? WHERE id=?",
					new Object[] { petOwner.getMoney() + price, petOwner.getId() });

			// 修改宠物商店id为购买它的宠物商店id，主人id设置为null
			petDao.executeSQL("UPDATE pet SET owner_id=null,store_id=? WHERE id=?",
					new Object[] { store_id, p.getId() });

			// 修改商店的元宝数，取出原来的元宝数加上卖掉宠物的钱
			petStoreDao.executeSQL("UPDATE petstore SET balance=? WHERE id=?",
					new Object[] { petStore.getBalance() - price, store_id });

			System.out.println("交易成功！");
		}
	}
}
