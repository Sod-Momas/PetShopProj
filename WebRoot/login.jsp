<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="cn.icloudit.pet.entity.PetOwner"%>
<%@page import="cn.icloudit.pet.dao.IPetDAO"%>
<%@page import="cn.icloudit.pet.dao.impl.PetDAOImpl"%>
<%@page import="cn.icloudit.pet.utils.DBHelper"%>
<%@page import="cn.icloudit.pet.entity.Pet"%>
<%@page import="java.sql.Connection"%>
<%@page import="cn.icloudit.pet.dao.IPetOwnerDAO"%>
<%@page import="cn.icloudit.pet.dao.impl.PetOwnerDAOImpl"%>
<%@page import="cn.icloudit.pet.dao.IPetStoreDAO"%>
<%@page import="cn.icloudit.pet.dao.impl.PetStoreDAOImpl"%>
<%@page import="cn.icloudit.pet.entity.PetStore"%>

<!DOCTYPE html">
<html>
	<head>
		<title>登录页面</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<style type="text/css">
			.list {
				width: 100%;
				text-align: center;
				border: 1px dashed #000;
			}
		</style>
	</head>

	<body>&nbsp; 
		<%!	PetOwner petOwner = null;%>
		<%!
			public PetOwner login(String username,String password){
				IPetOwnerDAO petOwnerDao =new PetOwnerDAOImpl();
				List<PetOwner> polist = petOwnerDao.queryAll(new DBHelper().getConnection());
				for(PetOwner p:polist){
					if(p.getName().equals(username)&&p.getPassword().equals(password)){
						return p;
					}
				}
				return null;
			}
		
		 %>
		<div id="body" style="width: 700px; margin: 0 auto;">
			<h1>
				【代码】在网页中展示宠物信息并实现pet登录
			</h1>
			<hr />
			<p style="text-align: right;">
				<a href="index.jsp">返回主页</a>
			</p>
			
			<%
				String loginMoule=
			"<div>"+
				"<form style=\"width:200px;margin:0 auto;\">"+
					"<label for=\"username\">用户名:</label><br/>"+
					"<input type=\"text\" name=\"username\" id=\"username\"/><br/>"+
					"<label for=\"password\">密&nbsp;&nbsp;码:</label><br/>"+
					"<input type=\"password\" name=\"password\" id=\"password\" /><br/>"+
					"<input type=\"submit\"/>&nbsp;&nbsp;"+
					"<input type=\"reset\"/>"+
				"</form>"+
			"</div>";
			 %>
			<%
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				this.petOwner = login(username,password);
				
				if(null==this.petOwner){
					out.print(loginMoule);					
				}
			 %>
				
			<table rules="rows" id="t1" class="list" cellpadding="10">
				<caption>宠物们</caption>
				<tr>
					<th>序号</th>
					<th>
						宠物名称
					</th>
					<th>
						宠物品种
					</th>
					<th>
						宠物生日
					</th>
				</tr>
				<%
					IPetDAO petDao = new PetDAOImpl();
					List<Pet> plist = petDao.queryAll(new DBHelper().getConnection());

					if (null != plist || plist.size() > 0) {
						for (int i = 0; i < plist.size(); i++) {
							out.print(
							"<tr>" +
								"<td>" + (i + 1) + "</td>" + 
								"<td>"	+ plist.get(i).getName() + "</td>" + 
								"<td>"	+ plist.get(i).getTypeName()+ "</td>" + 
								"<td>"	+ plist.get(i).getBirthday() + "</td>" +
							"</tr>");
						}
					}
				%>
			</table>
			<table rules="rows" id="t2" class="list" cellpadding="10">
				<caption>主人们</caption>
				<tr>
					<th>序号</th>
					<th>名称</th>
				</tr>
				<%
					IPetOwnerDAO petOwnerDao =new PetOwnerDAOImpl();
					List<PetOwner> polist = petOwnerDao.queryAll(new DBHelper().getConnection());
					
					if(null!=polist||polist.size()>0)
					{
						for(int i=0;i<polist.size();i++){
							out.print(
								"<tr>" +
									"<td>" + (i + 1) + "</td>" + 
									"<td>"	+ polist.get(i).getName() + "</td>" + 
								"</tr>"
							);
						}
					}
				 %>
				
			</table>

			<table rules="rows" id="t3" class="list" cellpadding="10">
				<caption>商店们</caption>
				<tr>
					<th>序号</th>
					<th>名称</th>
				</tr>
				<%
					IPetStoreDAO petStoreDao=new PetStoreDAOImpl();
					List<PetStore> petStore =petStoreDao.queryAll(new DBHelper().getConnection());
					
					if(null!=petStore||petStore.size()>0)
					{
						for(int i=0;i<petStore.size();i++){
							out.print(
								"<tr>" +
									"<td>" + (i + 1) + "</td>" + 
									"<td>"	+ petStore.get(i).getName() + "</td>" + 
								"</tr>"
							);
						}
					}
				 %>
			</table>
		</div>
		

	</body>
</html>
