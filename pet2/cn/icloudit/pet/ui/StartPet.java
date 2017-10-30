package cn.icloudit.pet.ui;

import java.util.Scanner;

import cn.icloudit.pet.entity.PetOwner;
import cn.icloudit.pet.service.IPetService;

/**
 * 表示层类
 * 
 * @author 夏小山
 *
 */
public class StartPet {

	/** 用于输入数据的工具变量 */
	private Scanner input = new Scanner(System.in);
	/** 服务提供方 */
	private IPetService petService;

	/**
	 * IPetService 的 getter方法
	 * 
	 * @param petService
	 *            一个IPetService对象
	 */
	public void setPm(IPetService petService) {
		this.petService = petService;
	}

	/**
	 * 初始化表示层,显示欢迎页面
	 */
	public void init() {
		petService.init();
	}

	/**
	 * 显示登录菜单
	 */
	public void loginMenu() {
		boolean isLoop;// 循环开关
		do {
			isLoop = false;// 每次开始重置为false以保证不会陷入无限循环
			System.out.println("***************************");
			System.out.println("\t1.宠物主人登录");
			System.out.println("\t2.宠物商店登录");
			System.out.println("\t3.退出");
			System.out.println("***************************");
			System.out.print("请选择：");

			try {
				// 这个语句可能抛异常 InputMismatchException
				int choice = input.nextInt();

				switch (choice) {
				case 1:
					System.out.println("宠物主人登录");
					PetOwner po = petService.petOwnerLogin();// 调用登录方法而非自己实现
					if (po == null) {// 如果登录成功这个对象会被赋值,为null表示登录失败
						System.out.println("登录失败！请重新登录！");
						break;// 跳出登录,程序结束
					} else {
						// 登录成功,进入买卖菜单
						buySalePetMenu();
					}
					break;

				case 2:
					System.out.println("宠物商店登录");
					// 应该做一个类似宠物主人登录的流程
					break;

				case 3:// 我觉得用0来表示退出更加合适
					System.out.println("退出");
					break;

				default:// 输入的是数字但并不是范围内的,被default捕捉
					System.out.println("请输入数字1－3");
					isLoop = true;// 给一个再来一次的机会
					input = new Scanner(System.in); // 初始化输入法,否则可能出现缓冲区问题
					break;
				}
			} catch (Exception e) {
				// InputMismatchException
				// 输入了非数字会执行此块,打开循环开关以重新来一次
				System.out.println("请正确输入数字！");
				isLoop = true;
				input = new Scanner(System.in); // 初始化输入法,否则可能出现缓冲区问题
			}
		} while (isLoop);
		// 1.登录失败会退出
		// 2.主动退出
	}

	/**
	 * 显示宠物买卖菜单
	 */
	public void buySalePetMenu() {
		System.out.println("***************************");
		System.out.println("\t1.购买宠物 ");
		System.out.println("\t2.卖出宠物 ");
		System.out.println("***************************");
		input = new Scanner(System.in);// 初始化输入法

		switch (input.nextInt()) {
		case 1:
			// 进入购买宠物
			// 这里还需要一个二级菜单选项
			buyPetMenu();
			break;
		case 2:
			// 进入卖出宠物功能
			petService.salePet();
			break;
		// 这里应该需要一个default捕捉输入超过范围选项,然后执行相应操作
		}
		// 1.输入超过范围会退出
		// 2.方法正常执行完毕退出
	}

	/**
	 * 显示购买宠物菜单
	 */
	public void buyPetMenu() {
		System.out.println("***************************");
		System.out.println("\t1.购买库存宠物 ");
		System.out.println("\t2.购买新培育宠物 ");
		System.out.println("***************************");
		input = new Scanner(System.in);// 重新初始化输入法

		switch (input.nextInt()) {
		case 1:
			// 进入购买库存宠物功能
			petService.buyKuCunPet();
			break;
		case 2:
			// 进入购买新培育宠物功能
			petService.buyNewPet();
			break;
		// 这里应该需要一个default捕捉输入超过范围选项,然后执行相应操作
		}
		// 1.输入超过范围会退出
		// 2.方法正常执行完毕退出
	}

}
