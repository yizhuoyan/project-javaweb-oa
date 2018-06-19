package com.neusoft.oa.common.web.servlet;

import java.io.File;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.neusoft.oa.common.function.CommonFunction;
import com.neusoft.oa.core.dto.UserContext;
import com.neusoft.oa.core.service.FunctionFactory;
import com.neusoft.oa.core.web.servlet.CommonServlet;
@WebServlet("/user/modifyAvatar.do")
@MultipartConfig(maxFileSize=2048000)
public class ModifyUserAvatarServlet extends CommonServlet{

	@Override
	protected Object handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		UserContext uc = this.getCurrentUser(req);
		String userId=uc.getId();
		Part avatarPart=req.getPart("avatar");
		String avatarDir="/img/avatar/";
		String saveDir=this.getServletContext().getRealPath(avatarDir);
		String saveName=userId+"-"+avatarPart.getSubmittedFileName();
		System.out.println(new File(saveDir,saveName).getCanonicalPath());
		avatarPart.write(new File(saveDir,saveName).getCanonicalPath());
		//更新数据库
		CommonFunction fun=FunctionFactory.getFunction(CommonFunction.class);
		String newAvatar=avatarDir+saveName;
		fun.modifyUserAvatar(userId, newAvatar);
		//更新内存
		uc.setAvatar(newAvatar);
		
		return "@/jsp/base/userhome/my-account.jsp";
	}
}
