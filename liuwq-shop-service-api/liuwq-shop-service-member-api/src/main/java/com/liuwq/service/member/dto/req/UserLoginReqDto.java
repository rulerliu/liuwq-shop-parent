package com.liuwq.service.member.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginReqDto {
    /**
     * 手机号码
     * 密码
     * 短信验证码
     */
    /**
     * 手机号码
     */

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
    @ApiModelProperty(value = "手机号码", name = "mobile", required = true)
    private String mobile;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @ApiModelProperty(value = "密码", name = "passWord", required = true)
    private String passWord;
//
//    private String smsCode;

}
