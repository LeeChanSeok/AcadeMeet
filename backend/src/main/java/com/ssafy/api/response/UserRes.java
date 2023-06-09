package com.ssafy.api.response;

import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

/**
 * 회원 본인 정보 조회 API ([GET] /api/v1/users/me) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
@Component
public class UserRes{

	public static String filePath;
	@Value("${FILE_PATH}")
	public void setValue(String value){
		filePath = value;
	}
	static public String getFilePath() {return filePath;}

	@ApiModelProperty(name = "user id")
	int id;
	@ApiModelProperty(name="User Email")
	String email;
	@ApiModelProperty(name="name", example="your_name")
	String name;
	@ApiModelProperty(name="birth", example="your_birth")
	String birth;
	@ApiModelProperty(name="nick", example="your_nick")
	String nick;
	@ApiModelProperty(name="phone", example="your_phone")
	String phone;
	@ApiModelProperty(name="profile", example="your_profile")
	Resource profile;

	public static UserRes of(User user) throws MalformedURLException {

		UserRes res = new UserRes();
		res.setId(user.getId());
		res.setEmail(user.getEmail());
		res.setName(user.getName());
		res.setBirth(user.getBirth());
		res.setPhone(user.getPhone());
		res.setNick(user.getNick());

		res.setProfile(new UrlResource("file:"+getFilePath() +user.getProfile()));
		return res;
	}
}
