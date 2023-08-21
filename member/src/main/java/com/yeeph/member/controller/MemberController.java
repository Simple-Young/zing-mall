package com.yeeph.member.controller;

import com.yeeph.common.exception.BizCodeEnum;
import com.yeeph.common.utils.PageUtils;
import com.yeeph.common.utils.R;
import com.yeeph.member.entity.MemberEntity;
import com.yeeph.member.exception.EmailExistException;
import com.yeeph.member.exception.PhoneNumExistException;
import com.yeeph.member.exception.UserExistException;
import com.yeeph.member.feign.CouponFeignService;
import com.yeeph.member.service.MemberService;
import com.yeeph.member.vo.MemberLoginVo;
import com.yeeph.member.vo.MemberRegisterVo;
import com.yeeph.member.vo.SocialUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;


@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Resource
    private CouponFeignService couponFeignService;

    @PostMapping("/register2")
    public R register2(@RequestBody MemberRegisterVo registerVo) {
        try {
            memberService.register(registerVo);
        } catch (UserExistException userException) {
            return R.error(BizCodeEnum.USER_EXIST_EXCEPTION.getCode(), BizCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        } catch (PhoneNumExistException phoneException ) {
            return R.error(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(), BizCodeEnum.PHONE_EXIST_EXCEPTION.getMsg());
        }catch (EmailExistException emailExistException ) {
            return R.error(BizCodeEnum.EMAIL_EXIST_EXCEPTION.getCode(), BizCodeEnum.EMAIL_EXIST_EXCEPTION.getMsg());
        }
        return R.ok();
    }

    @PostMapping("/login")
    public R login(@RequestBody MemberLoginVo loginVo) {
        MemberEntity entity = memberService.login(loginVo);
        if (entity != null) {
            return R.ok().put("memberEntity", entity);
        } else {
            return R.error(BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getCode(), BizCodeEnum.LOGINACCT_PASSWORD_EXCEPTION.getMsg());
        }
    }

    @PostMapping("/oauth2/login")
    public R login(@RequestBody SocialUser socialUser) {
        MemberEntity entity = memberService.login(socialUser);
        if (entity != null) {
            return R.ok().put("memberEntity", entity);
        } else {
            return R.error();
        }
    }

    @PostMapping("/register")
    public R register(@RequestBody MemberRegisterVo registerVo) {
        try {
            memberService.register(registerVo);
        } catch (UserExistException userException) {
            return R.error(BizCodeEnum.USER_EXIST_EXCEPTION.getCode(), BizCodeEnum.USER_EXIST_EXCEPTION.getMsg());
        } catch (PhoneNumExistException phoneException ) {
            return R.error(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(), BizCodeEnum.PHONE_EXIST_EXCEPTION.getMsg());
        }catch (EmailExistException emailExistException ) {
            return R.error(BizCodeEnum.EMAIL_EXIST_EXCEPTION.getCode(), BizCodeEnum.EMAIL_EXIST_EXCEPTION.getMsg());
        }
        return R.ok();
    }



    @PostMapping("/coupons")
    public R test() {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setNickname("zhangsan");
        R memberCoupons = couponFeignService.memberCoupons();

        return memberCoupons.put("member", memberEntity).put("coupons", memberCoupons.get("coupons"));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = memberService.queryPage(params);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id) {
        MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody MemberEntity member) {
        memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody MemberEntity member) {
        memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids) {
        memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
