package com.yeeph.auth.feign.fallback;

import com.yeeph.auth.feign.MemberFeignService;
import com.yeeph.auth.vo.SocialUser;
import com.yeeph.auth.vo.UserLoginVo;
import com.yeeph.auth.vo.UserRegisterVo;
import com.yeeph.common.exception.BizCodeEnum;
import com.yeeph.common.utils.R;
import org.springframework.stereotype.Service;

@Service
public class MemberFallbackService implements MemberFeignService {
    @Override
    public R register2(UserRegisterVo registerVo) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R register(UserRegisterVo registerVo) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R login(UserLoginVo loginVo) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R login(SocialUser socialUser) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }

    @Override
    public R info(Long id) {
        return R.error(BizCodeEnum.READ_TIME_OUT_EXCEPTION.getCode(), BizCodeEnum.READ_TIME_OUT_EXCEPTION.getMsg());
    }
}
