package cn.icloudit.pet.ui;

import java.util.Scanner;

import cn.icloudit.pet.entity.PetOwner;
import cn.icloudit.pet.service.IPetService;

/**
 * ��ʾ����
 * 
 * @author ��Сɽ
 *
 */
public class StartPet {

	/** �����������ݵĹ��߱��� */
	private Scanner input = new Scanner(System.in);
	/** �����ṩ�� */
	private IPetService petService;

	/**
	 * IPetService �� getter����
	 * 
	 * @param petService
	 *            һ��IPetService����
	 */
	public void setPm(IPetService petService) {
		this.petService = petService;
	}

	/**
	 * ��ʼ����ʾ��,��ʾ��ӭҳ��
	 */
	public void init() {
		petService.init();
	}

	/**
	 * ��ʾ��¼�˵�
	 */
	public void loginMenu() {
		boolean isLoop;// ѭ������
		do {
			isLoop = false;// ÿ�ο�ʼ����Ϊfalse�Ա�֤������������ѭ��
			System.out.println("***************************");
			System.out.println("\t1.�������˵�¼");
			System.out.println("\t2.�����̵��¼");
			System.out.println("\t3.�˳�");
			System.out.println("***************************");
			System.out.print("��ѡ��");

			try {
				// ������������쳣 InputMismatchException
				int choice = input.nextInt();

				switch (choice) {
				case 1:
					System.out.println("�������˵�¼");
					PetOwner po = petService.petOwnerLogin();// ���õ�¼���������Լ�ʵ��
					if (po == null) {// �����¼�ɹ��������ᱻ��ֵ,Ϊnull��ʾ��¼ʧ��
						System.out.println("��¼ʧ�ܣ������µ�¼��");
						break;// ������¼,�������
					} else {
						// ��¼�ɹ�,���������˵�
						buySalePetMenu();
					}
					break;

				case 2:
					System.out.println("�����̵��¼");
					// Ӧ����һ�����Ƴ������˵�¼������
					break;

				case 3:// �Ҿ�����0����ʾ�˳����Ӻ���
					System.out.println("�˳�");
					break;

				default:// ����������ֵ������Ƿ�Χ�ڵ�,��default��׽
					System.out.println("����������1��3");
					isLoop = true;// ��һ������һ�εĻ���
					input = new Scanner(System.in); // ��ʼ�����뷨,������ܳ��ֻ���������
					break;
				}
			} catch (Exception e) {
				// InputMismatchException
				// �����˷����ֻ�ִ�д˿�,��ѭ��������������һ��
				System.out.println("����ȷ�������֣�");
				isLoop = true;
				input = new Scanner(System.in); // ��ʼ�����뷨,������ܳ��ֻ���������
			}
		} while (isLoop);
		// 1.��¼ʧ�ܻ��˳�
		// 2.�����˳�
	}

	/**
	 * ��ʾ���������˵�
	 */
	public void buySalePetMenu() {
		System.out.println("***************************");
		System.out.println("\t1.������� ");
		System.out.println("\t2.�������� ");
		System.out.println("***************************");
		input = new Scanner(System.in);// ��ʼ�����뷨

		switch (input.nextInt()) {
		case 1:
			// ���빺�����
			// ���ﻹ��Ҫһ�������˵�ѡ��
			buyPetMenu();
			break;
		case 2:
			// �����������﹦��
			petService.salePet();
			break;
		// ����Ӧ����Ҫһ��default��׽���볬����Χѡ��,Ȼ��ִ����Ӧ����
		}
		// 1.���볬����Χ���˳�
		// 2.��������ִ������˳�
	}

	/**
	 * ��ʾ�������˵�
	 */
	public void buyPetMenu() {
		System.out.println("***************************");
		System.out.println("\t1.��������� ");
		System.out.println("\t2.�������������� ");
		System.out.println("***************************");
		input = new Scanner(System.in);// ���³�ʼ�����뷨

		switch (input.nextInt()) {
		case 1:
			// ���빺������﹦��
			petService.buyKuCunPet();
			break;
		case 2:
			// ���빺�����������﹦��
			petService.buyNewPet();
			break;
		// ����Ӧ����Ҫһ��default��׽���볬����Χѡ��,Ȼ��ִ����Ӧ����
		}
		// 1.���볬����Χ���˳�
		// 2.��������ִ������˳�
	}

}
